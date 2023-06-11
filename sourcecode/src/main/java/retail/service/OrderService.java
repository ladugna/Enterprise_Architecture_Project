package retail.service;

import retail.service.adapter.OrderDTO;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Collection;

public interface OrderService {
    public OrderDTO createNewOrder(String name, String description, byte[] image, int barcodeNumber, double price,
                                int quantityInStock, String categoryName, String reviewTitle,
                                int reviewNumberOfStars, LocalDateTime date);
    public OrderDTO getOrder( int barcodeNumber);
    public Collection<OrderDTO> getAllOrders();
    public OrderDTO modifyOrder(String name, String description, byte[] image, int barcodeNumber, double price,
                                int quantityInStock, String categoryName, String reviewTitle,
                                int reviewNumberOfStars, LocalDateTime date);
}

