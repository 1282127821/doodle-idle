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

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ConsoleServerComponentListener
    extends AbstractMongoEventListener<ConsoleServerComponentEntity> {
  ConsoleServerComponentService componentService;

  @Override
  public void onBeforeConvert(BeforeConvertEvent<ConsoleServerComponentEntity> event) {
    ConsoleServerComponentEntity componentEntity = event.getSource();
    if (componentEntity.getArchiveInfo().getUniqueId() < 1) {
      componentEntity.getArchiveInfo().setUniqueId(componentService.uniqueSeq());
    }
    if (componentEntity.getArchiveInfo().getCategorySeqId() < 1) {
      componentEntity
          .getArchiveInfo()
          .setCategorySeqId(
              componentService.generateSeq(componentEntity.getArchiveInfo().getCategory()));
    }
  }
}
