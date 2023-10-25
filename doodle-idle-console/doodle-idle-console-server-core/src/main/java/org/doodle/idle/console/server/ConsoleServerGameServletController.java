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
import org.doodle.design.common.Result;
import org.doodle.design.idle.console.ConsoleGamePageOps;
import org.doodle.design.idle.console.ConsoleGameQueryOps;
import org.doodle.design.idle.console.model.payload.reply.ConsoleGamePageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleGameQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleGamePageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleGameQueryRequest;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerGameServletController
    implements ConsoleGameQueryOps.Servlet, ConsoleGamePageOps.Servlet {
  ConsoleServerGameService gameService;

  @PostMapping(ConsoleGamePageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<ConsoleGamePageReply> page(ConsoleGamePageRequest request) {
    return Result.bad();
  }

  @PostMapping(ConsoleGameQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<ConsoleGameQueryReply> query(ConsoleGameQueryRequest request) {
    return Result.bad();
  }
}
