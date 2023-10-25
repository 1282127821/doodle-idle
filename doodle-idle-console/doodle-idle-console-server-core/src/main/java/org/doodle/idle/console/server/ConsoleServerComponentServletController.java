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
import org.doodle.design.idle.console.ConsoleComponentPageOps;
import org.doodle.design.idle.console.ConsoleComponentQueryOps;
import org.doodle.design.idle.console.model.info.ConsoleComponentInfo;
import org.doodle.design.idle.console.model.payload.reply.ConsoleComponentPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleComponentQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleComponentPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleComponentQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerComponentServletController
    implements ConsoleComponentQueryOps.Servlet, ConsoleComponentPageOps.Servlet {
  ConsoleServerComponentService componentService;

  @PostMapping(ConsoleComponentPageOps.Servlet.PAGE_MAPPING)
  @Override
  public Result<ConsoleComponentPageReply> page(ConsoleComponentPageRequest request) {
    List<ConsoleComponentInfo> componentInfos = componentService.page(request.getPage());
    ConsoleComponentPageReply reply =
        ConsoleComponentPageReply.builder().componentInfos(componentInfos).build();
    return Result.ok(reply);
  }

  @PostMapping(ConsoleComponentQueryOps.Servlet.QUERY_MAPPING)
  @Override
  public Result<ConsoleComponentQueryReply> query(ConsoleComponentQueryRequest request) {
    ConsoleComponentInfo componentInfo = componentService.query(request.getUniqueId());
    ConsoleComponentQueryReply reply =
        ConsoleComponentQueryReply.builder().componentInfo(componentInfo).build();
    return Result.ok(reply);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<Result<Void>> onException(Exception ignored) {
    return ResponseEntity.badRequest().body(Result.bad());
  }
}
