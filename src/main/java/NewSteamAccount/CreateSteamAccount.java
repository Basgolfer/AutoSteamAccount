package NewSteamAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class CreateSteamAccount {

    private WebDriver chrome;
    private EmailPasswordFetcher emailPasswordFetcher;

    public CreateSteamAccount() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Brian\\IdeaProjects\\AutoSteamAccount\\src\\main\\resources\\chromedriver.exe");
        chrome = new ChromeDriver();
        emailPasswordFetcher = new EmailPasswordFetcher();
    }

    private void goToSteamWebsite() {
        chrome.get("https://store.steampowered.com/join/");
    }

    public void makeAccount() throws InterruptedException {
        goToSteamWebsite();
        fillInInformation();
        //sleep for captca entry
        sleep(15000);
        clickContinueButton();
    }

    private void fillInInformation() {
        setEmailTextBox();
        setReEnterEmailTextBox();
        checkConfirmationCheckBox();
    }

    private WebElement getCurrentEmailTextBox() {
        return chrome.findElement(By.id("email"));
    }

    private void setEmailTextBox() {
        getCurrentEmailTextBox().sendKeys(emailPasswordFetcher.getEmail());
    }

    private WebElement getReEnterEmailTextBox() {
        return chrome.findElement(By.id("reenter_email"));
    }

    private void setReEnterEmailTextBox() {
        getReEnterEmailTextBox().sendKeys(emailPasswordFetcher.getEmail());
    }

    private WebElement getConfirmationCheckBox() {
        return chrome.findElement(By.id("i_agree_check"));
    }

    private void checkConfirmationCheckBox() {
        getConfirmationCheckBox().click();
    }

    private WebElement getContinueButton() {
        return chrome.findElement(By.xpath("//span[text()='Continue']"));
    }

    private void clickContinueButton() throws InterruptedException {
        getContinueButton().click();
        sleep(1000);
    }

    private void sleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds);
    }

}
