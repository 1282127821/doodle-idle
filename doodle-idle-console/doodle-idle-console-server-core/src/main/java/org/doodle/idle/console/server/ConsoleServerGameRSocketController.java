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
package org.doodle.idle.console.server;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.doodle.design.idle.console.*;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerGameRSocketController
    implements ConsoleGameQueryOps.RSocket, ConsoleGamePageOps.RSocket {
  ConsoleServerMapper mapper;
  ConsoleServerGameService gameService;

  @MessageMapping(ConsoleGamePageOps.RSocket.PAGE_MAPPING)
  @Override
  public Mono<ConsoleGamePageReply> page(ConsoleGamePageRequest request) {
    return Mono.fromSupplier(request::getPage)
        .map(mapper::fromProto)
        .flatMap(gameService::pageMono)
        .map(mapper::toGameInfoList)
        .map(mapper::toGamePageReply)
        .onErrorMap(ConsoleServerExceptions.Page::new);
  }

  @MessageExceptionHandler(ConsoleServerExceptions.Page.class)
  Mono<ConsoleGamePageReply> onPageException(ConsoleServerExceptions.Page ignored) {
    return Mono.just(mapper.toGamePageError(ConsoleErrorCode.FAILURE));
  }

  @MessageMapping(ConsoleGameQueryOps.RSocket.QUERY_MAPPING)
  @Override
  public Mono<ConsoleGameQueryReply> query(ConsoleGameQueryRequest request) {
    return Mono.fromSupplier(request::getUniqueId)
        .flatMap(gameService::queryMono)
        .map(mapper::toProto)
        .map(mapper::toGameQueryReply)
        .onErrorMap(ConsoleServerExceptions.Query::new);
  }

  @MessageExceptionHandler(ConsoleServerExceptions.Query.class)
  Mono<ConsoleGameQueryReply> onQueryException(ConsoleServerExceptions.Query ignored) {
    return Mono.just(mapper.toGameQueryError(ConsoleErrorCode.FAILURE));
  }
}
