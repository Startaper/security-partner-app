package com.ecommerce.securitypartnerapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    protected static final Pattern phonePattern = Pattern.compile("^((\\+?7|8|\\+?[0-9]{2,3})(\\(?[0-9]{3}\\)?)([0-9]{3})-?([0-9]{2})-?([0-9]{2}))$");
    protected static final Pattern emailPattern = Pattern.compile("^((\\w|[-+])+(\\.[\\w-]+)*@[\\w-]+((\\.[\\d\\p{Alpha}]+)*(\\.\\p{Alpha}{2,})*)*)$");
    protected static final int passwordSize = 8;

    public static boolean validPassword(String password) {
        boolean resultBySize = false;
        boolean resultByUpperCase = false;
        boolean resultByLowerCase = false;

        if (password.length() == passwordSize)
            resultBySize = true;

        for (Character ch : password.toCharArray()) {
            if (Character.isUpperCase(ch))
                resultByUpperCase = true;

            if (Character.isLowerCase(ch))
                resultByLowerCase = true;
        }

        return resultBySize && resultByUpperCase && resultByLowerCase;
    }

    public static boolean validPhone(String phone) {
        Matcher matcher = phonePattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean validEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

}
