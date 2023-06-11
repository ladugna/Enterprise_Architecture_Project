package retail.service.adapter;

import retail.domain.OrderHistory;
import retail.domain.OrderHistoryDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAdapter {
    public static OrderHistory getOrderHistoryFromDTO(OrderHistoryDTO orderHistoryDTO){
        OrderHistory orderHistory = new OrderHistory();
        List<OrderHistoryDetails> orderHistoryDetails = new ArrayList<>();
        for (OrderHistoryDetailsDTO temp : orderHistoryDTO.getOrderHistoryDetails() ){
            orderHistoryDetails.add(OrderHistoryDetailsAdapter.getOrderHistoryDetailsFromDTO(temp));
        }
        orderHistory.setOrderHistoryDetails(orderHistoryDetails);
        orderHistory.setDateOrdered(orderHistoryDTO.getDateOrdered());
        orderHistory.setStatus(orderHistoryDTO.getStatus());
        orderHistory.setSubtotal(orderHistoryDTO.getSubtotal());
        orderHistory.setPaymentMethods(orderHistoryDTO.getPaymentMethods());
        orderHistory.setShippingAddress(orderHistoryDTO.getShippingAddress());
        orderHistory.setTotalPrice(orderHistoryDTO.getTotalPrice());
        orderHistory.setTaxAmount(orderHistoryDTO.getTaxAmount());
        orderHistory.setShippingAndHandling(orderHistoryDTO.getShippingAndHandling());
        orderHistory.setCustomer(orderHistoryDTO.getCustomer());
        return orderHistory;
    }
}
