package NewSteamAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.powermock.reflect.Whitebox;

import java.io.IOException;
import java.lang.reflect.Field;

public class CreateSteamAccountTest {
    private CreateSteamAccount createSteamAccount;
    private WebDriver chromeDriver;
    private EmailPasswordFetcher emailPasswordFetcher;

    @Before
    public void setUp() throws IOException {
        createSteamAccount = new CreateSteamAccount();
        Field chromeField = Whitebox.getField(CreateSteamAccount.class, "chrome");
        chromeDriver = (WebDriver) Whitebox.getFieldValue(chromeField, createSteamAccount);
        emailPasswordFetcher = new EmailPasswordFetcher();
    }

    @After
    public void tearDown() {
        chromeDriver.quit();
    }

    private void goToWebsite() throws Exception {
        Whitebox.invokeMethod(createSteamAccount, "goToSteamWebsite");
    }

    @Test
    public void makeAccountTest() throws InterruptedException {
        createSteamAccount.makeAccount();
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("email")).getAttribute("value"));
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("reenter_email")).getAttribute("value"));
        Assert.assertTrue(chromeDriver.findElement(By.id("i_agree_check")).isSelected());
    }

    @Test
    public void goToWebsiteTest() throws Exception {
        goToWebsite();
        String expected = "Create an Account";
        String actual = chromeDriver.getTitle();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fillInInformationTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(createSteamAccount, "fillInInformation");
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("email")).getAttribute("value"));
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("reenter_email")).getAttribute("value"));
        Assert.assertTrue(chromeDriver.findElement(By.id("i_agree_check")).isSelected());
    }

    @Test
    public void getCurrentEmailTextBoxTest() throws Exception {
        goToWebsite();
        WebElement actual = Whitebox.invokeMethod(createSteamAccount, "getCurrentEmailTextBox");
        WebElement expected = chromeDriver.findElement(By.id("email"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setEmailTextBoxTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(createSteamAccount, "setEmailTextBox");
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("email")).getAttribute("value"));
    }

    @Test
    public void getReEnterEmailTextBoxTest() throws Exception {
        goToWebsite();
        WebElement actual = Whitebox.invokeMethod(createSteamAccount, "getReEnterEmailTextBox");
        WebElement expected = chromeDriver.findElement(By.id("reenter_email"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setReEnterEmailTextBoxTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(createSteamAccount, "setReEnterEmailTextBox");
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("reenter_email")).getAttribute("value"));
    }

    @Test
    public void getConfirmationCheckBoxTest() throws Exception {
        goToWebsite();
        WebElement actual = Whitebox.invokeMethod(createSteamAccount, "getConfirmationCheckBox");
        WebElement expected = chromeDriver.findElement(By.id("i_agree_check"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkConfirmationCheckBoxTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(createSteamAccount, "checkConfirmationCheckBox");
        Assert.assertTrue(chromeDriver.findElement(By.id("i_agree_check")).isSelected());
    }

    @Test
    public void getContinueButtonTest() throws Exception {
        goToWebsite();
        WebElement actual = Whitebox.invokeMethod(createSteamAccount, "getContinueButton");
        WebElement expected = chromeDriver.findElement(By.xpath("//span[text()='Continue']"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void clickContinueButtonTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(createSteamAccount, "clickContinueButton");
        String expected = "Please enter a valid email address.\n" +
                "Please fill in the Re-enter email address field.\n" +
                "And find more errors highlighted below.\n";
        Assert.assertEquals(expected, chromeDriver.findElement(By.id("error_display")).getAttribute("innerText"));
    }
}