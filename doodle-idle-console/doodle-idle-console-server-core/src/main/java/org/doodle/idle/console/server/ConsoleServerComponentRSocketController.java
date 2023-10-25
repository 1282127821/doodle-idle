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
public class ConsoleServerComponentRSocketController
    implements ConsoleComponentQueryOps.RSocket, ConsoleComponentPageOps.RSocket {
  ConsoleServerMapper mapper;
  ConsoleServerComponentService componentService;

  @MessageMapping(ConsoleComponentPageOps.RSocket.PAGE_MAPPING)
  @Override
  public Mono<ConsoleComponentPageReply> page(ConsoleComponentPageRequest request) {
    return Mono.fromSupplier(request::getPage)
        .map(mapper::fromProto)
        .flatMap(componentService::pageMono)
        .map(mapper::toComponentInfoList)
        .map(mapper::toComponentPageReply)
        .onErrorMap(ConsoleServerExceptions.Page::new);
  }

  @MessageExceptionHandler(ConsoleServerExceptions.Page.class)
  Mono<ConsoleComponentPageReply> onPageException(ConsoleServerExceptions.Page ignored) {
    return Mono.just(mapper.toComponentPageError(ConsoleErrorCode.FAILURE));
  }

  @MessageMapping(ConsoleComponentQueryOps.RSocket.QUERY_MAPPING)
  @Override
  public Mono<ConsoleComponentQueryReply> query(ConsoleComponentQueryRequest request) {
    return Mono.fromSupplier(request::getUniqueId)
        .flatMap(componentService::queryMono)
        .map(mapper::toProto)
        .map(mapper::toComponentQueryReply)
        .onErrorMap(ConsoleServerExceptions.Query::new);
  }

  @MessageExceptionHandler(ConsoleServerExceptions.Query.class)
  Mono<ConsoleComponentQueryReply> onQueryException(ConsoleServerExceptions.Query ignored) {
    return Mono.just(mapper.toComponentQueryError(ConsoleErrorCode.FAILURE));
  }
}
