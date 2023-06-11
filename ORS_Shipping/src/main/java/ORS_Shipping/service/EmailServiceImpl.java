package ORS_Shipping.service;

import ORS_Shipping.domain.Email;
import ORS_Shipping.respository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailRepository emailRepository;

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        mailSender.send(message);
    }

    @Override
    public List<Email> getEmails() {
        return emailRepository.findAllByOrderByDateAsc();
    }

    @Override
    public void saveEmail(Email email) {
        emailRepository.save(email);
    }

    @Override
    public void deleteEmailById(long id) {
        emailRepository.deleteById(id);
    }
}
