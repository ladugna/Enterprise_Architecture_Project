package retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import retail.contract.ShoppingCartResponse;
import retail.domain.Customer;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;
import retail.service.CustomerService;
import retail.service.LineItemService;
import retail.service.ShoppingCartService;
import retail.service.adapter.CustomerDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CustomerService customerService;

    @Autowired
    LineItemService lineItemService;


    @GetMapping("")
    public ResponseEntity<?> findCart(OAuth2Authentication user){

        User connectedUser = (User) user.getUserAuthentication().getPrincipal();
        CustomerDTO customer = customerService.getCustomerByUsername(connectedUser.getUsername());
        Optional<ShoppingCart> response = shoppingCartService.load(customer.getCartId());
        ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();

        if(response.isEmpty()){
            return new ResponseEntity<>("Shopping cart not available" , HttpStatus.BAD_REQUEST);
        }

        shoppingCartResponse.setCartId(response.get().getCartId());

        // fetch lines in cart
        List<LineItem> lineItemList = lineItemService.findByCart(response.get());
        shoppingCartResponse.setLineItems(lineItemList);

        return new ResponseEntity<ShoppingCartResponse>(shoppingCartResponse, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@RequestParam Long id){
        Optional<ShoppingCart> response = shoppingCartService.load(id);

        if(response.isEmpty()){
            return new ResponseEntity<>("Line item not available" ,HttpStatus.BAD_REQUEST);
        }

        shoppingCartService.delete(id);
        return new ResponseEntity<ShoppingCart>(response.get(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody ShoppingCart shoppingCart){
        ShoppingCart shoppingCartUpdated = shoppingCartService.update(shoppingCart);
        return new ResponseEntity<ShoppingCart>(shoppingCartUpdated, HttpStatus.OK);
    }


}
