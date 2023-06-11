package retail.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@DiscriminatorValue("SingleItem")
@NoArgsConstructor
public class SingleItem extends Item{

    public SingleItem(long id, String name, String description, byte[] image, int barcodeNumber, double price, int quantityInStock, String category) {
        super(id, name, description, image, barcodeNumber, price, quantityInStock, category);
    }

    public SingleItem(String name, String description, byte[] image, int barcodeNumber, double price, int quantityInStock, String category){
        super(name,description,image, barcodeNumber,price, quantityInStock, category);
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }

    @Override
    public String toString() {
        return "SingleItem{} " + super.toString();
    }
}
