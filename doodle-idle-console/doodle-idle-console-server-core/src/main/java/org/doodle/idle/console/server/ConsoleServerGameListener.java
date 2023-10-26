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

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerGameListener extends AbstractMongoEventListener<ConsoleServerGameEntity> {
  ConsoleServerGameService gameService;

  @Override
  public void onBeforeConvert(BeforeConvertEvent<ConsoleServerGameEntity> event) {
    ConsoleServerGameEntity gameEntity = event.getSource();
    if (gameEntity.getArchiveInfo().getUniqueId() < 1) {
      gameEntity.getArchiveInfo().setUniqueId(gameService.uniqueSeq());
    }

    if (gameEntity.getArchiveInfo().getCategorySeqId() < 1) {
      gameEntity
          .getArchiveInfo()
          .setCategorySeqId(gameService.generateSeq(gameEntity.getArchiveInfo().getCategory()));
    }
  }
}