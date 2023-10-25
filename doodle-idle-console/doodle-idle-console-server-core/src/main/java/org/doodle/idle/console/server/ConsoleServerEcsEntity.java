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

import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.doodle.design.idle.console.model.info.ConsoleArchiveInfo;
import org.doodle.design.idle.console.model.info.ConsoleEcsIpInfo;
import org.doodle.design.idle.console.model.info.ConsoleEcsPortInfo;
import org.doodle.design.idle.console.model.info.ConsoleEcsSshInfo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = ConsoleServerEcsEntity.COLLECTION)
public class ConsoleServerEcsEntity {
  public static final String COLLECTION = "console-ecs";

  @MongoId ConsoleArchiveInfo archiveInfo;

  ConsoleEcsIpInfo ipInfo;

  ConsoleEcsPortInfo portInfo;

  ConsoleEcsSshInfo sshInfo;

  @Version byte dummy;

  @CreatedDate LocalDateTime createdAt;

  @LastModifiedDate LocalDateTime modifiedAt;
}
