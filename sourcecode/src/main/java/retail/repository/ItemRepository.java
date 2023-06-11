package retail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import retail.domain.Item;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);

    @Query("Select i from Item i where i.Price = :price")
    Page<Item> findAllByPrice(@Param("price") double price, Pageable pageable);
    @Query("Select i from Item i where i.Category = :category")
    Page<Item> findAllByCategory(@Param("category") String category, Pageable pageable);
    @Query("Select i from Item i where i.Name = :name")
    Page<Item> findAllByName(@Param("name") String name, Pageable pageable);

    @Query("Select i from Item i where i.BarcodeNumber = :barcodeNumber")
    Optional<Item> findByBarcodeNumber(int barcodeNumber);

}
