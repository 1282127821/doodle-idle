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
package org.doodle.idle.game.server.activity.sign;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.activity.annotation.ServerActivity;
import org.doodle.idle.framework.lifecycle.annotation.OnPrepare;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;

/**
 * 签到服务活动
 *
 * @author tingyanshen
 */
@Slf4j
@ServerActivity
public class SignServerActivity {

  @OnPrepare
  public void onPrepare() {
    log.info("OnPrepare: sign-server-activity");
  }

  @OnStart
  public void onStart() {
    log.info("OnStart: sign-server-activity");
  }

  @OnStop
  public void onStop() {
    log.info("OnStop: sign-server-activity");
  }
}
