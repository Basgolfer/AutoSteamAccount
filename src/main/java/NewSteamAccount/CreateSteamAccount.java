package NewSteamAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.ArrayList;

public class CreateSteamAccount {

    WebDriver chrome;
    EmailPasswordFetcher emailPasswordFetcher;
    private String username;

    public CreateSteamAccount() throws IOException {
        emailPasswordFetcher = new EmailPasswordFetcher();
    }

    private void goToSteamWebsite() {
        chrome.get("https://store.steampowered.com/join/");
    }

    public void makeAccount() throws InterruptedException {
        setChromeDriver();
        goToSteamWebsite();
        fillInInformation();
        //sleep for captcha entry
        sleep(15000);
        clickContinueButton();
        problemMakingAccount();
    }

    private void problemMakingAccount() throws InterruptedException {
        WebElement element = chrome.findElement(By.id("error_display"));
        if (element.getText().equals("There was a problem creating your Steam account, please try again later.")) {
            for (int i = 0; i < 10; i++) {
                sleep(5000);
                clickContinueButton();
                sleep(2000);
                if (element.getText().equals("")) {
                    break;
                }
            }
        }
    }

    private void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Brian\\IdeaProjects\\AutoSteamAccount\\src\\main\\resources\\chromedriver.exe");
        chrome = new ChromeDriver();
    }

    public WebDriver getChromeDriver() {
        return chrome;
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

    private void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public void enterNameAndPassword() throws IOException, InterruptedException {
        toTab(0);
        sleep(8000);
        WebElement element = chrome.findElement(By.id("accountname"));
        String numbersForUsername = getNumbersForUserName();
        String username = "Melad" + numbersForUsername;
        this.username = username;
        element.sendKeys(username);
        updateUserNameTxtFile();
        if (UserNameAvailable()) {
            enterPassword();
            sleep(500);
            enterPasswordAgain();
        }
        else {
            System.out.println("Please update UserName.txt to valid number or throw something in here to auto update it. To lazy ATM.");
        }
    }

    private void enterPassword() {
        WebElement element = chrome.findElement(By.id("password"));
        String password = emailPasswordFetcher.getPassword();
        password = password.replace('!', '@');
        element.sendKeys(password);
    }

    private void enterPasswordAgain() {
        WebElement element = chrome.findElement(By.id("reenter_password"));
        String password = emailPasswordFetcher.getPassword();
        password = password.replace('!', '@');
        element.sendKeys(password);
    }

    private Boolean UserNameAvailable() throws InterruptedException {
        WebElement checkAvailability = chrome.findElement(By.xpath("//*[@id=\"account_form_box\"]/div/div[1]/div[2]/span[1]/a"));
        checkAvailability.click();
        sleep(1000);
        WebElement availability = chrome.findElement(By.id("accountname_availability"));
        return availability.getText().equals("Available!");
    }

    private void updateUserNameTxtFile() throws IOException {
        String numbersForUsername = getNumbersForUserName();
        FileWriter fw = new FileWriter("C:\\Users\\Brian\\IdeaProjects\\AutoSteamAccount\\src\\main\\resources\\UserName.txt");
        BufferedWriter out = new BufferedWriter(fw);
        Integer number = Integer.parseInt(numbersForUsername);
        number++;
        out.write(number.toString());
        out.flush();
        out.close();
    }

    void toTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<String>(chrome.getWindowHandles());
        chrome.switchTo().window(tabs.get(tabNumber));
    }

    private String getNumbersForUserName() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Brian\\IdeaProjects\\AutoSteamAccount\\src\\main\\resources\\UserName.txt"));
        return bufferedReader.readLine();
    }

    public void clickSignUp() throws InterruptedException {
        sleep(500);
        WebElement completeSignUp = chrome.findElement(By.xpath("//*[@id=\"createAccountButton\"]/span"));
        completeSignUp.click();
        System.out.println("Account made: " + this.username);
        sleep(500);
        chrome.quit();
    }
}
