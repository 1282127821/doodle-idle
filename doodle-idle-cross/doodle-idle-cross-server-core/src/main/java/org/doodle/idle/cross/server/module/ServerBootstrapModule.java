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

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.cross.server.CrossServerContext;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;
import org.doodle.idle.framework.module.ModuleRegistry;
import org.doodle.idle.framework.module.annotation.ServerModule;

@Slf4j
@ServerModule
public class ServerBootstrapModule<S extends CrossServerContext> extends ModuleRegistry {

  @OnStart
  public void onStart(S s) {
    log.info("启动: 启动服务模块");
  }

  @OnStop
  public void onStop(S s) {
    log.info("关闭: 关闭服务模块");
  }
}
