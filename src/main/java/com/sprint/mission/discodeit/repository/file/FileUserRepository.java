package com.sprint.mission.discodeit.repository.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.mission.discodeit.config.FileConfig;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUserRepository implements UserRepository {

  private final String userJsonFile;
  private final ObjectMapper mapper;
  private Map<UUID, User> store;

  @Autowired
  public FileUserRepository(FileConfig fileConfig) {
    String fileDirectory = fileConfig.getFileDirectory();
    String fileName = fileConfig.getUserJsonPath();
    this.userJsonFile = fileDirectory + "/" + fileName;
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    store = loadUserFromJson();
  }

  @Override
  public User save(User user) {
    store.put(user.getId(), user);
    saveUsersToJson(store);
    return user;
  }

  @Override
  public Optional<User> findById(UUID id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return store.values().stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst();
  }

  @Override
  public List<User> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public boolean existsById(UUID id) {
    return store.containsKey(id);
  }

  @Override
  public void deleteById(UUID id) {
    if (store.remove(id) != null) {
      saveUsersToJson(store);
    }
  }

  @Override
  public boolean existsByUsername(String username) {
    return store.values().stream()
        .anyMatch(user -> user.getUsername().equals(username));
  }

  @Override
  public boolean existsByPhoneNumber(String phoneNumber) {
    return store.values().stream()
        .anyMatch(user -> user.getPhoneNumber().equals(phoneNumber));
  }

  @Override
  public boolean existsByEmail(String email) {
    return store.values().stream()
        .anyMatch(user -> user.getEmail().equals(email));
  }

  private Map<UUID, User> loadUserFromJson() {
    Map<UUID, User> map = new HashMap<>();
    File file = new File(userJsonFile);
    if (!file.exists()) {
      return map;
    }
    try {
      // JSON 파일 읽어 Map 형태로 변환
      map = mapper.readValue(file, new TypeReference<Map<UUID, User>>() {
      });
    } catch (IOException e) {
      throw new RuntimeException("Failed to save read statuses to JSON file.", e);
    }
    return map;
  }

  private void saveUsersToJson(Map<UUID, User> store) {
    try {
      // json 데이터를 보기좋게(pretty print) 정렬하여 저장
      mapper.writerWithDefaultPrettyPrinter().writeValue(new File(userJsonFile), store);
    } catch (IOException e) {
      throw new RuntimeException("Failed to save read statuses to JSON file.", e);
    }
  }
}