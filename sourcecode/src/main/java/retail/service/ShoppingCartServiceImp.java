package retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;
import retail.repository.ShoppingCartRepository;

import java.util.Optional;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService{

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public void add(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void delete(long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public Optional<ShoppingCart> load(long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        Optional<ShoppingCart> ShoppingCartOptional = shoppingCartRepository.findById(shoppingCart.getCartId());
        ShoppingCart shoppingCartFound = ShoppingCartOptional.orElse(null);
        if(shoppingCartFound != null){
//            shoppingCartFound.setLineItems(shoppingCart.getLineItems());
            shoppingCartRepository.save(shoppingCartFound);
            return shoppingCartFound;
        }
        return null;
    }


}
