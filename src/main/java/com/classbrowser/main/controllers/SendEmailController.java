package com.classbrowser.main.controllers;

import com.classbrowser.main.pojo.ResponseInformation;
import com.classbrowser.main.pojo.UserInformation;
import com.sendgrid.*;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
public class SendEmailController {

    private static Logger log = Logger.getLogger(SendEmailController.class);
    final String EMAIL = "robertvo79@gmail.com";
    final String FROM_EMAIL = "feedback@robertvo.me";
    @RequestMapping(value = "/send-email", method = RequestMethod.POST, consumes = "application/*")
    public
    @ResponseBody
    ResponseInformation sendEmailToAdmin(@RequestBody UserInformation userInformation) throws IOException {
        log.info("Sending email to " + EMAIL + ".");
        ResponseInformation ri = new ResponseInformation();

        Email from = new Email(FROM_EMAIL);
        String subject = "Feedback about Class Browser UH!";
        Email to = new Email(EMAIL);
        Content content = new Content("text/html", "<html>\n" +
                "<head>\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Feedback from " + userInformation.getFirstAndLastName() + ",\n" +
                "<br /><br/>\n" +
                userInformation.getFeedback() + "\n" +
                "<br /><br/>\n" +
                "" + userInformation.getFirstAndLastName() +
                " &lt;" + userInformation.getTheirEmailAddress() + "&gt;<br/>\n" +
                "This message was sent on [" + new java.util.Date() + "]." +
                "</body>\n" +
                "</html>");

        try {
            Mail mail = new Mail(from, subject, to, content);

            Optional<String> sendGridApiKey = Optional.ofNullable(System.getProperty("SENDGRID_API_KEY"));

            SendGrid sg;
            if(sendGridApiKey.isPresent()) {
                sg = new SendGrid(sendGridApiKey.get());
            }
            else {
                sg = new SendGrid("");
            }
            Request request = new Request();
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);

            log.debug("Response statusCode: " + response.statusCode);
            log.debug("Response body: " + response.body);
            log.debug("Response headers: " + response.headers);

            final String SUCCESS_FEEDBACK = "Feedback sent!";
            log.info(SUCCESS_FEEDBACK);
            ri.setMessage(SUCCESS_FEEDBACK);
            ri.setStatusCode(1);
        }
        catch (IOException ex) {
            final String FAILED_FEEDBACK = "Feedback was not sent. Please try again.";
            log.info(FAILED_FEEDBACK);
            ri.setMessage(FAILED_FEEDBACK);
            ri.setStatusCode(-1);
        }
        return ri;
    }
}
