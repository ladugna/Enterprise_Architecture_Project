package retail.service;

import org.springframework.stereotype.Service;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;

import java.util.Optional;

@Service
public interface ShoppingCartService {

    public void add(ShoppingCart shoppingCart);
    public void delete(long id);
    public Optional<ShoppingCart> load(long id);
    public ShoppingCart update(ShoppingCart shoppingCart);

}
