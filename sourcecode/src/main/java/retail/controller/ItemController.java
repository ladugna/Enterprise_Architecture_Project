package retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import retail.contract.ItemRequest;
import retail.service.ItemServiceImpl;
import org.springframework.data.domain.Pageable;
import retail.service.adapter.ItemDTO;


@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemServiceImpl itemService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> SaveItem(@RequestBody ItemRequest itemRequest)
    {

        return new ResponseEntity<>(itemService.saveItem(itemRequest), HttpStatus.OK);
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> UpdateItem(@RequestBody ItemRequest itemRequest)
    {
        return new ResponseEntity<>(itemService.editItem(itemRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "")
    public ResponseEntity<?> GetItems(Pageable pageable)
    {
        Page<ItemDTO> items = itemService.findAllItems(pageable);

        if(items.isEmpty())
        {
            return new ResponseEntity<>("No items found.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{Id}")
    public ResponseEntity<?> GetItemsById(@PathVariable long Id)
    {
        ItemDTO item = itemService.findById(Id);

        if(item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("No item found.", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/price/{price}")
    public ResponseEntity<?> GetItemsByPrice(@PathVariable double price,  Pageable pageable)
    {
        Page<ItemDTO> items = itemService.findAllByPrice(price, pageable);

        if(items.isEmpty())
        {
            return new ResponseEntity<>("No items found.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "name/{name}")
    public ResponseEntity<?> GetItemsByName(@PathVariable String name,  Pageable pageable)
    {
        Page<ItemDTO> items = itemService.findAllByName(name, pageable);

        if(items.isEmpty())
        {
            return new ResponseEntity<>("No items found.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "category/{category}")
    public ResponseEntity<?> GetItemsByPrice(@PathVariable String category,  Pageable pageable)
    {
        Page<ItemDTO> items = itemService.findAllByCategory(category, pageable);

        if(items.isEmpty())
        {
            return new ResponseEntity<>("No items found.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{Id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> DeleteItemById(@PathVariable long Id)
    {
        itemService.deleteItemById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
