package client.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateItemRequest {

    String name;
    String description;
    int barcodeNumber;
    double price;
    int quantityInStock;
    String category;

    public CreateItemRequest(String name, String description, int barcodeNumber, double price, int quantityInStock, String category) {
        this.name = name;
        this.description = description;
        this.barcodeNumber = barcodeNumber;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
    }
}
