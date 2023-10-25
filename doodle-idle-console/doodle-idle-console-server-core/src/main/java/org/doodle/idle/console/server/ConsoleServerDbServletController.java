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
import org.doodle.design.idle.console.ConsoleDbPageOps;
import org.doodle.design.idle.console.ConsoleDbQueryOps;
import org.doodle.design.idle.console.model.info.ConsoleDbInfo;
import org.doodle.design.idle.console.model.payload.reply.ConsoleDbPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleDbQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleDbPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleDbQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerDbServletController
    implements ConsoleDbQueryOps.Servlet, ConsoleDbPageOps.Servlet {
  ConsoleServerDbService dbService;

  @PostMapping(ConsoleDbPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<ConsoleDbPageReply> page(ConsoleDbPageRequest request) {
    List<ConsoleDbInfo> dbInfos = dbService.page(request.getPage());
    ConsoleDbPageReply reply = ConsoleDbPageReply.builder().dbInfos(dbInfos).build();
    return Result.ok(reply);
  }

  @PostMapping(ConsoleDbQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<ConsoleDbQueryReply> query(ConsoleDbQueryRequest request) {
    ConsoleDbInfo dbInfo = dbService.query(request.getUniqueId());
    ConsoleDbQueryReply reply = ConsoleDbQueryReply.builder().dbInfo(dbInfo).build();
    return Result.ok(reply);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<Result<Void>> onException(Exception ignored) {
    return ResponseEntity.badRequest().body(Result.bad());
  }
}
