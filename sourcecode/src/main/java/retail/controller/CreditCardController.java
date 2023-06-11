package retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import retail.contract.CreditCardRequest;
import retail.contract.ItemRequest;
import retail.service.CreditCardServiceImpl;
import retail.service.ItemServiceImpl;
import retail.service.adapter.CreditCardDTO;
import retail.service.adapter.ItemDTO;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
    @Autowired
    CreditCardServiceImpl creditCardService;

    @PostMapping("")
    public ResponseEntity<?> SaveCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
        return new ResponseEntity<>(creditCardService.saveCreditCard(creditCardRequest), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> UpdateCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
        return new ResponseEntity<>(creditCardService.editCreditCard(creditCardRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "")
    public ResponseEntity<?> GetCreditCards(Pageable pageable) {
        Page<CreditCardDTO> creditCards = creditCardService.findAll(pageable);

        if (creditCards.isEmpty()) {
            return new ResponseEntity<>("No credit cards found.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(creditCards, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> GetCreditCardById(@PathVariable long id) {
        CreditCardDTO creditCard = creditCardService.findById(id);

        if (creditCard == null) {
            return new ResponseEntity<>("No credit card found.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(creditCard, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteItemById(@PathVariable long id) {
        creditCardService.deleteCreditCardById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
