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
import org.doodle.design.idle.console.ConsoleEcsPageOps;
import org.doodle.design.idle.console.ConsoleEcsQueryOps;
import org.doodle.design.idle.console.model.info.ConsoleEcsInfo;
import org.doodle.design.idle.console.model.payload.reply.ConsoleEcsPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleEcsQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleEcsPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleEcsQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerEcsServletController
    implements ConsoleEcsQueryOps.Servlet, ConsoleEcsPageOps.Servlet {
  ConsoleServerEcsService ecsService;

  @PostMapping(ConsoleEcsQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<ConsoleEcsPageReply> page(ConsoleEcsPageRequest request) {
    List<ConsoleEcsInfo> ecsInfos = ecsService.page(request.getPage());
    ConsoleEcsPageReply reply = ConsoleEcsPageReply.builder().ecsInfos(ecsInfos).build();
    return Result.ok(reply);
  }

  @PostMapping(ConsoleEcsPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<ConsoleEcsQueryReply> query(ConsoleEcsQueryRequest request) {
    ConsoleEcsInfo ecsInfo = ecsService.query(request.getUniqueId());
    ConsoleEcsQueryReply reply = ConsoleEcsQueryReply.builder().ecsInfo(ecsInfo).build();
    return Result.ok(reply);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<Result<Void>> onException(Exception ignored) {
    return ResponseEntity.badRequest().body(Result.bad());
  }
}
