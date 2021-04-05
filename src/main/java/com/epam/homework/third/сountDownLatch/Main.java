package com.epam.homework.third.сountDownLatch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    CountDownLatch countDownLatch = new CountDownLatch(UniversityCredit.values().length);
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    executorService.submit(new AdmissionToTheSession(countDownLatch));
    Arrays.stream(UniversityCredit.values())
        .map(universityCredit -> new UniversityCreditRunnable(universityCredit, countDownLatch))
        .forEach(executorService::submit);

    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);
  }
}
