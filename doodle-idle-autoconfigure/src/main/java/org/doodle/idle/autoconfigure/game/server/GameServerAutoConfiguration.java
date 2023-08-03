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
package org.doodle.idle.autoconfigure.game.server;

import java.util.stream.Collectors;
import org.doodle.admin.autoconfigure.client.AdminClientAutoConfiguration;
import org.doodle.broker.autoconfigure.client.BrokerClientAutoConfiguration;
import org.doodle.broker.client.BrokerClientRSocketRequester;
import org.doodle.config.autoconfigure.client.ConfigClientAutoConfiguration;
import org.doodle.console.autoconfigure.client.ConsoleClientAutoConfiguration;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.excel.autoconfigure.client.ExcelClientAutoConfiguration;
import org.doodle.idle.autoconfigure.game.server.module.*;
import org.doodle.idle.framework.module.RoleModuleRegistry;
import org.doodle.idle.framework.module.ServerModuleRegistry;
import org.doodle.idle.framework.module.reactive.RoleModuleOperationHandler;
import org.doodle.idle.framework.module.reactive.ServerModuleOperationHandler;
import org.doodle.idle.game.server.GameServerContext;
import org.doodle.idle.game.server.GameServerProperties;
import org.doodle.login.autoconfigure.client.LoginClientAutoConfiguration;
import org.doodle.payment.autoconfigure.client.PaymentClientAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration(
    after = {
      BrokerClientAutoConfiguration.class,
      ConfigClientAutoConfiguration.class,
      ConsoleClientAutoConfiguration.class,
      LoginClientAutoConfiguration.class,
      PaymentClientAutoConfiguration.class,
      AdminClientAutoConfiguration.class,
      ConsoleClientAutoConfiguration.class,
      ExcelClientAutoConfiguration.class
    })
@ConditionalOnClass(GameServerProperties.class)
@ConditionalOnBean(BrokerClientRSocketRequester.class)
@EnableConfigurationProperties(GameServerProperties.class)
@Import({
  BagModuleConfiguration.class,
  FightModuleConfiguration.class,
  LoginModuleConfiguration.class,
  MailModuleConfiguration.class,
  PaymentModuleConfiguration.class,
  RoleModuleConfiguration.class,
  TaskModuleConfiguration.class
})
public class GameServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ServerModuleRegistry<Object> serverModuleRegistry() {
    return new ServerModuleRegistry<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public ServerModuleOperationHandler serverModuleOperationHandler() {
    return new ServerModuleOperationHandler();
  }

  @Bean
  @ConditionalOnMissingBean
  public RoleModuleRegistry<Object> roleModuleRegistry() {
    return new RoleModuleRegistry<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public RoleModuleOperationHandler roleModuleOperationHandler() {
    return new RoleModuleOperationHandler();
  }

  @Bean
  @ConditionalOnMissingBean
  public GameServerContext gameServerContext(
      ServerModuleOperationHandler operationHandler, ServerModuleRegistry<Object> registry) {
    return new GameServerContext(
        new OperationRequester(operationHandler),
        registry.getModules().stream().map(Object::getClass).collect(Collectors.toList()));
  }
}
