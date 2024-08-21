package ru.ddev.second;

import java.math.BigDecimal;

public class NumberSpelling {

  public static final String ONE_THOUSAND = "тысяча";
  public static final String THOUSAND = "тысяч";
  public static final String THOUSANDS = "тысячи";

  /**
   *  Функция, возвращающую прописное написание стоимости (до тысяч 99 999.99)
   */
  public String getNumberSpelling(BigDecimal number) {
    if (number.compareTo(BigDecimal.valueOf(99999.99)) > 0) {
      throw new IllegalArgumentException("Number should be less or equal than 99999.99");
    }
    StringBuilder spelling = new StringBuilder();
    int value = number.intValue();
    int tenThousands = value / 10_000;

    String thousandTenDigitsSpelling = getTenDigitSpelling(tenThousands);
    if (!thousandTenDigitsSpelling.isEmpty()) {
      spelling.append(thousandTenDigitsSpelling).append(" ");
    }

    int thousands = (value - (tenThousands * 10_000)) / 1000;

    String thousandDigitSpelling = getDigitSpelling(thousands, true);
    if (!thousandDigitSpelling.isEmpty()) {
      spelling.append(thousandDigitSpelling).append(" ");
      if (thousands == 1) {
        spelling.append(ONE_THOUSAND).append(" ");
      } else if (thousands == 2) {
        spelling.append(THOUSANDS).append(" ");
      } else {
        spelling.append(THOUSAND).append(" ");
      }
    }

    int hundreds = (value  - (tenThousands * 10_000) - (thousands * 1000)) / 100;

    String hundredDigitSpelling = getHundredDigitSpelling(hundreds);
    if (!hundredDigitSpelling.isEmpty()) {
      spelling.append(hundredDigitSpelling).append(" ");
    }

    appendTenValue(value, spelling);

    float floatValue = number.floatValue();

    int tenFloatValue  = (int) ((floatValue  - number.intValue()) * 100);

    if (tenFloatValue > 0) {
      spelling.append(" и ");
      appendTenValue(tenFloatValue, spelling);
    }

    return spelling.toString().trim();
  }

  private void appendTenValue(int value, StringBuilder spelling) {
    int tens = ((value % 100) - (value % 10)) / 10;

    String tenDigitsSpelling = getTenDigitSpelling(tens);
    if (!tenDigitsSpelling.isEmpty()) {
      spelling.append(tenDigitsSpelling).append(" ");
    }

    int digits = value % 10;

    String digitSpelling  = getDigitSpelling(digits, false);
    if (!digitSpelling.isEmpty()) {
      spelling.append(digitSpelling);
    }
  }

  private String getHundredDigitSpelling(int digit) {
    return switch (digit) {
      case 1 -> "сто";
      case 2 -> "двести";
      case 3 -> "триста";
      case 4 -> "четыреста";
      case 5 -> "пятьсот";
      case 6 -> "шестьсот";
      case 7 -> "семьсот";
      case 8 -> "восемьсот";
      case 9 -> "девятьсот";
      case 0 -> "";
      default -> throw new IllegalStateException("Unexpected value: " + digit);
    };
  }


  private String getTenDigitSpelling(int digit) {
    return switch (digit) {
      case 1 -> "десять";
      case 2 -> "двадцать";
      case 3 -> "тридцать";
      case 4 -> "сорок";
      case 5 -> "пятьдесят";
      case 6 -> "шестьдесят";
      case 7 -> "семьдесят";
      case 8 -> "восемьдесят";
      case 9 -> "девяносто";
      case 0 -> "";
      default -> throw new IllegalStateException("Unexpected value: " + digit);
    };
  }

  private String getDigitSpelling(int digit, boolean multiple) {
    return switch (digit) {
      case 1 -> multiple ? "одна" : "один";
      case 2 -> multiple ? "две" : "два";
      case 3 -> "три";
      case 4 -> "четыре";
      case 5 -> "пять";
      case 6 -> "шесть";
      case 7 -> "семь";
      case 8 -> "восемь";
      case 9 -> "девять";
      case 0 -> "";
      default -> throw new IllegalStateException("Unexpected value: " + digit);
    };
  }
}
