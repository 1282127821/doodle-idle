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
package org.doodle.idle.game.server.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;
import org.doodle.idle.framework.module.ModuleRegistry;
import org.doodle.idle.framework.module.annotation.RoleModule;
import org.doodle.idle.game.server.GameRoleContext;

/**
 * 玩家角色启动模块
 *
 * @author tingyanshen
 */
@Slf4j
@RoleModule
public class RoleBootstrapModule<R extends GameRoleContext> extends ModuleRegistry {
  public RoleBootstrapModule() {
    add(this);
  }

  @OnStart
  public void onStart(R role) {
    log.info("OnStart: role-bootstrap-module");
  }

  @OnStop
  public void onStop(R role) {
    log.info("OnStop: role-bootstrap-module");
  }
}
