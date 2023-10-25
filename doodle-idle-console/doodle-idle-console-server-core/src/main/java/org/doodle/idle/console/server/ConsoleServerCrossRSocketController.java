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
public class ConsoleServerCrossRSocketController
    implements ConsoleCrossQueryOps.RSocket, ConsoleCrossPageOps.RSocket {
  ConsoleServerMapper mapper;
  ConsoleServerCrossService crossService;

  @MessageMapping(ConsoleCrossPageOps.RSocket.PAGE_MAPPING)
  @Override
  public Mono<ConsoleCrossPageReply> page(ConsoleCrossPageRequest request) {
    return Mono.fromSupplier(request::getPage)
        .map(mapper::fromProto)
        .flatMap(crossService::pageMono)
        .map(mapper::toCrossInfoList)
        .map(mapper::toCrossPageReply)
        .onErrorMap(ConsoleServerExceptions.Page::new);
  }

  @MessageExceptionHandler(ConsoleServerExceptions.Page.class)
  Mono<ConsoleCrossPageReply> onPageException(ConsoleServerExceptions.Page ignored) {
    return Mono.just(mapper.toCrossPageError(ConsoleErrorCode.FAILURE));
  }

  @MessageMapping(ConsoleCrossQueryOps.RSocket.QUERY_MAPPING)
  @Override
  public Mono<ConsoleCrossQueryReply> query(ConsoleCrossQueryRequest request) {
    return Mono.fromCallable(request::getUniqueId)
        .flatMap(crossService::queryMono)
        .map(mapper::toProto)
        .map(mapper::toCrossQueryReply);
  }

  @MessageExceptionHandler(ConsoleServerExceptions.Query.class)
  Mono<ConsoleCrossQueryReply> onQueryException(ConsoleServerExceptions.Query ignored) {
    return Mono.just(mapper.toCrossQueryError(ConsoleErrorCode.FAILURE));
  }
}
