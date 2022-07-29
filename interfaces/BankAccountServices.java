package interfaces;

import model.LinkedAccount;

import java.util.ArrayList;

public interface BankAccountServices {
    ArrayList<LinkedAccount> getBankDetails(String userId) throws Exception;

    void linkAccount(String userId, LinkedAccount accounts) throws Exception;

    void removeAccount(LinkedAccount accounts) throws Exception;
}
