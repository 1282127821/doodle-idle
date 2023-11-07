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
package org.doodle.idle.game.server.module.activity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.activity.ActivityRegistry;
import org.doodle.idle.framework.lifecycle.annotation.*;
import org.doodle.idle.framework.module.annotation.*;
import org.doodle.idle.framework.timer.annotation.*;
import org.doodle.idle.game.server.GameServerContext;
import org.springframework.messaging.support.MessageHeaderInitializer;
import reactor.core.publisher.Mono;

/**
 * 活动服务模块
 *
 * @author tingyanshen
 */
@Slf4j
@ServerModule
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public class ActivityServerModule<S extends GameServerContext> extends ActivityRegistry {
  OperationRequester requester;

  protected MessageHeaderInitializer createHeaders(S server) {
    return headerAccessor ->
        headerAccessor.setHeader(GameServerContext.GAME_SERVER_CONTEXT, server);
  }

  @OnPrepare
  public Mono<Void> onPrepare(S server) {
    return this.requester
        .annotation(OnPrepare.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnPatch
  public Mono<Void> onPatch(S server) {
    return this.requester
        .annotation(OnPatch.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnStart
  public Mono<Void> onStart(S server) {
    return this.requester
        .annotation(OnStart.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnStop
  public Mono<Void> onStop(S server) {
    return this.requester
        .annotation(OnStop.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
  }

  @OnSave
  public Mono<Void> onSave(S server) {
    return this.requester
        .annotation(OnSave.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder();
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
