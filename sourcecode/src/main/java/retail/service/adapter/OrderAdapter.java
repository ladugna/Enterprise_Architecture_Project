package retail.service.adapter;

import retail.domain.Category;
import retail.domain.Item;
import retail.domain.Review;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter {
//    public static Item getOrderFromOrderDTO(OrderDTO orderDTO){
//        return new Item(orderDTO.getName(), orderDTO.getDescription(), orderDTO.getImage(), orderDTO.getBarcodeNumber(),
//                orderDTO.getPrice(), orderDTO.getQuantityInStock());
//    }
//    public static OrderDTO getOrderDTOFromOrder(Item item ){
//        OrderDTO orderDTO=new OrderDTO(item.getName(),item.getDescription(),
//                item.getImage(), item.getBarcodeNumber(), item.getPrice(), item.getQuantityInStock());
//
//        for (Category category: item.getCategories()){
//            orderDTO.getCategories().add(CategoryAdapter.getCategoryDTOFromCategory(category));
//        }
//  for(Item item1:item.getItems()){
//      orderDTO.getItems().add(OrderAdapter.getOrderDTOFromOrder(item1));
//  }
//
//  for(Review review:item.getReviews()){
//      orderDTO.getReviews().add(ReviewAdapter.getReviewDTOFromReview(review));
//  }
//        return orderDTO;
//    }

//public static List<OrderDTO> getOrderDTOListFromOrderList(List<Item> itemList){
//        List<OrderDTO> orderDTOList=new ArrayList<>();
//        for(Item item:itemList){
//            orderDTOList.add(OrderAdapter.getOrderDTOFromOrder(item));
//        }
//
//        return orderDTOList;
//}
}
