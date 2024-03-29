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
package org.doodle.idle.game.server.module.task;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.lifecycle.annotation.OnPatch;
import org.doodle.idle.framework.lifecycle.annotation.OnPrepare;
import org.doodle.idle.framework.module.annotation.ModuleExceptionHandler;
import org.doodle.idle.framework.module.annotation.ServerModule;
import org.doodle.idle.game.server.GameServerContext;

/**
 * 任务服务模块
 *
 * @author tingyanshen
 */
@Slf4j
@ServerModule
public class TaskServerModule<S extends GameServerContext> {

  @OnPrepare
  public void onPrepare(S server) {
    log.info("任务模块 [服务]");
    //    throw new RuntimeException();
  }

  @OnPatch
  public void onPatch(S server) {
    log.info("任务模块 [服务]");
  }

  @ModuleExceptionHandler(Exception.class)
  public void onException(Exception e) {
    log.info("任务模块 [服务]", e);
  }
}
