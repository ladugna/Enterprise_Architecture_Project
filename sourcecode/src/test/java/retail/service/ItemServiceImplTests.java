package retail.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import retail.domain.Item;
import retail.domain.SingleItem;
import retail.repository.ItemRepository;
import retail.service.adapter.ItemDTO;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemServiceImplTests {
    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    public void testFindAllItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new SingleItem(1L,"Apple1", "good apple",null,4893345, 10.0,5, "pants"));
        itemList.add(new SingleItem(1L,"Apple2", "good apple",null,4893346, 10.0,5, "pants"));
        Page<Item> itemPage = new PageImpl<>(itemList);
        Mockito.when(itemRepository.findAll(Mockito.any(Pageable.class))).thenReturn(itemPage);

        Page<ItemDTO> resultPage = itemService.findAllItems(Pageable.unpaged());

        Mockito.verify(itemRepository).findAll(Mockito.any(Pageable.class));
        Mockito.verify(mapper, Mockito.times(2)).map(Mockito.any(Item.class), Mockito.eq(ItemDTO.class));
    }

    @Test
    public void testFindAllByName() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new SingleItem(1L,"Apple1", "good apple",null,4893345, 10.0,5, "pants"));
        Page<Item> itemPage = new PageImpl<>(itemList);
        Mockito.when(itemRepository.findAllByName(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(itemPage);

        Page<ItemDTO> resultPage = itemService.findAllByName("Apple1", Pageable.unpaged());

        Mockito.verify(itemRepository).findAllByName(Mockito.anyString(), Mockito.any(Pageable.class));
        Mockito.verify(mapper).map(Mockito.any(Item.class), Mockito.eq(ItemDTO.class));
    }
}

