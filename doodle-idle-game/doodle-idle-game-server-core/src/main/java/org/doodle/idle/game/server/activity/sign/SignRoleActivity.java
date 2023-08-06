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
import org.doodle.idle.framework.activity.annotation.RoleActivity;
import org.doodle.idle.framework.lifecycle.annotation.OnPatch;
import org.doodle.idle.game.server.GameRoleContext;

/**
 * 签到玩家活动
 *
 * @author tingyanshen
 */
@Slf4j
@RoleActivity
public class SignRoleActivity<R extends GameRoleContext> {

  @OnPatch
  public void onPatch(R role) {
    log.info("OnPatch: sign-role-activity");
  }
}
