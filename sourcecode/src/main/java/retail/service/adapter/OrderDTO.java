package retail.service.adapter;


import javax.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class OrderDTO {
    private String Name;
    private String Description;
    private byte[] Image;
    private int BarcodeNumber;
    private double Price;
    private int QuantityInStock;

    private Collection<CategoryDTO> Categories=new ArrayList<>();

    private Collection<OrderDTO> Items=new ArrayList<>();

    private Collection<ReviewDTO> Reviews=new ArrayList<>();

    public OrderDTO(String name, String description, byte[] image, int barcodeNumber, double price, int QuantityInStock) {
        Name = name;
        Description = description;
        Image = image;
        BarcodeNumber = barcodeNumber;
        Price = price;
    }

    public OrderDTO() {
    }
}
