package retail.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomterTest {

    Customer customer;
    @BeforeEach
    public void setup() {
        customer = new Customer("Frank", "Brown");
    }
    @AfterEach
    public void cleanup(){
        customer = null;
    }
    @Test
    public void testCreateCustomer(){
        assertThat(customer.getFirstName()).isEqualTo("Frank");
        assertThat(customer.getLastName()).isEqualTo("Brown");
    }
}