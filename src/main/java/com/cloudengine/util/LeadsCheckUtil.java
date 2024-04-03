package com.cloudengine.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class LeadsCheckUtil {

    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static boolean validPhoneNumber(String phoneNumber) {
        try {
            Phonenumber.PhoneNumber pu = phoneUtil.parse(phoneNumber, "CN");

            boolean isValid = phoneUtil.isValidNumber(pu);
            return isValid;
        } catch (Exception e) {

        }
    }



    public static boolean validEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null) {
            return false;
        }
        return email.matches(emailRegex);
    }



    public static void main(String[] args) throws NumberParseException {
        boolean r = LeadsCheckUtil.validPhoneNumber("+8613067789355");
        System.out.println(r);
    }
}
