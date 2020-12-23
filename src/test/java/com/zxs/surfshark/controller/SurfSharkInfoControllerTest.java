package com.zxs.surfshark.controller;

import org.junit.jupiter.api.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class SurfSharkInfoControllerTest {

    @Test
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        SurfSharkInfoController surfSharkInfoController = new SurfSharkInfoController();
        String result = surfSharkInfoController.insertBatch();
        System.out.print(result);
    }
}