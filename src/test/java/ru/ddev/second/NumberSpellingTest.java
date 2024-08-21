package ru.ddev.second;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class NumberSpellingTest {

  private final NumberSpelling numberSpelling = new NumberSpelling();

  @Test
  public void shouldReturnTenThousandsRound() {
    String correct = "девяносто девять тысяч";
    BigDecimal value = BigDecimal.valueOf(99_000);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnTenThousandsNotRound() {
    String correct = "девяносто девять тысяч триста двадцать один";
    BigDecimal value = BigDecimal.valueOf(99_321);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnTenThousandsWithZero() {
    String correct = "девяносто девять тысяч триста один";
    BigDecimal value = BigDecimal.valueOf(99_301);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnThousandsRoundOneNotation() {
    String correct = "одна тысяча";
    BigDecimal value = BigDecimal.valueOf(1000);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnTenThousandsRoundOneNotation() {
    String correct = "пятьдесят одна тысяча";
    BigDecimal value = BigDecimal.valueOf(51_000);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnTenThousandsRoundMultiNotation() {
    String correct = "пятьдесят две тысячи";
    BigDecimal value = BigDecimal.valueOf(52_000);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnTenThousandsWithFloatPart() {
    String correct = "пятьдесят две тысячи пятьсот тридцать семь и двадцать один";
    BigDecimal value = BigDecimal.valueOf(52_537.21);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

  @Test
  public void shouldReturnRandom() {
    String correct = "двадцать пять тысяч семьсот девяносто девять и тридцать три";
    BigDecimal value = BigDecimal.valueOf(25799.33);

    String spelling = numberSpelling.getNumberSpelling(value);

    Assertions.assertEquals(correct, spelling);
  }

}