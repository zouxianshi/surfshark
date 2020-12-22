package com.zxs.surfshark.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfSharkInfoControllerTest {

    @Test
    public static void main(String[] args) {
        SurfSharkInfoController surfSharkInfoController = new SurfSharkInfoController();
        String result = surfSharkInfoController.insertBatch();
        System.out.print(result);
    }
}