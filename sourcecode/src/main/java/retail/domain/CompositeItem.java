package retail.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("CompositeItem")
@NoArgsConstructor
public class CompositeItem extends Item {

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Collection<Item> subItems = new ArrayList<>();

    public CompositeItem(long id, String name, String description, byte[] image, int barcodeNumber, double price, int quantityInStock, String category) {
        super(id, name, description, image, barcodeNumber, price, quantityInStock, category);
    }

    public CompositeItem(String name, String description, byte[] image, int barcodeNumber, double price, int quantityInStock, String category){
        super(name,description,image, barcodeNumber,price, quantityInStock,category);
    }

    public void addItem(Item item){
        subItems.add(item);
    }

    public void removeItem(Item item){
        subItems.remove(item);
    }

    @Override
    public double calculatePrice() {
        return subItems.stream().mapToDouble(Item::calculatePrice).sum();
    }

    @Override
    public String toString() {
        return "CompositeItem{" +
                "subItems=" + subItems +
                "} " + super.toString();
    }
}

