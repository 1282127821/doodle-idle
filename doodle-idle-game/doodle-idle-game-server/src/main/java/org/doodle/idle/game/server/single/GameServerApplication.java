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

import com.google.common.collect.Lists;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.module.ServerModuleRegistry;
import org.doodle.idle.framework.module.annotation.OnStart;
import org.doodle.idle.framework.module.reactive.RoleModuleOperationHandler;
import org.doodle.idle.framework.module.reactive.ServerModuleOperationHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GameServerApplication implements CommandLineRunner {
  ServerModuleRegistry<Object> serverRegistry;
  OperationRequester serverOperationRequester;

  OperationRequester roleOperationRequester;

  public GameServerApplication(
      ServerModuleOperationHandler serverOperationHandler,
      ServerModuleRegistry<Object> serverRegistry,
      RoleModuleOperationHandler roleOperationHandler) {
    this.serverRegistry = serverRegistry;
    this.serverOperationRequester = new OperationRequester(serverOperationHandler);
    this.roleOperationRequester = new OperationRequester(roleOperationHandler);
  }

  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(
        (t, e) -> log.error("线程({})发生未捕获异常: {}", t.getName(), e.getMessage(), e));

    SpringApplication.run(GameServerApplication.class, args);
  }

  @Override
  public void run(String... args) {
    this.serverOperationRequester.annotation(OnStart.class).natureOrder().block();
    this.serverOperationRequester
        .annotation(OnStart.class)
        .handlers(
            Lists.reverse(serverRegistry.getModules()).stream()
                .map(Object::getClass)
                .collect(Collectors.toList()))
        .natureOrder()
        .block();
  }
}
