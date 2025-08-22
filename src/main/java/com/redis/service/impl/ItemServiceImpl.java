package com.redis.service.impl;

import com.redis.model.Item;
import com.redis.repository.ItemRepository;
import com.redis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    @Cacheable(value = "item", key = "#id")
    public Optional<Item> findById(String id) {
        return itemRepository.findById(id);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }
}
