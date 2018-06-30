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

public class ValidateEmailAddressTest {

    private ValidateEmailAddress validateEmailAddress;
    private WebDriver chromeDriver;
    private EmailPasswordFetcher emailPasswordFetcher;

    @Before
    public void setUp() throws IOException {
        validateEmailAddress = new ValidateEmailAddress();
        Field firefoxField = Whitebox.getField(ValidateEmailAddress.class, "chromeDriver");
        chromeDriver = (WebDriver) Whitebox.getFieldValue(firefoxField, validateEmailAddress);
        Field emailPasswordFetcherField = Whitebox.getField(ValidateEmailAddress.class, "emailPasswordFetcher");
        emailPasswordFetcher = (EmailPasswordFetcher) Whitebox.getFieldValue(emailPasswordFetcherField, validateEmailAddress);
    }

    @After
    public void tearDown() {
        //chromeDriver.quit();
    }

    private void goToWebsite() throws Exception{
        Whitebox.invokeMethod(validateEmailAddress, "goToGmailWebsite");
    }

    @Test
    public void signInTest() throws InterruptedException {
        validateEmailAddress.signIn();
        Assert.assertEquals("Sent Mail", chromeDriver.findElement(By.xpath("//a[text()='Sent Mail']")).getText());
    }

    @Test
    public void goToGmailWebsiteTest() throws Exception {
        goToWebsite();
        String expected = "Gmail";
        String actual = chromeDriver.getTitle();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEmailOrPhoneTextBox() throws Exception {
        goToWebsite();
        WebElement actual = Whitebox.invokeMethod(validateEmailAddress, "getEmailOrPhoneTextBox");
        WebElement expected = chromeDriver.findElement(By.id("identifierId"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setEmailOrPhoneTextBox() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Assert.assertNotNull(emailPasswordFetcher);
        Assert.assertEquals(emailPasswordFetcher.getEmail(), chromeDriver.findElement(By.id("identifierId")).getAttribute("value"));
    }

    @Test
    public void getNextButtonTest() throws Exception {
        goToWebsite();
        WebElement actual = Whitebox.invokeMethod(validateEmailAddress, "getNextButton");
        WebElement expected = chromeDriver.findElement(By.id("identifierNext"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void clickNextButtonTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Assert.assertEquals("Welcome", chromeDriver.findElement(By.id("headingText")).getText());
    }

    @Test
    public void getPasswordTextBoxTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        WebElement actual = Whitebox.invokeMethod(validateEmailAddress, "getPasswordTextBox");
        WebElement expected = chromeDriver.findElement(By.name("password"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setPasswordTextBoxTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        Assert.assertEquals(emailPasswordFetcher.getPassword(), chromeDriver.findElement(By.name("password")).getAttribute("value"));
    }

    @Test
    public void getNextButton2ndTimeTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        WebElement actual = Whitebox.invokeMethod(validateEmailAddress, "getNextButton2ndTime");
        WebElement expected = chromeDriver.findElement(By.xpath("//span[text()='Next']"));
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void clickNextButton2ndTimeTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton2ndTime");
        Assert.assertEquals("Sent Mail", chromeDriver.findElement(By.xpath("//a[text()='Sent Mail']")).getText());
    }

    @Test
    public void clickEmailTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton2ndTime");
        Whitebox.invokeMethod(validateEmailAddress, "clickEmail");
        Assert.assertEquals("Hello,", chromeDriver.findElement(By.xpath("//tr/td[contains(./text(),\"Hello,\")]")).getText());
    }

    @Test
    public void clickCreateMyAccountTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton2ndTime");
        Whitebox.invokeMethod(validateEmailAddress, "clickEmail");
        Whitebox.invokeMethod(validateEmailAddress, "clickCreateMyAccount");
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//h2[contains(./text(),\" Veri\")]")).getText().contains(" Veri"));
    }

    @Test
    public void toTab1() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton2ndTime");
        Whitebox.invokeMethod(validateEmailAddress, "clickEmail");
        Whitebox.invokeMethod(validateEmailAddress, "clickCreateMyAccount");
        Whitebox.invokeMethod(validateEmailAddress, "toTab1");
        Assert.assertTrue(chromeDriver.getTitle().contains("New Steam Account Email Veri"));
    }

    @Test
    public void DeleteEmailTest() throws Exception {
        goToWebsite();
        Whitebox.invokeMethod(validateEmailAddress, "setEmailOrPhoneTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton");
        Whitebox.invokeMethod(validateEmailAddress, "setPasswordTextBox");
        Whitebox.invokeMethod(validateEmailAddress, "clickNextButton2ndTime");
        Whitebox.invokeMethod(validateEmailAddress, "clickEmail");
        Whitebox.invokeMethod(validateEmailAddress, "clickCreateMyAccount");
        Whitebox.invokeMethod(validateEmailAddress, "toTab1");
        Whitebox.invokeMethod(validateEmailAddress, "DeleteEmail");
        Assert.fail();
    }

}