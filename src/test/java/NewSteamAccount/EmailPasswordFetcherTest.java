package NewSteamAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;

public class EmailPasswordFetcherTest {
    private EmailPasswordFetcher emailPasswordFetcher;
    private String email;
    private String password;

    @Before
    public void setUp() throws Exception {
        emailPasswordFetcher = new EmailPasswordFetcher();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Brian\\IdeaProjects\\AutoSteamAccount\\src\\main\\resources\\Email_Info.txt"));
        email = bufferedReader.readLine();
        password = bufferedReader.readLine();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getEmailTest() throws Exception {
        //System.out.println(emailPasswordFetcher.getEmail());
        Assert.assertEquals(email, emailPasswordFetcher.getEmail());
    }

    @Test
    public void getPasswordTest() throws Exception {
        //System.out.println(emailPasswordFetcher.getPassword());
        Assert.assertEquals(password, emailPasswordFetcher.getPassword());
    }

}