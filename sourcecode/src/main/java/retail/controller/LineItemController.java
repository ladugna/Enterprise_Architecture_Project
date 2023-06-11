package retail.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import retail.contract.ItemRequest;
import retail.contract.createLineItemRequest;
import retail.contract.updateLineItemRequest;
import retail.domain.Customer;
import retail.domain.Item;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;
import retail.service.*;
import retail.service.adapter.CustomerDTO;
import retail.service.adapter.ItemAdapter;
import retail.service.adapter.ItemDTO;

import javax.sound.sampled.Line;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lineItem")
public class LineItemController {

    @Autowired
    LineItemService lineItemService;

    @Autowired
    ItemServiceImpl itemService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    CustomerService customerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<?> createLineItem(OAuth2Authentication user, @RequestBody createLineItemRequest request){

        int barcodeNumber = request.getBarcodeNumber();
        int qty = request.getQty();

        // get item from stock
        Item cartItem = itemService.findByBarcodeNumber(barcodeNumber) != null ?
                ItemAdapter.getItemFromItemRequest(mapper.map(itemService.findByBarcodeNumber(barcodeNumber),
                        ItemRequest.class)) : null;

        // get user then the cart linked
        User connectedUser = (User) user.getUserAuthentication().getPrincipal();
        CustomerDTO customer = customerService.getCustomerByUsername(connectedUser.getUsername());
        ShoppingCart shoppingCart = (shoppingCartService.load(customer.getCartId())).orElse(null);

        if(shoppingCart != null){

            // create new line
            LineItem lineItem = new LineItem();
            lineItem.setItem(cartItem);
            lineItem.setDiscountValue(cartItem != null ? cartItem.getPrice() : 0);
            lineItem.setQuantity(qty);
            lineItem.setCart(shoppingCart);

            LineItem lineItemCreated = lineItemService.add(lineItem);

            return new ResponseEntity<LineItem>(lineItemCreated, HttpStatus.OK);

        }

        return new ResponseEntity<>("unable to find logged user cart" ,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    public  ResponseEntity<?> updateLineItem(OAuth2Authentication user, @RequestBody updateLineItemRequest request){
        LineItem lineItemUpdated = lineItemService.updateQty(request.getLineId(), request.getQty());
        return new ResponseEntity<LineItem>(lineItemUpdated, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> findLineItem(@PathVariable long id){
        Optional<LineItem> response = lineItemService.load(id);

        if(response.isEmpty()){
            return new ResponseEntity<>("Line item not available" ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<LineItem>(response.get(), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteLineItem(@PathVariable Long id){
        Optional<LineItem> response = lineItemService.load(id);

        if(response.isEmpty()){
            return new ResponseEntity<>("Line item not available" ,HttpStatus.BAD_REQUEST);
        }

        lineItemService.delete(id);
        return new ResponseEntity<LineItem>(response.get(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> findAllLineItem(){
        List<LineItem> lineItems = lineItemService.findAll();
        return new ResponseEntity<List<LineItem>>(lineItems, HttpStatus.OK);
    }

}
