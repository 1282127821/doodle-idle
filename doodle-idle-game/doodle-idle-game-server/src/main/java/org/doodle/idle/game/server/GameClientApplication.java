/*
 * Copyright (c) 2022-present Doodle. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.doodle.idle.game.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.rsocket.Payload;
import io.rsocket.Socket;
import io.rsocket.SocketConnectionSetupPayload;
import io.rsocket.core.SocketConnector;
import io.rsocket.metadata.CompositeMetadataCodec;
import io.rsocket.metadata.TaggingMetadataCodec;
import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.SocketClientTransport;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.doodle.boot.autoconfigure.socket.SocketServerAutoConfiguration;
import org.doodle.boot.socket.netty.NettySocketClientTransport;
import org.doodle.idle.autoconfigure.game.server.GameServerAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpClient;

@Slf4j
@SpringBootApplication(
    exclude = {SocketServerAutoConfiguration.class, GameServerAutoConfiguration.class})
public class GameClientApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(GameClientApplication.class);
  }

  @Override
  public void run(String... args) throws Exception {
    Mono<Socket> connect =
        SocketConnector.create()
            .setupPayload(DefaultPayload.create(UUID.randomUUID().toString()))
            .acceptor(this::accept)
            .connect(this::transport);
    Socket socket = connect.block();
    PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;
    while (!sendingSocket.isDisposed()) {
      TimeUnit.SECONDS.sleep(1);
      CompositeByteBuf composited = allocator.compositeBuffer();
      ByteBuf encodedRoute =
          TaggingMetadataCodec.createRoutingMetadata(allocator, Collections.singleton("1.1"))
              .getContent();
      CompositeMetadataCodec.encodeAndAddMetadata(
          composited, allocator, WellKnownMimeType.MESSAGE_RSOCKET_ROUTING, encodedRoute);
      sendingSocket
          .oneway(DefaultPayload.create(DefaultPayload.create("hi").data(), composited))
          .subscribe();
    }
  }

  Socket sendingSocket;

  private SocketClientTransport transport() {
    return new NettySocketClientTransport(
        TcpClientTransport.create(TcpClient.create().port(10001)));
  }

  private Mono<Socket> accept(SocketConnectionSetupPayload setupPayload, Socket socket) {
    sendingSocket = socket;
    return Mono.just(
        new Socket() {
          @Override
          public Mono<Void> oneway(Payload payload) {
            log.info("收到来自服务端的消息: {}", DefaultPayload.create(payload).getDataUtf8());
            return Mono.never();
          }
        });
  }
}
