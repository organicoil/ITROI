

package ua.nure.publisher.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    /**
     * Validation form
     *
     */


    /**
     * DAO MySQL
     * @param login,password to validate for sign in
     * @return null if login and password are valid
     * @return String if login and password are not valid
     */
    public String validate(String login, String password) {
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            return "Invalid login or password";
        }
        Pattern pattern = Pattern.compile("(U?)[а-яА-ЯёЁіІїЇa-zA-Z0-9_]{5,17}");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            return "Incorrect login or password";
        }
        pattern = Pattern.compile("(U?)[а-яА-ЯёЁіІїЇa-zA-Z0-9_]{5,10}");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return "Incorrect login or password";
        }
        return null;
    }

    /**
     * DAO MySQL
     * @param login,password,name,surname,repassword to validate for sign up
     * @return null if login,password,name,surname,repasswordd are valid
     * @return String if login,password,name,surname,repassword are not valid
     */
    public String validate(String login, String name, String surname, String email, String password, String repassword) {
        if (surname == null || login == null || name == null || email == null || password == null
                || login.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
            return "Invalid register data";
        }
        Pattern pattern = Pattern.compile("^(U?)[а-яА-ЯёЁіІїЇa-zA-Z0-9_]{5,17}");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            return "Incorrect register data";
        }
        pattern = Pattern.compile("(U?)[а-яА-ЯёЁіІїЇa-zA-Z0-9_]{5,10}");
        matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            return "Incorrect register data";
        }
        pattern = Pattern.compile("(U?)[а-яА-ЯёЁіІїЇa-zA-Z0-9_]{5,10}");
        matcher = pattern.matcher(repassword);
        if (!matcher.matches()) {
            return "Incorrect register data";
        }
        pattern = Pattern.compile("^(U?)[а-яА-ЯёЁіІїЇa-zA-Z]{1,15}");
        matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            return "Incorrect register data";
        }
        pattern = Pattern.compile("^(U?)[а-яА-ЯёЁіІїЇa-zA-Z]{1,15}");
        matcher = pattern.matcher(surname);
        if (!matcher.matches()) {
            return "Incorrect register data";
        }
        pattern = Pattern.compile("(U?)[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}");
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return "Incorrect register data";
        }
        if(!password.equals(repassword))
            return "Passwords don`t match";
        return null;
    }
}
