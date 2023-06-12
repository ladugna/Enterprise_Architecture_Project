package retail.service.adapter;

import retail.contract.ItemRequest;
import retail.domain.CompositeItem;
import retail.domain.Item;
import retail.domain.SingleItem;

public class ItemAdapter {

    public static Item getItemFromItemRequest(ItemRequest itemRequest) {

        Item item;
        if (itemRequest.getSubItems() == null || itemRequest.getSubItems().isEmpty()) {
            item = getSingleItemFromItemRequest(itemRequest);
        } else {
            item = getCompositeItemFromItemRequest(itemRequest);
        }

        return item;
    }

    private static Item getSingleItemFromItemRequest(ItemRequest itemRequest) {
        return new SingleItem(itemRequest.getId(),itemRequest.getName(), itemRequest.getDescription(), itemRequest.getImage(), itemRequest.getBarcodeNumber(), itemRequest.getPrice(), itemRequest.getQuantityInStock(), itemRequest.getCategory());
    }

    private static Item getCompositeItemFromItemRequest(ItemRequest itemRequest) {
        Item item = new CompositeItem(itemRequest.getId(), itemRequest.getName(), itemRequest.getDescription(), itemRequest.getImage(), itemRequest.getBarcodeNumber(), itemRequest.getPrice(), itemRequest.getQuantityInStock(), itemRequest.getCategory());

        for (ItemRequest i : itemRequest.getSubItems()) {
            item.addItem(getItemFromItemRequest(i));
        }

        return item;
    }
}
