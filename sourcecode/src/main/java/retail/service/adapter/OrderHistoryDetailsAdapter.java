package retail.service.adapter;

import retail.domain.OrderHistoryDetails;

public class OrderHistoryDetailsAdapter {

    public static OrderHistoryDetails getOrderHistoryDetailsFromDTO(OrderHistoryDetailsDTO orderHistoryDetailsDTO){
        OrderHistoryDetails temp = new OrderHistoryDetails();
        temp.setDescription(orderHistoryDetailsDTO.getDescription());
        temp.setName(orderHistoryDetailsDTO.getName());
        temp.setPrice(orderHistoryDetailsDTO.getPrice());
        temp.setQuantity(orderHistoryDetailsDTO.getQuantity());
        temp.setDiscountValue(orderHistoryDetailsDTO.getDiscountValue());
        return temp;
    }
}
