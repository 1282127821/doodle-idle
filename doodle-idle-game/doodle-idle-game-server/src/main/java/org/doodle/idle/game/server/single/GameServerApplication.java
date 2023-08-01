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

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.module.ModuleOperationHandler;
import org.doodle.idle.framework.operation.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GameServerApplication implements CommandLineRunner {

  @Autowired ModuleOperationHandler messageHandler;

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(
        (t, e) -> log.error("线程({})发生未捕获异常: {}", t.getName(), e.getMessage(), e));

    SpringApplication.run(GameServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    messageHandler.handleMessage(OperationType.START);
    messageHandler.handleMessage(OperationType.DAY_ELAPSE);
  }
}
