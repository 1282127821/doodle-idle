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
package org.doodle.idle.console.server.single;

import java.util.Optional;
import org.doodle.design.idle.console.ConsoleArchiveType;
import org.doodle.design.idle.console.model.info.ConsoleArchiveInfo;
import org.doodle.idle.console.server.ConsoleServerGameEntity;
import org.doodle.idle.console.server.ConsoleServerGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleServerApplication implements CommandLineRunner {
  @Autowired ConsoleServerGameRepo gameRepo;

  public static void main(String[] args) {
    SpringApplication.run(ConsoleServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    gameRepo.save(
        ConsoleServerGameEntity.builder()
            .archiveInfo(
                ConsoleArchiveInfo.builder()
                    .type(ConsoleArchiveType.COMPONENT)
                    .category("JG")
                    .build())
            .build());

    gameRepo.save(
        ConsoleServerGameEntity.builder()
            .archiveInfo(
                ConsoleArchiveInfo.builder()
                    .type(ConsoleArchiveType.COMPONENT)
                    .category("YZX")
                    .build())
            .build());

    gameRepo.save(
        ConsoleServerGameEntity.builder()
            .archiveInfo(
                ConsoleArchiveInfo.builder()
                    .type(ConsoleArchiveType.COMPONENT)
                    .category("JG")
                    .build())
            .build());

    gameRepo.save(
        ConsoleServerGameEntity.builder()
            .archiveInfo(
                ConsoleArchiveInfo.builder()
                    .type(ConsoleArchiveType.COMPONENT)
                    .category("YZX")
                    .build())
            .build());

    Optional<ConsoleServerGameEntity> found = gameRepo.findByArchiveInfoUniqueId(1);
    System.out.println(found.orElse(null));
  }
}
