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
import org.doodle.idle.framework.module.annotation.*;
import org.doodle.idle.framework.timer.annotation.*;
import org.doodle.idle.game.server.GameServerContext;
import org.springframework.messaging.support.MessageHeaderInitializer;

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

  @OnStart
  public void onStart(S server) {
    MessageHeaderInitializer headers = createHeaders(server);
    this.requester
        .annotation(OnPrepare.class)
        .handlers(getActivities())
        .header(headers)
        .naturalOrder()
        .block();
    this.requester
        .annotation(OnStart.class)
        .handlers(getActivities())
        .header(headers)
        .naturalOrder()
        .block();
  }

  @OnStop
  public void onStop(S server) {
    this.requester
        .annotation(OnStop.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }

  @OnSave
  public void onSave(S server) {
    this.requester
        .annotation(OnSave.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }

  @OnOneIteration
  public void onOneIteration(S server) {
    this.requester
        .annotation(OnOneIteration.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }

  @OnDayElapse
  public void onDayElapse(S server) {
    this.requester
        .annotation(OnDayElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }

  @OnWeekElapse
  public void onWeekElapse(S server) {
    this.requester
        .annotation(OnWeekElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }

  @OnMonthElapse
  public void onMonthElapse(S server) {
    this.requester
        .annotation(OnMonthElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }

  @OnYearElapse
  public void onYearElapse(S server) {
    this.requester
        .annotation(OnYearElapse.class)
        .handlers(getActivities())
        .header(createHeaders(server))
        .naturalOrder()
        .block();
  }
}
