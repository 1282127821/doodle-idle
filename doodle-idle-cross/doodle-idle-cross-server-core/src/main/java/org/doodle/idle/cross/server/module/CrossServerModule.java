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
package org.doodle.idle.cross.server.module;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.cross.server.CrossServerContext;
import org.doodle.idle.framework.cross.CrossRegistry;
import org.doodle.idle.framework.lifecycle.annotation.OnPrepare;
import org.doodle.idle.framework.lifecycle.annotation.OnSave;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;
import org.doodle.idle.framework.module.annotation.*;
import org.doodle.idle.framework.timer.annotation.*;
import org.springframework.messaging.support.MessageHeaderInitializer;
import reactor.core.publisher.Mono;

/**
 * 跨服服务模块
 *
 * @author tingyanshen
 */
@Slf4j
@ServerModule
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public class CrossServerModule<S extends CrossServerContext> extends CrossRegistry {
  OperationRequester requester;

  protected MessageHeaderInitializer createHeaders(S server) {
    return headerAccessor ->
        headerAccessor.setHeader(CrossServerContext.CROSS_SERVER_CONTEXT, server);
  }

  @OnStart
  public Mono<Void> onStart(S server) {
    MessageHeaderInitializer headers = createHeaders(server);
    return Mono.when(
            this.requester
                .annotation(OnPrepare.class)
                .handlers(getActivities())
                .header(headers)
                .naturalOrder()
                .doFirst(() -> log.info("准备: 服务跨服")),
            this.requester
                .annotation(OnStart.class)
                .handlers(getActivities())
                .header(headers)
                .naturalOrder()
                .doFirst(() -> log.info("启动: 服务跨服")))
        .doFirst(() -> log.info("启动: 跨服服务模块"));
  }

  @OnStop
  public Mono<Void> onStop(S server) {
    return this.requester
        .annotation(OnStop.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .doFirst(() -> log.info("关闭: 跨服服务模块"));
  }

  @OnSave
  public Mono<Void> onSave(S server) {
    return this.requester
        .annotation(OnSave.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .doFirst(() -> log.info("保存: 跨服服务模块"));
  }

  @OnOneIteration
  public Mono<Void> onOneIteration(S server) {
    return this.requester
        .annotation(OnOneIteration.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnDayElapse
  public Mono<Void> onDayElapse(S server) {
    return this.requester
        .annotation(OnDayElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnWeekElapse
  public Mono<Void> onWeekElapse(S server) {
    return this.requester
        .annotation(OnWeekElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnMonthElapse
  public Mono<Void> onMonthElapse(S server) {
    return this.requester
        .annotation(OnMonthElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnYearElapse
  public Mono<Void> onYearElapse(S server) {
    return this.requester
        .annotation(OnYearElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }
}
