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
package org.doodle.idle.cross.server;

import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.context.BaseContext;
import org.springframework.messaging.support.MessageHeaderAccessor;
import reactor.core.publisher.Mono;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrossServerContext extends BaseContext {
  public static final String CROSS_SERVER_CONTEXT = "CROSS_SERVER_CONTEXT";

  public CrossServerContext(OperationRequester requester, List<Object> handlers) {
    super(requester, handlers);
  }

  @Override
  protected void initHeaders(MessageHeaderAccessor headerAccessor) {
    headerAccessor.setHeader(CROSS_SERVER_CONTEXT, this);
  }

  @Override
  public Mono<Void> prepare() {
    return super.prepare().doFirst(() -> log.info(">>> 跨服 [准备]"));
  }

  @Override
  public Mono<Void> patch() {
    return super.patch().doFirst(() -> log.info(">>> 跨服 [补丁]"));
  }

  @Override
  public Mono<Void> oneIteration() {
    return super.oneIteration().doFirst(() -> log.info(">>> 跨服 [TICK]"));
  }

  @Override
  public Mono<Void> start() {
    return super.start().doFirst(() -> log.info(">>> 跨服 [启动]"));
  }

  @Override
  public Mono<Void> stop() {
    return super.stop().doFirst(() -> log.info(">>> 跨服 [关闭]"));
  }

  @Override
  public Mono<Void> save() {
    return super.save().doFirst(() -> log.info(">>> 跨服 [保存]"));
  }

  @Override
  public Mono<Void> dayElapse() {
    return super.dayElapse().doFirst(() -> log.info(">>> 跨服 [跨天]"));
  }

  @Override
  public Mono<Void> weekElapse() {
    return super.weekElapse().doFirst(() -> log.info(">>> 跨服 [跨周]"));
  }

  @Override
  public Mono<Void> monthElapse() {
    return super.monthElapse().doFirst(() -> log.info(">>> 跨服 [跨月]"));
  }

  @Override
  public Mono<Void> yearElapse() {
    return super.yearElapse().doFirst(() -> log.info(">>> 跨服 [跨年]"));
  }
}
