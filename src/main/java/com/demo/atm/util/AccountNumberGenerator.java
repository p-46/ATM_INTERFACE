package com.demo.atm.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final int ACCOUNT_NUMBER_LENGTH = 8;

    public String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(ACCOUNT_NUMBER_LENGTH);
        
        // Ensure the generated account number is exactly 8 digits
        while (sb.length() < ACCOUNT_NUMBER_LENGTH) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}

