package com.epam.homework.fourth.exception;

public class InsufficientFundsException extends Exception {

  public InsufficientFundsException() {

    System.out.println("Недостаточно средств на счёте");
  }
}
