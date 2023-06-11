package retail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import retail.constant.Action;
import retail.domain.*;
import retail.error.CustomErrorType;
import retail.service.*;
import retail.service.adapter.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    LineItemService lineItemService;

    @Autowired
    OrderHistoryServiceImp orderHistoryServiceImp;

    @Autowired
    OrderHistoryDetailsImp orderHistoryDetailsService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private Sender sender;

    @PostMapping("/checkout")
    public  ResponseEntity<?> checkout(OAuth2Authentication user) throws JsonProcessingException {

        // get user then the cart linked
        User connectedUser = (User) user.getUserAuthentication().getPrincipal();
        CustomerDTO customer = customerService.getCustomerByUsername(connectedUser.getUsername());
        ShoppingCart shoppingCart = (shoppingCartService.load(customer.getCartId())).orElse(null);

        // fetch lines in cart
        List<LineItem> lineItemList = lineItemService.findByCart(shoppingCart);

        Long orderId = null;

        if(lineItemList.size() > 0){
            // create Order History
            double totalPrice = lineItemList.stream().mapToDouble(LineItem::getDiscountValue).sum();

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setTotalPrice(totalPrice);
            orderHistory.setShippingAndHandling(0);
            orderHistory.setTaxAmount(0);
            if(customer.getPreferredShippingAddressId() != null){
                orderHistory.setShippingAddress(AddressAdapter.getCategoryDTOFromCategory(customer.getPreferredShippingAddressId()));
            }
            if(customer.getBillingAddressId() != null){
                orderHistory.setBillingAddress(AddressAdapter.getCategoryDTOFromCategory(customer.getBillingAddressId()));
            }

            List<OrderHistoryDetails> orderHistoryDetails = new ArrayList<>();
            for(LineItem line : lineItemList){
                // change line order status in order to be removed to user's cart
                line.setStatus("PROCESSED");
                lineItemService.save(line);
                // get item linked to lineItem
                Item item = line.getItem();
                // create order HistoryDetails
                orderHistoryDetails.add(new OrderHistoryDetails(
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        line.getDiscountValue(),
                        line.getQuantity()
                ));
            }

            orderHistory.setOrderHistoryDetails(orderHistoryDetails);
            orderId = orderHistoryServiceImp.saveOrderHistory(orderHistory).getId();

            // sending message to shipping service using kafka
            //sender.send("new_order_processed", Long.toString(orderId));
            OrderAction kafkaMessage = new OrderAction(Action.ORDER_PLACED, customer.getUsername(), customer.getEmail());
            ObjectMapper objectMapper = new ObjectMapper();
            String kafkaMessageString =  objectMapper.writeValueAsString(kafkaMessage);
            sender.send("orderTopic", kafkaMessageString);

        }


        return new ResponseEntity<>("Order Successfully processed", HttpStatus.OK);
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<?> getOrder(@PathVariable("barcode") int barcode){
        OrderDTO orderDTO= orderService.getOrder(barcode);
        if(orderDTO==null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Order with barcode "+ barcode +" is not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
    }
@GetMapping("/order")
    public ResponseEntity<?> getAllOrders(){
   Collection<OrderDTO> orderDTOList= orderService.getAllOrders();
   Orders orders=new Orders();
   orders.setOrderDTOS(orderDTOList);
   if(orders==null){
       return new ResponseEntity<CustomErrorType>( new CustomErrorType("No order Found"),HttpStatus.NOT_FOUND);
   }
   return new ResponseEntity<Orders>(orders, HttpStatus.OK);

}
@PostMapping("/order")
public ResponseEntity<?> addNewOrder(@PathVariable(value = "name") String name,
                                  @PathVariable(value = "description")String description,
                                  @PathVariable(value = "image")byte[] image,
                                  @PathVariable(value = "barcodeNumber")int barcodeNumber,
                                  @PathVariable(value = "price")double price,
                                  @PathVariable(value = "quantityInStock")int quantityInStock,
                                  @PathVariable(value = "categoryName")String categoryName,
                                  @PathVariable(value = "reviewTitle")String reviewTitle,
                                  @PathVariable(value = "reviewNumberOfStars") int reviewNumberOfStars,
                                  @PathVariable(value = "date")LocalDateTime date){
        OrderDTO orderDTO= orderService.createNewOrder(name,description, image, barcodeNumber,price,
                quantityInStock, categoryName, reviewTitle, reviewNumberOfStars, date);
        return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
}

@PutMapping("/{barcode}")
public ResponseEntity<?> updateOrder(@PathVariable(value = "name") String name,
                                     @PathVariable(value = "description")String description,
                                     @PathVariable(value = "image")byte[] image,
                                     @PathVariable(value = "barcodeNumber")int barcodeNumber,
                                     @PathVariable(value = "price")double price,
                                     @PathVariable(value = "quantityInStock")int quantityInStock,
                                     @PathVariable(value = "categoryName")String categoryName,
                                     @PathVariable(value = "reviewTitle")String reviewTitle,
                                     @PathVariable(value = "reviewNumberOfStars") int reviewNumberOfStars,
                                     @PathVariable(value = "date")LocalDateTime date){
    OrderDTO orderDTO= orderService.createNewOrder(name,description, image, barcodeNumber,price,
            quantityInStock, categoryName, reviewTitle, reviewNumberOfStars, date);
        if(orderDTO==null){
        return new ResponseEntity<CustomErrorType>( new CustomErrorType("No order Found "),HttpStatus.NOT_FOUND);
         }
         return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
        }
   }
