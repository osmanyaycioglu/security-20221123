package com.training.security.security.random;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class RandomRun {

    // http://127.0.0.1/api/customer/get?id=100
    // {
    //      "password" : "123456";
    //      "phone" : "05437334873";
    // }
    // http://127.0.0.1/api/customer/get?id=101
    // http://127.0.0.1/api/customer/get?id=100&sid=12121


    public static void main(String[] args) {
        Random random = new SecureRandom(UUID.randomUUID().toString().getBytes());
        int i = random.nextInt();
        System.out.println(i);
    }
}
