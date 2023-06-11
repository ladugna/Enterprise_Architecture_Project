package retail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.domain.Category;
import retail.domain.Item;
import retail.domain.Review;
import retail.domain.SingleItem;
import retail.logging.ILogger;
import retail.repository.OrderRepository;
import retail.service.adapter.OrderAdapter;
import retail.service.adapter.OrderDTO;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ILogger logger;

    @Autowired
    private ModelMapper mapper;

    public OrderDTO createNewOrder(String name, String description, byte[] image, int barcodeNumber, double price,
                                int quantityInStock, String categoryName, String reviewTitle,
                                int reviewNumberOfStars,LocalDateTime date){
        Item item= new SingleItem(name, description,image, barcodeNumber, price, quantityInStock, categoryName);
        Review review= new Review(reviewTitle, reviewNumberOfStars, date);
        //Category category= new Category(categoryName);
        //Collection<Category> categories= new ArrayList<>();
        Collection<Review> reviews= new ArrayList<>();
        reviews.add(review);
        //categories.add(category);
      // item.setCategories(categories);
       //item.setReviews(reviews);

       orderRepository.save(item);
       logger.log("create order with barcode Number:   "+barcodeNumber);
       //return OrderAdapter.getOrderDTOFromOrder(item);
        return mapper.map(item, OrderDTO.class);
   }

   public OrderDTO getOrder( int barcodeNumber){
        Item item= orderRepository.findById(barcodeNumber).get();
       //return OrderAdapter.getOrderDTOFromOrder(item);
       return mapper.map(item, OrderDTO.class);
   }
public Collection<OrderDTO> getAllOrders(){
    List<Item> items= orderRepository.findAll();
    //return OrderAdapter.getOrderDTOListFromOrderList(items);
    return items.stream().map(i -> mapper.map(i, OrderDTO.class)).collect(Collectors.toList());
}

    @Override
    public OrderDTO modifyOrder(String name, String description, byte[] image, int barcodeNumber,
                                double price, int quantityInStock, String categoryName,
                                String reviewTitle, int reviewNumberOfStars, LocalDateTime date) {

        Item item= orderRepository.findById(barcodeNumber).get();
        if(item==null){
            return null;
        }
        item.setName(name);
        item.setDescription(description);
        item.setImage(image);
        item.setPrice(price);
        item.setBarcodeNumber(barcodeNumber);
        item.setQuantityInStock(quantityInStock);

        Review review1= new Review(reviewTitle, reviewNumberOfStars, date);
        Category category= new Category(categoryName);
        //Collection<Category> categories= new ArrayList<>();
        Collection<Review> reviews= new ArrayList<>();
        reviews.add(review1);
        //categories.add(category);
        item.setCategory(categoryName);//setCategories(categories);
        //item.setReviews(reviews);
        orderRepository.save(item);
        logger.log("create order with barcode number: "+barcodeNumber);
        //return OrderAdapter.getOrderDTOFromOrder(item);
        return mapper.map(item, OrderDTO.class);
    }

}
