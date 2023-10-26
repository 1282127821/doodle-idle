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
package org.doodle.idle.autoconfigure.cross.server;

import org.doodle.broker.autoconfigure.client.BrokerClientAutoConfiguration;
import org.doodle.config.autoconfigure.client.ConfigClientAutoConfiguration;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.excel.autoconfigure.client.ExcelClientAutoConfiguration;
import org.doodle.idle.cross.server.CrossServerBootstrap;
import org.doodle.idle.cross.server.CrossServerContext;
import org.doodle.idle.cross.server.CrossServerProperties;
import org.doodle.idle.cross.server.module.ServerBootstrapModule;
import org.doodle.idle.cross.server.support.CrossServerContextMethodArgumentResolver;
import org.doodle.idle.framework.module.reactive.ServerModuleOperationHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(
    after = {
      BrokerClientAutoConfiguration.class,
      ConfigClientAutoConfiguration.class,
      ExcelClientAutoConfiguration.class
    })
@ConditionalOnClass(CrossServerProperties.class)
@EnableConfigurationProperties(CrossServerProperties.class)
public class CrossServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ServerBootstrapModule<? extends CrossServerContext> serverBootstrapModule() {
    return new ServerBootstrapModule<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public ServerModuleOperationHandler serverModuleOperationHandler() {
    ServerModuleOperationHandler operationHandler = new ServerModuleOperationHandler();
    operationHandler
        .getArgumentResolverConfigurer()
        .addCustomResolver(new CrossServerContextMethodArgumentResolver());
    return operationHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public CrossServerContext crossServerContext(
      ServerModuleOperationHandler operationHandler,
      ServerBootstrapModule<? extends CrossServerContext> bootstrapModule) {
    return new CrossServerContext(
        new OperationRequester(operationHandler), bootstrapModule.getModules());
  }

  @Bean
  @ConditionalOnMissingBean
  public CrossServerBootstrap gameServerBootstrap(CrossServerContext serverContext) {
    return new CrossServerBootstrap(serverContext);
  }
}
