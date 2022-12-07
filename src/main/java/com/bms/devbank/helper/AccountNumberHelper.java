package com.bms.devbank.helper;

public class AccountNumberHelper {
    public static String generateAccountNumber() {
        final StringBuilder accountNumber = new StringBuilder("TR");
        for (int i = 0; i < 16; i++) {
            accountNumber.append((int) (Math.random() * 10));
        }
        return accountNumber.toString();
    }
}
