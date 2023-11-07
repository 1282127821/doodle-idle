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
package org.doodle.idle.cross.server.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.cross.server.CrossServerContext;
import org.doodle.idle.framework.lifecycle.annotation.OnPrepare;
import org.doodle.idle.framework.lifecycle.annotation.OnSave;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;
import org.doodle.idle.framework.module.ModuleRegistry;
import org.doodle.idle.framework.module.annotation.ServerModule;

@Slf4j
@ServerModule
public class ServerBootstrapModule<S extends CrossServerContext> extends ModuleRegistry {

  public ServerBootstrapModule() {
    add(this);
  }

  @OnPrepare
  public void onPrepare(S server) {
    log.info("准备: 在所有模块之前，适合执行驱动模块准备");
  }

  @OnStart
  public void onStart(S server) {
    log.info("启动: 在所有模块之后, 适合执行驱动模块启动");
  }

  @OnStop
  public void onStop(S server) {
    log.info("关闭: 在所有模块之前， 适合执行驱动模块关闭");
  }

  @OnSave
  public void onSave(S server) {
    log.info("保存: 在所有模块之后，适合执行驱动模块保存");
  }
}
