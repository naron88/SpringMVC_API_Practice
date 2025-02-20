package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class JCFChannelRepository implements ChannelRepository {

  Map<UUID, Channel> store;

  public JCFChannelRepository() {
    this.store = new HashMap<>();
  }

  @Override
  public Channel save(Channel channel) {
    store.put(channel.getId(), channel);
    return channel;
  }

  @Override
  public Optional<Channel> findById(UUID channelId) {
    return Optional.ofNullable(store.get(channelId));
  }

  @Override
  public List<Channel> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public boolean existsById(UUID id) {
    return store.containsKey(id);
  }

  @Override
  public void deleteById(UUID id) {
    store.remove(id);
  }
}
