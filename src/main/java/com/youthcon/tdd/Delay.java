package com.youthcon.tdd;

import org.springframework.stereotype.Component;

@Component
public class Delay {
    public Delay() throws InterruptedException {
        System.out.println("Delay.Delay");
        Thread.sleep(1000);
    }
}
