package retail.service.adapter;

import lombok.Data;
import retail.domain.Address;
import retail.domain.ShoppingCart;

@Data
public class CustomerDTO  {
	private long id;
	private String firstName;
	private String lastName;
	private String password;
	private String username;
	private String email;
	private long cartId;
	private Address BillingAddressId;
	private Address PreferredShippingAddressId;


	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

