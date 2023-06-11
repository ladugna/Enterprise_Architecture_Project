package retail.repository;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import retail.contract.AddressResponse;
import retail.domain.Address;
import retail.repository.AddressRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper mapper;
    @Test
public void findAddressByCityTest(){
        Address address=new Address("1000 N 4th St", "144", "fairfield", "52556");
        entityManager.persist(address);
        entityManager.flush();
     AddressResponse found= addressRepository.findAddressByCity(address.getCity())
             .map(ent->mapper.map(ent, AddressResponse.class)).get();
     assertThat(address.getCity()).isEqualTo(found.getCity());

    }
}
