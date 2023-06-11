package retail.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import retail.contract.ItemRequest;
import retail.domain.Item;
import retail.service.adapter.ItemDTO;
import retail.service.adapter.ReviewDTO;

import java.util.Optional;

public interface ItemService {
    Page<ItemDTO> findAllItems(Pageable pageable);
    Page<ItemDTO> findAllByName(String name, Pageable pageable);
    Page<ItemDTO> findAllByPrice(double price, Pageable pageable);
    Page<ItemDTO> findAllByCategory(String category, Pageable pageable);
    ItemDTO findById(long Id);
    ItemDTO saveItem(ItemRequest itemRequest);
    void deleteItemById(long Id);
    ItemDTO editItem(ItemRequest itemRequest);

    ItemDTO findByBarcodeNumber(int barcodeNumber);

}
