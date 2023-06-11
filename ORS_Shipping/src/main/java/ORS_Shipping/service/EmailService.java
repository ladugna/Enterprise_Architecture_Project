package ORS_Shipping.service;

import ORS_Shipping.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailService {
    void sendEmail(Email email);
    List<Email> getEmails();
    void saveEmail(Email email);
    void deleteEmailById(long id);
}
