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
package org.doodle.idle.game.server.single;

import io.rsocket.Socket;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.packet.PacketExceptionHandler;
import org.doodle.design.socket.SocketConnectMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
@SpringBootApplication
public class GameServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(GameServerApplication.class, args);
  }

  @SocketConnectMapping
  public Mono<Void> connect(Socket socket) {
    return socket.onClose().doFirst(() -> log.info("客户端链接")).doOnTerminate(() -> log.info("客户端关闭"));
  }

  @PacketExceptionHandler
  public void onException(Exception e) {
    log.error("", e);
  }
}
