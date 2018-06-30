import NewSteamAccount.CreateSteamAccount;
import NewSteamAccount.ValidateEmailAddress;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        CreateSteamAccount createSteamAccount = new CreateSteamAccount();
        createSteamAccount.makeAccount();
        ValidateEmailAddress validateEmailAddress = new ValidateEmailAddress();
        validateEmailAddress.signIn();
    }
}