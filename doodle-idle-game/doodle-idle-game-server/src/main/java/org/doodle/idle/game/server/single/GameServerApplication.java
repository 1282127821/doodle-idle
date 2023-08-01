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
package org.doodle.idle.game.server.single;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.module.annotation.OnStart;
import org.doodle.idle.framework.module.annotation.OnStop;
import org.doodle.idle.framework.module.reactive.RoleModuleOperationHandler;
import org.doodle.idle.framework.module.reactive.ServerModuleOperationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GameServerApplication implements CommandLineRunner {

  @Autowired ServerModuleOperationHandler serverOperationHandler;
  @Autowired RoleModuleOperationHandler roleOperationHandler;

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(
        (t, e) -> log.error("线程({})发生未捕获异常: {}", t.getName(), e.getMessage(), e));

    SpringApplication.run(GameServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Executors.newSingleThreadScheduledExecutor()
        .scheduleWithFixedDelay(
            () -> {
              serverOperationHandler.handleAnnotation(OnStart.class).block();
              serverOperationHandler.handleAnnotation(OnStop.class).block();

              roleOperationHandler.handleAnnotation(OnStart.class).block();
              roleOperationHandler.handleAnnotation(OnStop.class).block();
            },
            10,
            10,
            TimeUnit.MICROSECONDS);

    Thread.currentThread().join();
  }
}
