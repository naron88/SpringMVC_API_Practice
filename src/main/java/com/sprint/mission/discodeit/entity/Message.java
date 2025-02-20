package com.sprint.mission.discodeit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Entity
@Getter
public class Message implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private final UUID id;
  private final Instant createdAt;
  private Instant updatedAt;

  private String message;
  private UUID channelId;
  private UUID authorId;
  private List<UUID> attachmentIds;

  public Message() {
    this.id = UUID.randomUUID();
    this.createdAt = Instant.now();
  }

  public Message(String message, UUID channelId, UUID authorId, List<UUID> attachmentIds) {
    this.id = UUID.randomUUID();
    this.createdAt = Instant.now();
    //
    this.message = message;
    this.channelId = channelId;
    this.authorId = authorId;
    this.attachmentIds = attachmentIds;
  }

  public void update(String newContent) {
    boolean anyValueUpdated = false;
    if (newContent != null && !newContent.equals(this.message)) {
      this.message = newContent;
      anyValueUpdated = true;
    }

    if (anyValueUpdated) {
      this.updatedAt = Instant.now();
    }
  }
}
