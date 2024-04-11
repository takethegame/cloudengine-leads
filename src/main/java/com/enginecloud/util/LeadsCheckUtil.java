package com.enginecloud.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LeadsCheckUtil {

    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static boolean validPhoneNumber(String phoneNumber) {
        try {
            Phonenumber.PhoneNumber pu = phoneUtil.parse(phoneNumber, "CN");

            boolean isValid = phoneUtil.isValidNumber(pu);
            return isValid;
        } catch (Exception e) {

        }
        return false;
    }



    public static boolean validEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null) {
            return false;
        }
        return email.matches(emailRegex);
    }



    public static void main(String[] args) throws NumberParseException, IOException, InterruptedException {
        boolean r = LeadsCheckUtil.validPhoneNumber("+8613067789355");
        System.out.println(r);

        var request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com")).GET().build();
        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }
}
