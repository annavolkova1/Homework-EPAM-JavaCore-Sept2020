package com.epam.homework.fourth;

import com.epam.homework.fourth.exception.InsufficientFundsException;
import com.epam.homework.fourth.exception.WrongOperationException;
import java.util.Scanner;

public class ATM implements Runnable {

  BankAccount bankAccount;

  public ATM(BankAccount bankAccount) {

    this.bankAccount = bankAccount;
  }

  /**
   * Main thread of execution
   */

  @Override
  public void run() {

    boolean isValid = true;

    Scanner sc = new Scanner(System.in);

    showAvailableOperations();
    while (isValid) {
      int number = sc.nextInt();

      switch (number) {
        case 1:
          getBalance(bankAccount);
          break;

        case 2:
          System.out.println("Сколько денег Вы хотите внести?");
          int amount = sc.nextInt();
          System.out.println("В какой валюте Вы вносите деньги? 1 - RUB, 2 - LEVA, 3 - LIRA");
          bankAccount.deposit(amount, chooseCurrency(sc));
          getBalance(bankAccount);
          break;

        case 3:
          System.out.println("Сколько денег Вы хотите снять?");
          int amount3 = sc.nextInt();
          System.out.println("В какой валюте Вы снимате деньги? 1 - RUB, 2 - LEVA, 3 - LIRA");
          try {
            bankAccount.withdraw(amount3, chooseCurrency(sc));
          }
          catch (InsufficientFundsException e) {
            e.printStackTrace();
          }
          getBalance(bankAccount);
          break;

        default:
          isValid = false;
          try {
            throw new WrongOperationException();
          }
          catch (WrongOperationException e) {
            e.printStackTrace();
          }
      }

      System.out.println("Хотите продолжить? 1 - да, 2 - нет");
      int contin = sc.nextInt();

      if (contin == 1) {
        showAvailableOperations();
      }
      else {
        isValid = false;
      }
    }
  }

  /**
   * This method returns the currency according to which currency the user entered
   *
   * @return currency
   */

  private Currency chooseCurrency(Scanner sc) {

    int curr = sc.nextInt();
    Currency currency;
    switch (curr) {
      case 1:
        currency = Currency.RUB;
        break;
      case 2:
        currency = Currency.LEVA;
        break;
      case 3:
        currency = Currency.LIRA;
        break;
      default:
        currency = bankAccount.getCurrency();
    }
    return currency;
  }

  /**
   * This method prints the current balance in the current currency
   *
   * @param bankAccount user's bank account
   */

  private void getBalance(BankAccount bankAccount) {

    System.out.println("Ваш баланс = " + bankAccount.getBalance() + " " + bankAccount.getCurrency());
  }

  /**
   * This method prints available operation
   */

  private void showAvailableOperations() {

    System.out.println("Введите 1, чтобы узнать баланс");
    System.out.println("Введите 2, чтобы внести деньги на счёт");
    System.out.println("Введите 3, чтобы снять деньги со счёта");
  }
}
