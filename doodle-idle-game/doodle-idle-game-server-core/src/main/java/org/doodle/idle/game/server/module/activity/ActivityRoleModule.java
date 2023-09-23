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
import org.doodle.idle.framework.lifecycle.annotation.OnPrepare;
import org.doodle.idle.framework.lifecycle.annotation.OnSave;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;
import org.doodle.idle.framework.module.annotation.RoleModule;
import org.doodle.idle.framework.timer.annotation.*;
import org.doodle.idle.game.server.GameRoleContext;
import org.springframework.messaging.support.MessageHeaderInitializer;
import reactor.core.publisher.Mono;

/**
 * 玩家活动模块
 *
 * @author tingyanshen
 */
@Slf4j
@RoleModule
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public class ActivityRoleModule<R extends GameRoleContext> extends ActivityRegistry {
  OperationRequester requester;

  protected MessageHeaderInitializer createHeaders(R role) {
    return headerAccessor -> headerAccessor.setHeader(GameRoleContext.GAME_ROLE_CONTEXT, role);
  }

  @OnStart
  public Mono<Void> onStart(R role) {
    MessageHeaderInitializer headers = createHeaders(role);
    return Mono.when(
        this.requester
            .annotation(OnPrepare.class)
            .handlers(getActivities())
            .header(headers)
            .naturalOrder(),
        this.requester
            .annotation(OnStart.class)
            .handlers(getActivities())
            .header(headers)
            .naturalOrder());
  }

  @OnStop
  public Mono<Void> onStop(R role) {
    return this.requester
        .annotation(OnStop.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }

  @OnSave
  public Mono<Void> onSave(R role) {
    return this.requester
        .annotation(OnSave.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }

  @OnOneIteration
  public Mono<Void> onOneIteration(R role) {
    return this.requester
        .annotation(OnOneIteration.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }

  @OnDayElapse
  public Mono<Void> onDayElapse(R role) {
    return this.requester
        .annotation(OnDayElapse.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }

  @OnWeekElapse
  public Mono<Void> onWeekElapse(R role) {
    return this.requester
        .annotation(OnWeekElapse.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }

  @OnMonthElapse
  public Mono<Void> onMonthElapse(R role) {
    return this.requester
        .annotation(OnMonthElapse.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }

  @OnYearElapse
  public Mono<Void> onYearElapse(R role) {
    return this.requester
        .annotation(OnYearElapse.class)
        .handlers(getActivities())
        .header(createHeaders(role))
        .naturalOrder();
  }
}
