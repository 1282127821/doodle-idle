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
package org.doodle.idle.console.client;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.doodle.broker.client.BrokerClientRSocketRequester;
import org.doodle.design.broker.frame.BrokerFrame;
import org.doodle.design.broker.frame.BrokerFrameMimeTypes;
import org.doodle.design.broker.frame.BrokerFrameUtils;
import org.doodle.design.idle.console.*;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrokerConsoleClientRSocket implements ConsoleClientRSocket {
  BrokerClientRSocketRequester requester;
  BrokerFrame frame;

  public BrokerConsoleClientRSocket(
      BrokerClientRSocketRequester requester, ConsoleClientProperties properties) {
    this.requester = requester;
    this.frame = BrokerFrameUtils.unicast(properties.getServer().getTags());
  }

  @Override
  public Mono<ConsoleComponentPageReply> page(ConsoleComponentPageRequest request) {
    return route(ConsoleComponentPageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(ConsoleComponentPageReply.class);
  }

  @Override
  public Mono<ConsoleComponentQueryReply> query(ConsoleComponentQueryRequest request) {
    return route(ConsoleComponentQueryOps.RSocket.QUERY_MAPPING)
        .data(request)
        .retrieveMono(ConsoleComponentQueryReply.class);
  }

  @Override
  public Mono<ConsoleCrossPageReply> page(ConsoleCrossPageRequest request) {
    return route(ConsoleCrossPageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(ConsoleCrossPageReply.class);
  }

  @Override
  public Mono<ConsoleCrossQueryReply> query(ConsoleCrossQueryRequest request) {
    return route(ConsoleCrossQueryOps.RSocket.QUERY_MAPPING)
        .data(request)
        .retrieveMono(ConsoleCrossQueryReply.class);
  }

  @Override
  public Mono<ConsoleDbPageReply> page(ConsoleDbPageRequest request) {
    return route(ConsoleDbPageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(ConsoleDbPageReply.class);
  }

  @Override
  public Mono<ConsoleDbQueryReply> query(ConsoleDbQueryRequest request) {
    return route(ConsoleDbQueryOps.RSocket.QUERY_MAPPING)
        .data(request)
        .retrieveMono(ConsoleDbQueryReply.class);
  }

  @Override
  public Mono<ConsoleEcsPageReply> page(ConsoleEcsPageRequest request) {
    return route(ConsoleEcsPageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(ConsoleEcsPageReply.class);
  }

  @Override
  public Mono<ConsoleEcsQueryReply> query(ConsoleEcsQueryRequest request) {
    return route(ConsoleEcsQueryOps.RSocket.QUERY_MAPPING)
        .data(request)
        .retrieveMono(ConsoleEcsQueryReply.class);
  }

  @Override
  public Mono<ConsoleGamePageReply> page(ConsoleGamePageRequest request) {
    return route(ConsoleGamePageOps.RSocket.PAGE_MAPPING)
        .data(request)
        .retrieveMono(ConsoleGamePageReply.class);
  }

  @Override
  public Mono<ConsoleGameQueryReply> query(ConsoleGameQueryRequest request) {
    return route(ConsoleGameQueryOps.RSocket.QUERY_MAPPING)
        .data(request)
        .retrieveMono(ConsoleGameQueryReply.class);
  }

  protected RSocketRequester.RequestSpec route(String route) {
    return requester.route(route).metadata(frame, BrokerFrameMimeTypes.BROKER_FRAME_MIME_TYPE);
  }
}
