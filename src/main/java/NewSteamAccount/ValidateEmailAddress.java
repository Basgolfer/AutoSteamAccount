package NewSteamAccount;

import org.openqa.selenium.*;
import java.io.IOException;

public class ValidateEmailAddress extends CreateSteamAccount{

    public ValidateEmailAddress(WebDriver driver) throws IOException {
        chrome = driver;
    }

    public void signIn() throws InterruptedException {
        goToGmailWebsite();
        setEmailOrPhoneTextBox();
        clickNextButton();
        setPasswordTextBox();
        clickNextButton2ndTime();
        clickEmail();
        clickCreateMyAccount();
        toTab(1);
        DeleteEmail();
    }

    private void DeleteEmail() throws InterruptedException {
        WebElement element = chrome.findElement(By.xpath("//*[@id=\":5\"]/div[2]/div[1]/div/div[2]/div[3]/div"));
        element.click();
    }

    private void clickCreateMyAccount() throws InterruptedException {
        WebElement element = chrome.findElement(By.xpath("//a/span[contains(./text(),\"Create My Account\")]"));
        sleep();
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            element = chrome.findElement(By.xpath("//a/span[contains(./text(),\"Create My Account\")]"));
            element.click();
        }
    }

    private void clickEmail() {
        WebElement element = chrome.findElement(By.id(":36"));
        element.click();
    }

    private WebElement getNextButton2ndTime() {
        return chrome.findElement(By.xpath("//span[text()='Next']"));
    }

    private void clickNextButton2ndTime() throws InterruptedException {
        getNextButton2ndTime().click();
        sleep();
    }

    private void goToGmailWebsite() {
        ((JavascriptExecutor)chrome).executeScript("window.open()");
        toTab(1);
        chrome.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    }

    private WebElement getEmailOrPhoneTextBox() {
        return chrome.findElement(By.id("identifierId"));
    }

    private void setEmailOrPhoneTextBox() {
        getEmailOrPhoneTextBox().sendKeys(emailPasswordFetcher.getEmail());
    }

    private WebElement getNextButton() {
        return chrome.findElement(By.id("identifierNext"));
    }

    private void clickNextButton() throws InterruptedException {
        getNextButton().click();
        sleep();
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }

    private WebElement getPasswordTextBox() {
        return chrome .findElement(By.name("password"));
    }

    private void setPasswordTextBox() {
        getPasswordTextBox().sendKeys(emailPasswordFetcher.getPassword());
    }


}
