package retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import retail.contract.CustomerRequest;
import retail.contract.LoginRequest;
import retail.error.CustomErrorType;
import retail.service.CustomerServiceImpl;
import retail.service.UserDetailsServiceImpl;
import retail.service.adapter.CustomerDTO;
import retail.util.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<CustomerDTO> customers = customerService.getAllUsers();
        return new ResponseEntity<List<CustomerDTO>>(customers, HttpStatus.OK);
    }
    @PostMapping("/admins")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAdmin(@RequestBody CustomerRequest customerRequest) throws Exception{
        try {
            String salt = "randomsalt";
            String saltedPassword = customerRequest.getPassword() + salt;
            String hashedPassword = passwordEncoder.encode(saltedPassword);
            String encodedPassword = passwordEncoder.encode(customerRequest.getPassword());
            CustomerDTO customer = customerService.addCustomer(customerRequest,
                    encodedPassword, saltedPassword, hashedPassword, "ADMIN");
            if(customer == null){
                return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with username = "
                        + customerRequest.getUsername() + " is already exist"), HttpStatus.FOUND);
            }
            return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw  e;
        }
    }

    @PostMapping("/customers")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerRequest) throws Exception{
        try {

            String salt = "randomsalt";
            String saltedPassword = customerRequest.getPassword() + salt;
            String hashedPassword = passwordEncoder.encode(saltedPassword);
            String encodedPassword = passwordEncoder.encode(customerRequest.getPassword());
            CustomerDTO customer = customerService.addCustomer(customerRequest,
                    encodedPassword, saltedPassword, hashedPassword, "CUSTOMER");
            if(customer == null){
                return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with username = "
                        + customerRequest.getUsername() + " is already exist"), HttpStatus.FOUND);
            }
            return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        UserDetails customer = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if(customer == null){
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("The Customer with username = "
                    + loginRequest.getUsername() + " is not available"), HttpStatus.NOT_FOUND);
        }
        boolean passwordsMatch = passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword());
        if (passwordsMatch == true){
            return new ResponseEntity<String>("Login Successful", HttpStatus.OK );
        }

        return new ResponseEntity<String>("Password is wrong", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        //SecurityContextHolder.clearContext();
        return new ResponseEntity<String>("Logout Successful", HttpStatus.NOT_FOUND);
    }
}
