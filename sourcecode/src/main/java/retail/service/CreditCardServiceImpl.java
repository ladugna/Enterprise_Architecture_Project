package retail.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retail.contract.CreditCardRequest;
import retail.domain.CreditCard;
import retail.domain.Customer;
import retail.repository.CreditCardRepository;
import retail.repository.CustomerRepository;
import retail.service.adapter.CreditCardDTO;
import retail.util.Util;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public Page<CreditCardDTO> findAll(Pageable pageable) {

        String username = Util.getLoggedInUserName();

        if (username != null) {

            Customer customer = customerRepository.findByUsername(username);

            if(customer != null){

                return new PageImpl<>(customer.getCreditCards().stream().map(creditCard -> mapper.map(creditCard, CreditCardDTO.class)).collect(Collectors.toList()));
            }
        }

        return null;

    }

    @Override
    public CreditCardDTO findById(long id) {
        String username = Util.getLoggedInUserName();

        if (username != null) {

            Customer customer = customerRepository.findByUsername(username);

            if(customer != null){
                Optional<CreditCard> cc = customer.getCreditCards().stream().filter(c -> c.getId() == id).findFirst();

                if(cc.isPresent())
                {
                    return mapper.map(cc.get(), CreditCardDTO.class);
                }
            }
        }

        return null;
    }

    @Override
    @Transactional
    public CreditCardDTO saveCreditCard(CreditCardRequest creditCardRequest) {
        String username = Util.getLoggedInUserName();

        if (username != null) {

            Customer customer = customerRepository.findByUsername(username);

            if(customer != null){
                CreditCard cc = mapper.map(creditCardRequest, CreditCard.class);

                customer.getCreditCards().add(cc);
                //customerRepository.save(customer);

                return mapper.map(cc, CreditCardDTO.class);
            }
        }

        return null;
    }

    @Override
    @Transactional
    public void deleteCreditCardById(long id) {

        String username = Util.getLoggedInUserName();

        if (username != null) {

            Customer customer = customerRepository.findByUsername(username);

            if(customer != null){
                Optional<CreditCard> cc = customer.getCreditCards().stream().filter(c -> c.getId() == id).findFirst();

                if(cc.isPresent())
                {
                    customer.getCreditCards().remove(cc.get());
                    creditCardRepository.deleteById(id);
                }
            }
        }
    }

    @Override
    @Transactional
    public CreditCardDTO editCreditCard(CreditCardRequest creditCardRequest) {

        String username = Util.getLoggedInUserName();

        if (username != null) {

            Customer customer = customerRepository.findByUsername(username);

            if(customer != null){
                Optional<CreditCard> cc = customer.getCreditCards().stream().filter(c -> c.getId() == creditCardRequest.getId()).findFirst();

                if(cc.isPresent())
                {
                    mapper.map(creditCardRepository.save(mapper.map(creditCardRequest, CreditCard.class)),CreditCardDTO.class);
                }
            }
        }

        return null;
    }
}

