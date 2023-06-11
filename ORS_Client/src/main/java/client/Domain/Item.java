package client.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

    private long Id;
    private String Name;
    private String Description;
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


}
