package retail.service;

import retail.contract.CustomerRequest;
import retail.domain.Customer;
import retail.service.adapter.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public CustomerDTO getCustomer(String firstName, String lastName);
    public CustomerDTO addCustomer(CustomerRequest customerRequest, String encodedPassword,
                                   String passwordSalt, String passwordHash, String role);
    public CustomerDTO updateCustomer(long customerId, CustomerRequest customerRequest);
    public CustomerDTO getCustomerByUsername(String username);
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO deleteCustomer(String username);

    CustomerDTO updateCustomerPassword(long customerId, String encodedPassword, String saltedPassword, String hashedPassword);

    public List<CustomerDTO> getAllUsers();

    public CustomerDTO deleteCustomerById(long customerId);

    public boolean invalidateUser(String username, String role) ;
}
