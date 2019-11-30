package com.k21d.spi.impl;

import com.k21d.spi.SPIService;

public class SPIOneServiceImpl implements SPIService {
    @Override
    public void execute() {
        System.out.println("SPI one execute()");
    }
}
