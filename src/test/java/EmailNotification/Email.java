package EmailNotification;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.*;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public class Email {
    //reportFileName = TestExecutionResultFileName
    public static void execute(String reporText, String screenName) throws Exception

    {
        String path = "D:/testFolder";

        String[] to = {"s.konoplyaniy@gmail.com", "s.konoplyaniy@gmail.com"};

//
        Email.sendMail("s.konoplyaniy@gmail.com",
                "Rjyjgkzybq1989",
                "smtp.gmail.com",
                "465",
                "false",
                "true",
                true,
                "javax.net.ssl.SSLSocketFactory",
                "false",
                to,
                "Test email",
                "Contents if any",
                path,
                screenName);
    }

    public static boolean sendMail(String userName,
                                   String passWord,
                                   String host,
                                   String port,
                                   String starttls,
                                   String auth,
                                   boolean debug,
                                   String socketFactoryClass,
                                   String fallback,
                                   String[] to,
                                   String subject,
                                   String text,
                                   String attachmentPath,
                                   String attachmentName)
    {

//Object Instantiation of a properties file.
        Properties props = new Properties();

        props.put("mail.smtp.user", userName);

        props.put("mail.smtp.host", host);

        if (!"".equals(port)) {
            props.put("mail.smtp.port", port);
        }

        if (!"".equals(starttls)) {
            props.put("mail.smtp.starttls.enable", starttls);
            props.put("mail.smtp.auth", auth);
        }

        if (debug) {

            props.put("mail.smtp.debug", "true");

        } else {

            props.put("mail.smtp.debug", "false");

        }

        if (!"".equals(port)) {
            props.put("mail.smtp.socketFactory.port", port);
        }
        if (!"".equals(socketFactoryClass)) {
            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        if (!"".equals(fallback)) {
            props.put("mail.smtp.socketFactory.fallback", fallback);
        }

        try {

            Session session = Session.getDefaultInstance(props, null);

            session.setDebug(debug);

            MimeMessage msg = new MimeMessage(session);

            msg.setText(text);

            msg.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachmentName);
            multipart.addBodyPart(messageBodyPart);

            File att = new File(new File(attachmentPath), attachmentName);
            messageBodyPart.attachFile(att);

            msg.setContent(multipart);
            msg.setFrom(new InternetAddress(userName));

            for (int i = 0; i < to.length; i++) {
                msg.addRecipient(Message.RecipientType.TO, new
                        InternetAddress(to[i]));
            }

            msg.saveChanges();

            Transport transport = session.getTransport("smtp");

            transport.connect(host, userName, passWord);

            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();

            return true;

        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
    }
}

