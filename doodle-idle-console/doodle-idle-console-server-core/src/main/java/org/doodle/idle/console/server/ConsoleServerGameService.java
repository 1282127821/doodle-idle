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

import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.doodle.design.common.model.PageRequest;
import org.doodle.design.idle.console.model.info.ConsoleGameInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsoleServerGameService extends ConsoleServerSeqService {
  ConsoleServerMapper mapper;
  ConsoleServerGameRepo gameRepo;

  public ConsoleServerGameService(
      MongoTemplate mongoTemplate, ConsoleServerMapper mapper, ConsoleServerGameRepo gameRepo) {
    super(mongoTemplate, ConsoleServerGameEntity.COLLECTION);
    this.mapper = mapper;
    this.gameRepo = gameRepo;
  }

  public Mono<List<ConsoleGameInfo>> pageMono(PageRequest pageRequest) {
    return Mono.fromCallable(() -> page(pageRequest));
  }

  public List<ConsoleGameInfo> page(PageRequest pageRequest) {
    Page<ConsoleServerGameEntity> page =
        gameRepo.findAll(
            Pageable.ofSize(pageRequest.getPageSize()).withPage(pageRequest.getPageNumber()));
    List<ConsoleServerGameEntity> content = page.getContent();
    return CollectionUtils.isEmpty(content)
        ? Collections.emptyList()
        : content.stream().map(mapper::toPojo).toList();
  }

  public Mono<ConsoleGameInfo> queryMono(long uniqueId) {
    return Mono.fromCallable(() -> query(uniqueId));
  }

  public ConsoleGameInfo query(long uniqueId) {
    return gameRepo.findByArchiveInfoUniqueId(uniqueId).map(mapper::toPojo).orElse(null);
  }
}
