package retail.service.adapter;

import retail.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter {
	public static Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO) {

		Customer customer = new Customer(customerDTO.getFirstName(), customerDTO.getLastName());
		customer.setPassword(customerDTO.getPassword());
		customer.setUsername(customerDTO.getUsername());
		customer.setEmail(customerDTO.getEmail());
		return customer;
	}

	public static CustomerDTO getCustomerDTOFromCustomer(Customer customer) {

		CustomerDTO customerDTO = new CustomerDTO(customer.getFirstName(), customer.getLastName());
		customerDTO.setUsername(customer.getUsername());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setId(customer.getId());
		customerDTO.setPassword(customer.getPassword());
		customerDTO.setCartId(customer.getCart().getCartId());
		return customerDTO;
	}
	public static List<CustomerDTO> getAllCustomerDTOsFromCustomers(List<Customer> customers){
		List<CustomerDTO> customerDTOS = new ArrayList<>();
		for(Customer customer: customers){
			customerDTOS.add(CustomerAdapter.getCustomerDTOFromCustomer(customer));
		}
		return customerDTOS;
	}

}
