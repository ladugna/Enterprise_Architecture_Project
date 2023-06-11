package ORS_Shipping.service;


import ORS_Shipping.domain.OrderAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public interface ShippingService{
    void receiveOrder(String orderActionString);
    void processShippingQueue();
}
