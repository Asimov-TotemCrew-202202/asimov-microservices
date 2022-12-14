package com.totemcrew.items.api;

import com.totemcrew.items.domain.service.ItemService;
import com.totemcrew.items.mapping.ItemMapper;
import com.totemcrew.items.resource.CreateItemResource;
import com.totemcrew.items.resource.ItemResource;
import com.totemcrew.items.resource.UpdateItemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ItemsController {

    private final ItemService itemService;

    private final ItemMapper mapper;

    public ItemsController(ItemService itemService, ItemMapper mapper) {
        this.itemService = itemService;
        this.mapper = mapper;
    }

    @GetMapping("courses/{courseId}/items")
    public List<ItemResource> getAllByCourseID(@PathVariable Long courseId) {
        return mapper.modelListToResource(itemService.getAllByCourseId(courseId));
    }

    @PostMapping("courses/{courseId}/items")
    public ItemResource createItem(@RequestBody CreateItemResource request, @PathVariable Long courseId) {
        return mapper.toResource(itemService.create(mapper.toModel(request),courseId));
    }


    @PutMapping("items/{itemId}")
    public ItemResource updateItem(@PathVariable Long itemId, @RequestBody UpdateItemResource request) {
        return mapper.toResource(itemService.update(itemId, mapper.toModel(request)));
    }

    @DeleteMapping("items/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        return itemService.delete(itemId);
    }
}
