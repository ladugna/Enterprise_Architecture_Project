package retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.domain.LineItem;
import retail.domain.ShoppingCart;
import retail.repository.LineItemRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LineItemServiceImp implements LineItemService{

    @Autowired
    LineItemRepository lineItemRepository;

    @Override
    public LineItem add(LineItem lineItem) {
        return lineItemRepository.save(lineItem);
    }

    @Override
    public void delete(long id) {
        lineItemRepository.deleteById(id);
    }

    @Override
    public Optional<LineItem> load(long id) {
        return lineItemRepository.findById(id);
    }

    @Override
    public LineItem updateQty(long lineId,int Qty) {
        Optional<LineItem> lineItemOptional = lineItemRepository.findById(lineId);
        LineItem lineItemFound = lineItemOptional.orElse(null);
        if(lineItemFound != null){
            lineItemFound.setQuantity(Qty);
            return lineItemRepository.save(lineItemFound);
        }
        return null;
    }

    @Override
    public List<LineItem> findAll() {
        return lineItemRepository.findAll();
    }

    @Override
    public List<LineItem> findByCart(ShoppingCart cart) {
        return lineItemRepository.findByCart(cart);
    }

    @Override
    public void save(LineItem lineItem) {
        lineItemRepository.save(lineItem);
    }
}
