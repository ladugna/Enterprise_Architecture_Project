package retail.service.adapter;

import retail.constant.OrderHistoryState;

public class OrderStatusUpdateRequest {
    private OrderHistoryState status;

    public OrderStatusUpdateRequest() {

    }

    public OrderHistoryState getStatus() {
        return status;
    }

    public void setStatus(OrderHistoryState status) {
        this.status = status;
    }
}
