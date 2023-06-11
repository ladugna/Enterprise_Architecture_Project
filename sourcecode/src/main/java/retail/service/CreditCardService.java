package retail.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import retail.contract.CreditCardRequest;
import retail.contract.ItemRequest;
import retail.service.adapter.CreditCardDTO;

public interface CreditCardService {
    Page<CreditCardDTO> findAll(Pageable pageable);
    CreditCardDTO findById(long id);
    CreditCardDTO saveCreditCard(CreditCardRequest creditCardRequest);
    void deleteCreditCardById(long id);
    CreditCardDTO editCreditCard(CreditCardRequest creditCardRequest);
}

