package utility;

public class Helper {

    public boolean checkValidRecord(int record, int size) {
        return size >= record && record != 0;
    }

    public boolean fieldValidation(String input){
        if(input.equals("")){
            System.out.println("Field should not be empty");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password, String rePassword){
        return rePassword.equals(password);
    }
}
