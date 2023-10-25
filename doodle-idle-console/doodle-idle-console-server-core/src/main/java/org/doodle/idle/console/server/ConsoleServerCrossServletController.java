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

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.doodle.design.common.Result;
import org.doodle.design.idle.console.ConsoleCrossPageOps;
import org.doodle.design.idle.console.ConsoleCrossQueryOps;
import org.doodle.design.idle.console.model.info.ConsoleCrossInfo;
import org.doodle.design.idle.console.model.payload.reply.ConsoleCrossPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleCrossQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleCrossPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleCrossQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerCrossServletController
    implements ConsoleCrossQueryOps.Servlet, ConsoleCrossPageOps.Servlet {
  ConsoleServerCrossService crossService;

  @PostMapping(ConsoleCrossPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<ConsoleCrossPageReply> page(ConsoleCrossPageRequest request) {
    List<ConsoleCrossInfo> crossInfos = crossService.page(request.getPage());
    ConsoleCrossPageReply reply = ConsoleCrossPageReply.builder().crossInfos(crossInfos).build();
    return Result.ok(reply);
  }

  @PostMapping(ConsoleCrossQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<ConsoleCrossQueryReply> query(ConsoleCrossQueryRequest request) {
    ConsoleCrossInfo crossInfo = crossService.query(request.getUniqueId());
    ConsoleCrossQueryReply reply = ConsoleCrossQueryReply.builder().crossInfo(crossInfo).build();
    return Result.ok(reply);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<Result<Void>> onException(Exception ignored) {
    return ResponseEntity.badRequest().body(Result.bad());
  }
}
