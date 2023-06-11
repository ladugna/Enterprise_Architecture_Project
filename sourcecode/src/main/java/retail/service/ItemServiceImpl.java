package retail.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retail.contract.ItemRequest;
import retail.domain.Item;
import retail.repository.ItemRepository;
import retail.service.adapter.ItemAdapter;
import retail.service.adapter.ItemDTO;

import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public Page<ItemDTO> findAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(i -> mapper.map(i, ItemDTO.class));
    }

    @Override
    public Page<ItemDTO> findAllByName(String name, Pageable pageable) {
        return itemRepository.findAllByName(name, pageable).map(i -> mapper.map(i, ItemDTO.class));
    }

    @Override
    public Page<ItemDTO> findAllByPrice(double price, Pageable pageable) {
        return itemRepository.findAllByPrice(price, pageable).map(i -> mapper.map(i, ItemDTO.class));
    }

    @Override
    public Page<ItemDTO> findAllByCategory(String category, Pageable pageable) {
        return itemRepository.findAllByCategory(category, pageable).map(i -> mapper.map(i, ItemDTO.class));
    }

    @Override
    public ItemDTO findById(long Id) {
        Optional<Item> item = itemRepository.findById(Id);

        if(item.isPresent())
        {
            return mapper.map(item.get(), ItemDTO.class);
        }
        return null;
    }

    @Override
    public ItemDTO saveItem(ItemRequest itemRequest) {
        Item item = ItemAdapter.getItemFromItemRequest(itemRequest);

       return mapper.map(itemRepository.save(item), ItemDTO.class);
    }

    @Override
    public void deleteItemById(long Id) {
        itemRepository.deleteById(Id);
    }

    @Override
    public ItemDTO editItem(ItemRequest itemRequest) {
        Item item = ItemAdapter.getItemFromItemRequest(itemRequest);

        return mapper.map(itemRepository.save(item), ItemDTO.class);
    }

    @Override
    public ItemDTO findByBarcodeNumber(int barcodeNumber) {
        Optional<Item> item = itemRepository.findByBarcodeNumber(barcodeNumber);
        return item.map(value -> mapper.map(value, ItemDTO.class)).orElse(null);
    }
}
