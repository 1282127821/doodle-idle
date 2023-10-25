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
import org.doodle.design.idle.console.ConsoleCrossPageOps;
import org.doodle.design.idle.console.ConsoleCrossQueryOps;
import org.doodle.design.idle.console.model.payload.reply.ConsoleCrossPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleCrossQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleCrossPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleCrossQueryRequest;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerCrossServletController
    implements ConsoleCrossQueryOps.Servlet, ConsoleCrossPageOps.Servlet {
  ConsoleServerCrossService crossService;

  @PostMapping(ConsoleCrossPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<ConsoleCrossPageReply> page(ConsoleCrossPageRequest request) {
    return Result.bad();
  }

  @PostMapping(ConsoleCrossQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<ConsoleCrossQueryReply> query(ConsoleCrossQueryRequest request) {
    return Result.bad();
  }
}
