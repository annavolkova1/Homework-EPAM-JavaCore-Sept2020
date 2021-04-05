package com.epam.homework.third.сountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class UniversityCreditRunnable implements Runnable{
  private final CountDownLatch countDownLatch;
  private final UniversityCredit universityCredit;

  public UniversityCreditRunnable(UniversityCredit universityCredit, CountDownLatch countDownLatch) {
    this.universityCredit = universityCredit;
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    System.out.println("Student receives credit " + universityCredit);
    try {
      TimeUnit.SECONDS.sleep(5);
      System.out.println("Credit received "  + universityCredit);
      countDownLatch.countDown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
