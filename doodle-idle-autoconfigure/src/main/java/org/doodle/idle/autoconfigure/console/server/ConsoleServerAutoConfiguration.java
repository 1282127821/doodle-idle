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
package org.doodle.idle.autoconfigure.console.server;

import org.doodle.broker.autoconfigure.client.BrokerClientAutoConfiguration;
import org.doodle.idle.console.server.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@AutoConfiguration(after = BrokerClientAutoConfiguration.class)
@ConditionalOnClass(ConsoleServerProperties.class)
@EnableConfigurationProperties(ConsoleServerProperties.class)
@EnableMongoAuditing
@EnableMongoRepositories(
    basePackageClasses = {
      ConsoleServerComponentRepo.class,
      ConsoleServerCrossRepo.class,
      ConsoleServerDbRepo.class,
      ConsoleServerEcsRepo.class,
      ConsoleServerGameRepo.class
    })
public class ConsoleServerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerMapper consoleServerMapper() {
    return new ConsoleServerMapper();
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerComponentService consoleServerComponentService(
      MongoTemplate mongoTemplate,
      ConsoleServerMapper mapper,
      ConsoleServerComponentRepo componentRepo) {
    return new ConsoleServerComponentService(mongoTemplate, mapper, componentRepo);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerComponentListener consoleServerComponentListener(
      ConsoleServerComponentService componentService) {
    return new ConsoleServerComponentListener(componentService);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerCrossService consoleServerCrossService(
      MongoTemplate mongoTemplate, ConsoleServerMapper mapper, ConsoleServerCrossRepo crossRepo) {
    return new ConsoleServerCrossService(mongoTemplate, mapper, crossRepo);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerCrossListener consoleServerCrossListener(
      ConsoleServerCrossService crossService) {
    return new ConsoleServerCrossListener(crossService);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerDbService consoleServerDbService(
      MongoTemplate mongoTemplate, ConsoleServerMapper mapper, ConsoleServerDbRepo dbRepo) {
    return new ConsoleServerDbService(mongoTemplate, mapper, dbRepo);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerDbListener consoleServerDbListener(ConsoleServerDbService dbService) {
    return new ConsoleServerDbListener(dbService);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerEcsService consoleServerEcsService(
      MongoTemplate mongoTemplate, ConsoleServerMapper mapper, ConsoleServerEcsRepo ecsRepo) {
    return new ConsoleServerEcsService(mongoTemplate, mapper, ecsRepo);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerEcsListener consoleServerEcsListener(ConsoleServerEcsService ecsService) {
    return new ConsoleServerEcsListener(ecsService);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerGameService consoleServerGameService(
      MongoTemplate mongoTemplate, ConsoleServerMapper mapper, ConsoleServerGameRepo gameRepo) {
    return new ConsoleServerGameService(mongoTemplate, mapper, gameRepo);
  }

  @Bean
  @ConditionalOnMissingBean
  public ConsoleServerGameListener consoleServerGameListener(ConsoleServerGameService gameService) {
    return new ConsoleServerGameListener(gameService);
  }

  @AutoConfiguration
  @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
  public static class ServletConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerComponentServletController consoleServerComponentServletController(
        ConsoleServerComponentService componentService) {
      return new ConsoleServerComponentServletController(componentService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerCrossServletController consoleServerCrossServletController(
        ConsoleServerCrossService crossService) {
      return new ConsoleServerCrossServletController(crossService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerDbServletController consoleServerDbServletController(
        ConsoleServerDbService dbService) {
      return new ConsoleServerDbServletController(dbService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerEcsServletController consoleServerEcsServletController(
        ConsoleServerEcsService ecsService) {
      return new ConsoleServerEcsServletController(ecsService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerGameServletController consoleServerGameServletController(
        ConsoleServerGameService gameService) {
      return new ConsoleServerGameServletController(gameService);
    }
  }

  @AutoConfiguration
  public static class RSocketConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerComponentRSocketController consoleServerComponentRSocketController(
        ConsoleServerMapper mapper, ConsoleServerComponentService componentService) {
      return new ConsoleServerComponentRSocketController(mapper, componentService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerCrossRSocketController consoleServerCrossRSocketController(
        ConsoleServerMapper mapper, ConsoleServerCrossService crossService) {
      return new ConsoleServerCrossRSocketController(mapper, crossService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerDbRSocketController consoleServerDbRSocketController(
        ConsoleServerMapper mapper, ConsoleServerDbService dbService) {
      return new ConsoleServerDbRSocketController(mapper, dbService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerEcsRSocketController consoleServerEcsRSocketController(
        ConsoleServerMapper mapper, ConsoleServerEcsService ecsService) {
      return new ConsoleServerEcsRSocketController(mapper, ecsService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsoleServerGameRSocketController consoleServerGameRSocketController(
        ConsoleServerMapper mapper, ConsoleServerGameService gameService) {
      return new ConsoleServerGameRSocketController(mapper, gameService);
    }
  }
}
