package com.epam.homework.third.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Child implements Runnable {

  private final Semaphore horse;

  public Child(Semaphore horse) {

    this.horse = horse;
  }

  @Override
  public void run() {

    System.out.println(Thread.currentThread().getName() + " waiting free horse on the carousel.");
    try {
      horse.acquire();
      System.out.println(Thread.currentThread().getName() + " swinging on the carousel.");
      TimeUnit.SECONDS.sleep(5);
      System.out.println(Thread.currentThread().getName() + " left the carousel.");
      horse.release();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
