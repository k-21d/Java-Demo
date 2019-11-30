package com.k21d.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest {
    public static void main(String[] args) {
        Iterator<SPIService> providers = Service.providers(SPIService.class);
        while (providers.hasNext()){
            SPIService next = providers.next();
            next.execute();
        }
        System.out.println("====================");
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        for (SPIService spiService : load) {
            spiService.execute();
        }

    }
}
