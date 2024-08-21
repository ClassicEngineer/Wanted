package ru.ddev.second;

import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    NumberSpelling ns = new NumberSpelling();
    String spelling = ns.getNumberSpelling(BigDecimal.valueOf(99_999.99));
    System.out.println(spelling);
  }
}
