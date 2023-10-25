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
package org.doodle.idle.console.server;

import java.util.Objects;
import org.doodle.design.idle.console.ConsoleMapper;
import org.doodle.design.idle.console.model.info.*;

public class ConsoleServerMapper extends ConsoleMapper {
  public ConsoleComponentInfo toPojo(ConsoleServerComponentEntity componentEntity) {
    return ConsoleComponentInfo.builder()
        .archiveInfo(componentEntity.getArchiveInfo())
        .hostInfo(componentEntity.getHostInfo())
        .build();
  }

  public ConsoleCrossInfo toPojo(ConsoleServerCrossEntity crossEntity) {
    return ConsoleCrossInfo.builder()
        .archiveInfo(crossEntity.getArchiveInfo())
        .hostInfo(crossEntity.getHostInfo())
        .build();
  }

  public ConsoleDbInfo toPojo(ConsoleServerDbEntity dbEntity) {
    ConsoleDbInfo.ConsoleDbInfoBuilder builder =
        ConsoleDbInfo.builder()
            .archiveInfo(dbEntity.getArchiveInfo())
            .hostInfo(dbEntity.getHostInfo());
    if (Objects.nonNull(dbEntity.getMongodbInfo())) {
      builder.mongodbInfo(dbEntity.getMongodbInfo());
    }
    return builder.build();
  }

  public ConsoleEcsInfo toPojo(ConsoleServerEcsEntity ecsEntity) {
    return ConsoleEcsInfo.builder()
        .archiveInfo(ecsEntity.getArchiveInfo())
        .ipInfo(ecsEntity.getIpInfo())
        .portInfo(ecsEntity.getPortInfo())
        .sshInfo(ecsEntity.getSshInfo())
        .build();
  }

  public ConsoleGameInfo toPojo(ConsoleServerGameEntity gameEntity) {
    return ConsoleGameInfo.builder()
        .archiveInfo(gameEntity.getArchiveInfo())
        .hostInfo(gameEntity.getHostInfo())
        .build();
  }
}
