package retail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import retail.contract.CustomerRequest;
import retail.contract.LoginRequest;
import retail.contract.addCustomerRequest;
import retail.domain.Customer;
import retail.error.CustomErrorType;
import retail.service.CustomerServiceImpl;
import retail.service.UserDetailsServiceImpl;
import retail.service.adapter.CustomerDTO;
import retail.util.Util;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	UserDetailsServiceImpl userDetailsService;


	//@Autowired
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@RequestMapping(value = { "/customer" }, produces = "application/json")
	public Map<String, Object> customer(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
		return userInfo;
	}



	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers(){
		List<CustomerDTO> customers = customerService.getAllCustomers();
		return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
	}

	@PutMapping("/customers/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable long customerId,
											@RequestBody CustomerRequest customerRequest){
		CustomerDTO customer =customerService.updateCustomer(customerId, customerRequest);
		if (customer == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with id = "
					+ customerId + " is not available"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CustomerDTO> (customer, HttpStatus.OK);
	}
	@DeleteMapping("/customers/names/{Username}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String Username){
		if(customerService.invalidateUser(Util.getLoggedInUserName(), "ADMIN")){
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("access denied"), HttpStatus.UNAUTHORIZED);
		}
		CustomerDTO customer = customerService.deleteCustomer(Username);
		if(customer == null){
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with name = "
					+ Username + " is not available"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Delete Successful", HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long customerId){
		if(customerService.invalidateUser(Util.getLoggedInUserName(), "ADMIN")){
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("access denied"), HttpStatus.UNAUTHORIZED);
		}
		CustomerDTO customer = customerService.deleteCustomerById(customerId);
		if(customer == null){
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with name = "
					+ customerId + " is not available"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Delete Successful", HttpStatus.OK);
	}
	@PutMapping("/customers/{customerId}/password")
	public ResponseEntity<?> updateCustomer(@PathVariable long customerId,
											@RequestBody String password){

		String salt = "randomsalt";
		String saltedPassword = password + salt;
		String hashedPassword = passwordEncoder.encode(saltedPassword);
		String encodedPassword = passwordEncoder.encode(password);
		CustomerDTO customer = customerService.updateCustomerPassword(customerId, encodedPassword, saltedPassword, hashedPassword);
		if (customer == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with id = "
					+ customerId + " is not available"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String> ("updated password", HttpStatus.OK);
	}

}
