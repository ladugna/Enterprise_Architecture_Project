package retail.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retail.domain.OrderHistory;
import retail.service.OrderHistoryService;
import retail.service.adapter.OrderHistoryDTO;
import retail.service.adapter.OrderStatusUpdateRequest;

import java.util.Map;

@RestController
@RequestMapping("/orderHistory")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping
    public ResponseEntity<?> getOrderHistories(Pageable pageable , @RequestParam(required = false) Map<String, String> searchCriteria)
    {
        Page<OrderHistory> response = orderHistoryService.findAll(pageable , searchCriteria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderHistory(@PathVariable long id){
        OrderHistory orderHistory = orderHistoryService.findById(id);
        return new ResponseEntity<>(orderHistory , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveOrderHistory(@RequestBody OrderHistoryDTO orderHistory)
    {
        OrderHistory orderHistoryResult = orderHistoryService.saveOrderHistory(orderHistory);
        return new ResponseEntity<>(orderHistoryResult , HttpStatus.OK);
    }

    @PutMapping("/updateState/{id}")
    public ResponseEntity<?> updateOrderHistoryState(@PathVariable long id , @RequestBody OrderStatusUpdateRequest request){
        orderHistoryService.updateOrderHistoryStatus(id , request.getStatus());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
