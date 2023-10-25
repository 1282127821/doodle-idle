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

import java.util.Collections;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.doodle.design.common.Result;
import org.doodle.design.idle.console.*;
import org.doodle.design.idle.console.model.payload.reply.ConsoleComponentPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleComponentQueryReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleCrossPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleCrossQueryReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleDbPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleDbQueryReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleEcsPageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleEcsQueryReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleGamePageReply;
import org.doodle.design.idle.console.model.payload.reply.ConsoleGameQueryReply;
import org.doodle.design.idle.console.model.payload.request.ConsoleComponentPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleComponentQueryRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleCrossPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleCrossQueryRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleDbPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleDbQueryRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleEcsPageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleEcsQueryRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleGamePageRequest;
import org.doodle.design.idle.console.model.payload.request.ConsoleGameQueryRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleClientServletImpl implements ConsoleClientServlet {
  RestTemplate restTemplate;

  static final ParameterizedTypeReference<Result<ConsoleComponentPageReply>> COMPONENT_PAGE_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleComponentQueryReply>>
      COMPONENT_QUERY_REPLY = new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleCrossPageReply>> CROSS_PAGE_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleCrossQueryReply>> CROSS_QUERY_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleDbPageReply>> DB_PAGE_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleDbQueryReply>> DB_QUERY_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleEcsPageReply>> ECS_PAGE_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleEcsQueryReply>> ECS_QUERY_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleGamePageReply>> GAME_PAGE_REPLY =
      new ParameterizedTypeReference<>() {};

  static final ParameterizedTypeReference<Result<ConsoleGameQueryReply>> GAME_QUERY_REPLY =
      new ParameterizedTypeReference<>() {};

  @Override
  public Result<ConsoleComponentPageReply> page(ConsoleComponentPageRequest request) {
    return restTemplate
        .exchange(
            ConsoleComponentPageOps.Servlet.PAGE_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request),
            COMPONENT_PAGE_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleComponentQueryReply> query(ConsoleComponentQueryRequest request) {
    return restTemplate
        .exchange(
            ConsoleComponentQueryOps.Servlet.QUERY_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            COMPONENT_QUERY_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleCrossPageReply> page(ConsoleCrossPageRequest request) {
    return restTemplate
        .exchange(
            ConsoleCrossPageOps.Servlet.PAGE_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            CROSS_PAGE_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleCrossQueryReply> query(ConsoleCrossQueryRequest request) {
    return restTemplate
        .exchange(
            ConsoleCrossQueryOps.Servlet.QUERY_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            CROSS_QUERY_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleDbPageReply> page(ConsoleDbPageRequest request) {
    return restTemplate
        .exchange(
            ConsoleDbPageOps.Servlet.PAGE_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            DB_PAGE_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleDbQueryReply> query(ConsoleDbQueryRequest request) {
    return restTemplate
        .exchange(
            ConsoleDbQueryOps.Servlet.QUERY_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            DB_QUERY_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleEcsPageReply> page(ConsoleEcsPageRequest request) {
    return restTemplate
        .exchange(
            ConsoleEcsPageOps.Servlet.PAGE_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            ECS_PAGE_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleEcsQueryReply> query(ConsoleEcsQueryRequest request) {
    return restTemplate
        .exchange(
            ConsoleEcsQueryOps.Servlet.QUERY_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            ECS_QUERY_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleGamePageReply> page(ConsoleGamePageRequest request) {
    return restTemplate
        .exchange(
            ConsoleGamePageOps.Servlet.PAGE_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            GAME_PAGE_REPLY)
        .getBody();
  }

  @Override
  public Result<ConsoleGameQueryReply> query(ConsoleGameQueryRequest request) {
    return restTemplate
        .exchange(
            ConsoleGameQueryOps.Servlet.QUERY_MAPPING,
            HttpMethod.POST,
            new HttpEntity<>(request, createHttpHeaders()),
            GAME_QUERY_REPLY)
        .getBody();
  }

  protected HttpHeaders createHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return HttpHeaders.readOnlyHttpHeaders(headers);
  }
}
