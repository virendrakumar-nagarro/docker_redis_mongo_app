package com.redis.controller;

import com.redis.model.Item;
import com.redis.repository.ItemRepository;
import com.redis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String listItems(Model model) {
        model.addAttribute("items", itemService.getAllItem());
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Item item) {
        return "add-item";
    }

    @PostMapping("/add")
    public String addItem(Item item) {
        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Item item = itemService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        model.addAttribute("item", item);
        return "update-item";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") String id, Item item) {
        item.setId(id);
        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") String id) {
        Item item = itemService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        itemService.deleteItem(item);
        return "redirect:/";
    }
}
