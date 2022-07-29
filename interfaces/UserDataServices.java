package interfaces;

import model.User;

public interface UserDataServices {
    User getProfileDetail(String loginInput) throws Exception;
    void createAccount(String userName, String firstName, String lastName, String emailId, String password) throws Exception;
}
