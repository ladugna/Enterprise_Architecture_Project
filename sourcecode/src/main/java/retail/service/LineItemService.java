package retail.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.Optional;

@Service
public interface LineItemService {
    public LineItem add(LineItem lineItem);
    public void delete(long id);
    public Optional<LineItem> load(long id);
    public LineItem updateQty(long id, int Qty);
    public List<LineItem> findAll();
    public List<LineItem> findByCart(ShoppingCart cart);
    public void save(LineItem lineItem);
}
