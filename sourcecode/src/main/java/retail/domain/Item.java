package retail.domain;


import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")

public abstract class Item {

    @Id
    @GeneratedValue
    private long Id;
    private String Name;
    private String Description;
    @Lob
    private byte[] Image;
    private int BarcodeNumber;
    private double Price;
    private int QuantityInStock;
    private String Category;

    public Item(long id, String name, String description, byte[] image, int barcodeNumber, double price, int quantityInStock, String category) {
        Id = id;
        Name = name;
        Description = description;
        Image = image;
        BarcodeNumber = barcodeNumber;
        Price = price;
        QuantityInStock = quantityInStock;
        Category = category;
    }

    public Item(String name, String description, byte[] image, int barcodeNumber, double price, int quantityInStock, String category) {
        Name = name;
        Description = description;
        Image = image;
        BarcodeNumber = barcodeNumber;
        Price = price;
        QuantityInStock = quantityInStock;
        Category = category;
    }

    public void addItem(Item item){throw new UnsupportedOperationException();}
    public void removeItem(Item item){throw new UnsupportedOperationException();}

    public abstract double calculatePrice();
}
