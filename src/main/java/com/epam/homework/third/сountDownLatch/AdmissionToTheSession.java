package com.epam.homework.third.сountDownLatch;

import java.util.concurrent.CountDownLatch;

public class AdmissionToTheSession implements Runnable {

  private final CountDownLatch countDownLatch;

  public AdmissionToTheSession(CountDownLatch countDownLatch) {

    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {

    System.out.println("The student receives credits for admission to the session.");
    try {
      countDownLatch.await();
      System.out.println("Student admitted to the session!");
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
