package EmailNotification;

import org.apache.commons.io.FileUtils;

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

//this class can send email with attachments
public class Email {
    //reportFileName = TestExecutionResultFileName

    public static void execute(String subjectMessage, ErrorMessage error) throws Exception

    {
        ArrayList<String> fileNames = error.getFileNames();
        fileNames.size();
        String path =  "/home/geser/Automation/Sreenshot/TestObjects/Errors/" + error.getFileFolder();

        String[] to = {"s.konoplyaniy@gmail.com", "s.konoplyaniy@gmail.com"};

        Email.sendMail(
                "s.konoplyaniy@gmail.com",
                "",
                "smtp.gmail.com",
                "465",
                "false",
                "true",
                true,
                "javax.net.ssl.SSLSocketFactory",
                "false",
                to,
                subjectMessage,
                "Contents if any", //here must be text message on email (expected  errors describes) !!!!
                path,
                fileNames);
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
                                   ArrayList<String> attachlist)
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

                DataSource source = new FileDataSource(attachmentPath);
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
//for add all files to attachment
                for (int i = 0; i < attachlist.size(); i ++) {
                    messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(attachlist.get(i));
                    multipart.addBodyPart(messageBodyPart);
                    i++;
                }

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

