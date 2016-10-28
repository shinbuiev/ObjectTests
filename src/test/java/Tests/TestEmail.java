package Tests;

import EmailNotification.Email;
import org.testng.annotations.Test;

/**
 * Created by Sergiy.K on 28-Oct-16.
 */
public class TestEmail {
    @Test
    public void sendEmail() throws Exception {
        Email email = new Email();



        email.execute("fdsfdsfsfa", "new_project_commits.txt");

    }
}
