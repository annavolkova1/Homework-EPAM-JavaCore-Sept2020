package com.epam.homework.fourth;

import com.epam.homework.fourth.exception.WrongPinException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws WrongPinException, InterruptedException {

    List<User> users = new ArrayList<>();
    users.add(new User(123, 1));
    users.add(new User(231, 5));
    users.add(new User(12345, 111));

    List<BankAccount> bankAccounts = new ArrayList<>();
    bankAccounts.add(new BankAccount(100, 1, Currency.LEVA));
    bankAccounts.add(new BankAccount(20000000, 5, Currency.LIRA));
    bankAccounts.add(new BankAccount(50000, 111, Currency.RUB));

    User authorisedUser = null;
    BankAccount authorisedUserBankAccount = null;

    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите пин код");
    int pin = scanner.nextInt();

    for (User user : users) {
      if (user.checkPIN(pin)) {
        authorisedUser = user;
        break;
      }
    }

    if (authorisedUser == null) {
      throw new WrongPinException();
    }

    for (BankAccount bankAccount : bankAccounts) {
      if (bankAccount.checkUserID(authorisedUser.getID())) {
        authorisedUserBankAccount = bankAccount;
        break;
      }
    }

    ATMFactory atmFactory = new ATMFactory();

    ATM atm1 = atmFactory.createATM(authorisedUserBankAccount);
    Thread thread1 = new Thread(atm1);

    ATM atm2 = atmFactory.createATM(authorisedUserBankAccount);
    Thread thread2 = new Thread(atm2);

    thread1.start();
    thread1.join();
    thread2.start();
  }
}
