package test;

import currencyConverter.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyTest {

    /**
     * We assume that the check for border / invalid values is done when receiving user input,
     * hence the conversion is done here even if it is a border value that is not in the program domain
     */
    @Test
    void testConvert() {
        assertEquals(-2222222.0*2.0, Currency.convert(-2222222.0, 2.0)); // typical
        assertEquals(-0.09*2.0, Currency.convert(-0.09, 2.0)); // border
        assertEquals(0.0, Currency.convert(0.0, 2.0)); // border
        assertEquals(500000.0*2.0, Currency.convert(500000.0, 2.0)); // typical
        assertEquals(1000000.0*2.0, Currency.convert(1000000.0, 2.0)); // border
        assertEquals(1000000.01*2.0, Currency.convert(1000000.01, 2.0)); // border
        assertEquals(2222222.0*2.0, Currency.convert(2222222.0, 2.0)); // typical
    }
}