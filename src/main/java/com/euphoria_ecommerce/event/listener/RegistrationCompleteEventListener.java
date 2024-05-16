package com.euphoria_ecommerce.event.listener;

import com.euphoria_ecommerce.dto.MailBody;
import com.euphoria_ecommerce.dto.RegisterRequest;
import com.euphoria_ecommerce.event.RegistrationCompleteEvent;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.service.AuthenticationService;
import com.euphoria_ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final AuthenticationService authenticationService;
    private RegisterRequest request;
    private final JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //1. Get the newly registered user
        User user = event.getUser();
        //2. Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();
        //3. Save the verification token for the user
//        authenticationService.saveUserVerificationToken(user, verificationToken);
        //4. Build the verification url to be sent to the user
        String url = event.getApplicationUrl() + "/api/user/register/verifyEmail?token=" + verificationToken;
        //5. Send the email
        try {
            sendVerificationEmail(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration : {}", url);
    }

    public void sendVerificationEmail(String url) throws Exception {

        String subject = "Email Verification";
        String mailContent = "<p>Hi, Thank you for registering with us," + "" +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +
                "<p> Thank you <br> Euphoria";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("");
        messageHelper.setTo(request.email());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);

    }

}
