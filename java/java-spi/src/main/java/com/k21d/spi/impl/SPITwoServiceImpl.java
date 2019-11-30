package com.k21d.spi.impl;

import com.k21d.spi.SPIService;

public class SPITwoServiceImpl implements SPIService {
    @Override
    public void execute() {
        System.out.println("SPI two execute()");
    }
}
