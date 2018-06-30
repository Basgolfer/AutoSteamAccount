package NewSteamAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;

public class ValidateEmailAddress {
    private WebDriver chromeDriver;
    private EmailPasswordFetcher emailPasswordFetcher;

    public ValidateEmailAddress() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Brian\\IdeaProjects\\AutoSteamAccount\\src\\main\\resources\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        emailPasswordFetcher = new EmailPasswordFetcher();
    }

    public void signIn() throws InterruptedException {
        goToGmailWebsite();
        setEmailOrPhoneTextBox();
        clickNextButton();
        setPasswordTextBox();
        clickNextButton2ndTime();
        clickEmail();
        clickCreateMyAccount();
        toTab1();
        DeleteEmail();
    }

    private void DeleteEmail() throws InterruptedException {
        WebElement element = chromeDriver.findElement(By.cssSelector(".T-IJ-J5-Ji.nX T-I-ax7.T-I-Js-Gs.T-I-Js-IF.W6eDmd.T-I-JW"));
        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private void toTab1() {
        ArrayList<String> tabs = new ArrayList<String>(chromeDriver.getWindowHandles());
        chromeDriver.switchTo().window(tabs.get(0));
    }

    private void clickCreateMyAccount() throws InterruptedException {
        WebElement element = chromeDriver.findElement(By.xpath("//a/span[contains(./text(),\"Create My Account\")]"));
        sleep();
        element.click();
    }

    private void clickEmail() {
        WebElement element = chromeDriver.findElement(By.xpath("//span[text()='Steam']"));
        ((JavascriptExecutor)chromeDriver).executeScript("arguments[0].click();", element);
    }

    private WebElement getNextButton2ndTime() {
        return chromeDriver.findElement(By.xpath("//span[text()='Next']"));
    }

    private void clickNextButton2ndTime() throws InterruptedException {
        getNextButton2ndTime().click();
        sleep();
    }

    private void goToGmailWebsite() {
        chromeDriver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
    }

    private WebElement getEmailOrPhoneTextBox() {
        return chromeDriver.findElement(By.id("identifierId"));
    }

    private void setEmailOrPhoneTextBox() {
        getEmailOrPhoneTextBox().sendKeys(emailPasswordFetcher.getEmail());
    }

    private WebElement getNextButton() {
        return chromeDriver.findElement(By.id("identifierNext"));
    }

    private void clickNextButton() throws InterruptedException {
        getNextButton().click();
        sleep();
    }

    private void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }

    private WebElement getPasswordTextBox() {
        return chromeDriver.findElement(By.name("password"));
    }

    private void setPasswordTextBox() {
        getPasswordTextBox().sendKeys(emailPasswordFetcher.getPassword());
    }


}
