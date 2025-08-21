package com.redis.service;

import com.redis.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ItemService {
    List<Item> getAllItem();

    void saveItem(Item item);

    Optional<Item> findById(String id);

    void deleteItem(Item item);
}
