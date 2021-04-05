package com.epam.homework.fourth;

import com.epam.homework.fourth.exception.InsufficientFundsException;

public class BankAccount {

  public BankAccount(int balance, int userID, Currency currency) {

    this.balance = balance;
    this.userID = userID;
    this.currency = currency;
  }

  private volatile double balance;
  private final int userID;
  private final Currency currency;
  private final ExchangeRate exchangeRate = new ExchangeRate();

  /**
   * This method returns current currency
   *
   * @return currency
   */
  public Currency getCurrency() {

    return currency;
  }

  /**
   * This method returns current balance
   *
   * @return balance
   */
  public synchronized double getBalance() {

    return balance;
  }

  /**
   * This method allows you to put money into the account
   *
   * @param amount the amount of money that the user is going to deposit into the account
   * @param currency currency in which the user is going to deposit money into the account
   */
  public synchronized void deposit(int amount, Currency currency) {

    if (this.currency == currency) {
      balance += amount;
    }
    else {
      double localExchangeRate = getLocalExchangerRate(currency);
      balance += amount * localExchangeRate;
    }
  }

  /**
   * This method allows you to withdraw money from the account
   *
   * @param amount the amount of money that the user wants to withdraw from the account
   * @param currency currency in which the user is going to withdraw money from the account
   * @throws InsufficientFundsException if your account has insufficient funds
   */
  public synchronized void withdraw(int amount, Currency currency) throws InsufficientFundsException {

    if (this.currency == currency) {
      if (balance < amount) {
        throw new InsufficientFundsException();
      }
      else {
        balance -= amount;
      }
    }
    else {
      double localExchangeRate = getLocalExchangerRate(currency);
      if (balance < amount * localExchangeRate) {
        throw new InsufficientFundsException();
      }
      else {
        balance -= amount * localExchangeRate;
      }
    }
  }

  /**
   * This method to get the conversion rate
   *
   * @param currency user-selected currency
   * @return localExchangeRate
   */
  private double getLocalExchangerRate(Currency currency) {

    double localExchangeRate;
    if (currency.equals(Currency.LEVA)) {
      if (this.currency.equals(Currency.RUB)) {
        localExchangeRate = exchangeRate.get_LEVA_RUB();
      }
      else {
        localExchangeRate = exchangeRate.get_LEVA_LIRA();
      }
    }
    else if (currency.equals(Currency.RUB)) {
      if (this.currency.equals(Currency.LEVA)) {
        localExchangeRate = exchangeRate.get_RUB_LEVA();
      }
      else {
        localExchangeRate = exchangeRate.get_RUB_LIRA();
      }
    }
    else {
      if (this.currency.equals(Currency.RUB)) {
        localExchangeRate = exchangeRate.get_LIRA_RUB();
      }
      else {
        localExchangeRate = exchangeRate.get_LIRA_LEVA();
      }
    }
    return localExchangeRate;
  }

  /**
   * This method checks UserID
   *
   * @return true if userID is passed variable (userID), corresponds to a class instance variable (this.userID)
   */
  public synchronized boolean checkUserID(int userID) {

    return this.userID == userID;
  }
}
