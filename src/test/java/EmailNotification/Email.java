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

    public static void execute(String subjectMessage, ErrorMessage error) throws Exception

    {
        ArrayList<File> fileList = error.getFiles();
        String content = error.getErrorMessage();

        String[] to = {"s.konoplyaniy@gmail.com", "s.konoplyaniy@gmail.com"};

        Email.sendMail(
                "testobjectsdream@gmail.com",
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
                content,
                fileList);
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
                                   ArrayList<File> fileList)
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


                MimeBodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();

//for add all files to attachment
                for (int i = 0; i < fileList.size(); i ++) {
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(fileList.get(i));
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(fileList.get(i).getName());
//                    messageBodyPart.setFileName(attachList.get(i) + ".png");
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

