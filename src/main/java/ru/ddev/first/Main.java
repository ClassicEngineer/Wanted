package ru.ddev.first;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

public class Main {
  public static void main(String[] args) {
    VacationCheck vacationCheck = new VacationCheck();
    InsuranceSending insuranceSending = new InsuranceSending(vacationCheck);
    Date nearesetSendingDate = insuranceSending.getNearestSendingDate(Date.valueOf(LocalDate.of(2024, Month.AUGUST, 2)));
    System.out.println(nearesetSendingDate);
  }
}