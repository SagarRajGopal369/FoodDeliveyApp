package com.food.models;

import java.security.SecureRandom;

public class OrderIdGenerator {

    private static SecureRandom secureRandom = new SecureRandom();

    public static int generateOrderId(int userId) {
        // Generate a random number between 1,000,000,000 and 2,147,483,640
        int randomComponent = 1_000_000_000 + secureRandom.nextInt(1_147_483_640);

        // Combine randomComponent with userId
        int orderId = randomComponent + (userId % 10);

        return orderId;
    }

//    public static void main(String[] args) {
//        int userId = 23;
//        int orderId = generateOrderId(userId);
//        System.out.println(orderId);
//    }
}
