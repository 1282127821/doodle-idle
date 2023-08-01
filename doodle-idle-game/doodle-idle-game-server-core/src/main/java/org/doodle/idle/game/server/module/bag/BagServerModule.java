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
package org.doodle.idle.game.server.module.bag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.module.annotation.ModuleExceptionHandler;
import org.doodle.idle.framework.module.annotation.OnStart;
import org.doodle.idle.framework.module.annotation.OnStop;
import org.doodle.idle.framework.module.annotation.ServerModule;
import org.doodle.idle.game.server.module.fight.FightServerModule;

/**
 * 服务背包模块
 *
 * @author tingyanshen
 */
@Slf4j
@RequiredArgsConstructor
@ServerModule
public class BagServerModule {
  private final FightServerModule fightServerModule;

  @OnStart
  public void onStart() throws InterruptedException {
    log.info("onStart: bag-module");
    Thread.sleep(10000);
    //    throw new RuntimeException("测试"); // 测试
  }

  @OnStop
  public void onStop() {
    log.info("onStop: bag-module");
  }

  @ModuleExceptionHandler(Exception.class)
  public void handleException(Exception e) {
    log.error("未知错误", e);
  }
}
