package retail.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import retail.contract.AddressResponse;
import retail.domain.Address;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a where a.addressLine = :addressLine and a.aptNo = :aptNo")
    Address findAddressesByAddressLineAndAptNo(String addressLine, String aptNo);

    @Query("select c from Address c where c.city=:city")
    Optional<Address> findAddressByCity(@PathVariable("city") String city);
}

