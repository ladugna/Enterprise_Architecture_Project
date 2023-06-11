package ORS_Shipping.service;

import ORS_Shipping.domain.Email;
import ORS_Shipping.domain.OrderAction;
import ORS_Shipping.domain.TraceRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    EmailService emailService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @KafkaListener(topics = "orderTopic")
    public void receiveOrder(String orderActionString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderAction orderAction = objectMapper.readValue(orderActionString, OrderAction.class);

            switch (orderAction.getAction()) {
                case ORDER_PLACED -> emailService.saveEmail(new Email(orderAction.getEmail(),"Order Placed", "Dear " + orderAction.getUsername() + ". Your order has been placed.", LocalDateTime.now()));
                case ORDER_SHIPPED -> emailService.saveEmail(new Email(orderAction.getEmail(),"Order Shipped", "Dear " + orderAction.getUsername() + ". Your order has been shipped.", LocalDateTime.now()));
                case ORDER_PROCESSED -> emailService.saveEmail(new Email(orderAction.getEmail(),"Order Processed", "Dear " + orderAction.getUsername() + ". Your order has been processed.", LocalDateTime.now()));
                case ORDER_DELIVERED -> emailService.saveEmail(new Email(orderAction.getEmail(),"Order Delivered", "Dear " + orderAction.getUsername() + ". Your order has been delivered.", LocalDateTime.now()));
                case ORDER_RETURNED -> emailService.saveEmail(new Email(orderAction.getEmail(),"Order Returned", "Dear " + orderAction.getUsername() + ". Your order has been returned.", LocalDateTime.now()));
                default -> log.warn("Incorrect order action provided.");
            }

            log.info("Receiver received message: " + orderActionString);
            TraceRecord traceRecord = new TraceRecord("receiveOrder", "info", orderActionString, LocalDateTime.now());
            publisher.publishEvent(traceRecord);

        } catch (Exception e) {
            log.error("Kafka receiver: " + e.getMessage());
            TraceRecord traceRecord = new TraceRecord("receiveOrder", "error", e.getMessage(), LocalDateTime.now());
            publisher.publishEvent(traceRecord);
        }
    }

    //schedule for every 5 minutes
    @Scheduled(cron = "0 0/5 * * * ?")
    public void processShippingQueue(){
        try
        {
            log.info("Processing Shipping Queue");

            List<Email> emails = emailService.getEmails();
            for(Email email: emails)
            {
                emailService.sendEmail(email);
                emailService.deleteEmailById(email.getId());
                log.info("Email sent to: " + email.getAddress());
                TraceRecord traceRecord = new TraceRecord("processShippingQueue", "info", "Processed Shipping Queue", LocalDateTime.now());
                publisher.publishEvent(traceRecord);
            }

        }catch(Exception e){
            log.error("Process Shipping Queue: " + e.getMessage());
            TraceRecord traceRecord = new TraceRecord("processShippingQueue", "error", e.getMessage(), LocalDateTime.now());
            publisher.publishEvent(traceRecord);
        }
    }
}
