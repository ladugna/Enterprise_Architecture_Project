package retail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.*;

import retail.contract.CustomerRequest;
import retail.domain.Customer;
import retail.logging.ILogger;
import retail.repository.CustomerRepository;
import retail.service.adapter.CustomerAdapter;
import retail.service.adapter.CustomerDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private ILogger logger;
    final String role = "CUSTOMER";

    public CustomerDTO getCustomer(String firstName, String lastName) {
        Customer customer = customerRepository.findTopByFirstNameAndLastNameAndRoles(firstName, lastName, role);
        if (customer == null) return null;
        return CustomerAdapter.getCustomerDTOFromCustomer(customer);
    }

    public boolean invalidateUser(String username, String role) {
        Customer customer = customerRepository.findByUsernameAndRoles(username, role);
        if (customer == null) return true;
        else return false;
    }

    public CustomerDTO addCustomer(CustomerRequest customerRequest, String encodedPassword,
                                   String passwordSalt, String passwordHash, String role) throws RuntimeException {
        try {
            Customer customer = customerRepository.findByUsername(customerRequest.getUsername());
            if (customer != null) {
                logger.log("User with username = " + customer.getUsername() + "Role =" + role +
                        " is already exist");
                return null;
            }
            customer = new Customer(customerRequest.getFirstName(), customerRequest.getLastName());
            customer.setUsername(customerRequest.getUsername());
            customer.setEmail(customerRequest.getEmail());
            customer.setPassword(encodedPassword);
            customer.setPasswordSalt(passwordSalt);
            customer.setPasswordHash(passwordHash);
            Set<String> roles = new HashSet<>();
            roles.add(role);
            customer.setRoles(roles);
            customerRepository.save(customer);
            logger.log("Add User with username = " + customer.getUsername() + "Role =" + role);
            return CustomerAdapter.getCustomerDTOFromCustomer(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

    public CustomerDTO updateCustomer(long customerId, CustomerRequest customerRequest) throws RuntimeException {
        try {
            Customer customer = customerRepository.findByIdAndRoles(customerId, role);
            //Customer customer = customerOptional.orElse(null);
            if (customer == null) {
                return null;
            }
            customer.setFirstName(customerRequest.getFirstName());
            customer.setLastName(customerRequest.getLastName());
            customer.setEmail(customerRequest.getEmail());
            //customer.setUsername(customerRequest.getUsername());
            customerRepository.save(customer);
            logger.log("Update User with username = " + customer.getUsername() + "Role =" + role);
            return CustomerAdapter.getCustomerDTOFromCustomer(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

    public CustomerDTO getCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsernameAndRoles(username, role);
        if (customer == null) {
            return null;
        }
        return CustomerAdapter.getCustomerDTOFromCustomer(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findByRoles(role);
        return CustomerAdapter.getAllCustomerDTOsFromCustomers(customers);
    }

    public CustomerDTO deleteCustomer(String username) throws RuntimeException {
        try {
            Customer customer = customerRepository.findByUsernameAndRoles(username, role);
            if (customer == null) {
                return null;
            }
            CustomerDTO customerDTO = CustomerAdapter.getCustomerDTOFromCustomer(customer);
            customerRepository.deleteByUsernameAndRoles(username, role);
            logger.log("Delete User with username = " + username);

            return customerDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

    @Override
    public CustomerDTO updateCustomerPassword(long customerId, String encodedPassword,
                                              String saltedPassword, String hashedPassword) throws RuntimeException {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            Customer customer = customerOptional.orElse(null);
            if (customer == null) {
                return null;
            }
            customer.setPassword(encodedPassword);
            customer.setPasswordSalt(saltedPassword);
            customer.setPasswordHash(hashedPassword);
            customerRepository.save(customer);
            logger.log("Update Password for User with username = " + customer.getUsername());

            return CustomerAdapter.getCustomerDTOFromCustomer(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CustomerDTO> getAllUsers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerAdapter.getAllCustomerDTOsFromCustomers(customers);
    }

    @Override
    public CustomerDTO deleteCustomerById(long customerId) {
        try {
            Customer customer = customerRepository.findByIdAndRoles(customerId, role);
            if (customer == null) {
                return null;
            }
            CustomerDTO customerDTO = CustomerAdapter.getCustomerDTOFromCustomer(customer);
            customerRepository.deleteByIdAndRoles(customerId, role);
            logger.log("Delete User with id = " + customerId);
            return customerDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(e.getMessage());
            throw e;
        }
    }

}
