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

import org.doodle.admin.autoconfigure.client.AdminClientAutoConfiguration;
import org.doodle.broker.autoconfigure.client.BrokerClientAutoConfiguration;
import org.doodle.broker.client.BrokerClientRSocketRequester;
import org.doodle.config.autoconfigure.client.ConfigClientAutoConfiguration;
import org.doodle.console.autoconfigure.client.ConsoleClientAutoConfiguration;
import org.doodle.excel.autoconfigure.client.ExcelClientAutoConfiguration;
import org.doodle.idle.autoconfigure.game.server.module.*;
import org.doodle.idle.framework.module.ModuleRegistry;
import org.doodle.idle.framework.module.reactive.ModuleOperationHandler;
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
import org.springframework.core.MethodParameter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.invocation.reactive.HandlerMethodReturnValueHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.SimpleRouteMatcher;
import reactor.core.publisher.Mono;

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
  public ModuleRegistry<Object> moduleRegistry() {
    return new ModuleRegistry<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public ModuleOperationHandler moduleOperationHandler(ModuleRegistry<Object> registry) {
    ModuleOperationHandler operationHandler = new ModuleOperationHandler(registry::getModules);
    operationHandler.setRouteMatcher(new SimpleRouteMatcher(new AntPathMatcher()));
    operationHandler
        .getReturnValueHandlerConfigurer()
        .addCustomHandler(
            new HandlerMethodReturnValueHandler() {
              @Override
              public boolean supportsReturnType(MethodParameter returnType) {
                return true;
              }

              @Override
              public Mono<Void> handleReturnValue(
                  Object returnValue, MethodParameter returnType, Message<?> message) {
                return Mono.empty();
              }
            });
    return operationHandler;
  }
}
