package retail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import retail.constant.Action;
import retail.domain.OrderAction;
import retail.service.Sender;

@SpringBootApplication
@EnableAuthorizationServer
@EnableKafka
public class OnlineRetailSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineRetailSystemApplication.class, args);
	}

}
