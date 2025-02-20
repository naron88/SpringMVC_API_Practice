package com.sprint.mission.discodeit.dto.readStatus;

import java.time.Instant;
import java.util.UUID;

public record ReadStatusDto(
    UUID readStatusId,
    UUID userId,
    UUID channelId,
    Instant lastReadTime,
    Instant createdAt,
    Instant updatedAt
) {

}
