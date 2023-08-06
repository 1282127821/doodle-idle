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

/**
 * 活动服务模块
 *
 * @author tingyanshen
 */
@Slf4j
@ServerModule
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public class ActivityServerModule extends ActivityRegistry {
  OperationRequester requester;

  @OnStart
  public void onStart() {
    this.requester.annotation(OnPrepare.class).handlers(getActivities()).naturalOrder().block();
    this.requester.annotation(OnStart.class).handlers(getActivities()).naturalOrder().block();
  }

  @OnStop
  public void onStop() {
    this.requester.annotation(OnStop.class).handlers(getActivities()).naturalOrder().block();
  }

  @OnSave
  public void onSave() {
    this.requester.annotation(OnSave.class).handlers(getActivities()).naturalOrder().block();
  }

  @OnOneIteration
  public void onOneIteration() {
    this.requester
        .annotation(OnOneIteration.class)
        .handlers(getActivities())
        .naturalOrder()
        .block();
  }

  @OnDayElapse
  public void onDayElapse() {
    this.requester.annotation(OnDayElapse.class).handlers(getActivities()).naturalOrder().block();
  }

  @OnWeekElapse
  public void onWeekElapse() {
    this.requester.annotation(OnWeekElapse.class).handlers(getActivities()).naturalOrder().block();
  }

  @OnMonthElapse
  public void onMonthElapse() {
    this.requester.annotation(OnMonthElapse.class).handlers(getActivities()).naturalOrder().block();
  }

  @OnYearElapse
  public void onYearElapse() {
    this.requester.annotation(OnYearElapse.class).handlers(getActivities()).naturalOrder().block();
  }
}
