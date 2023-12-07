package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MainWindowTest {

    /**
     * Following the given specification amounts in [0, 1 000 000] are valid and should be converted and any other
     * amounts should not. Only currencies in {USD, CAD, GBP, EUR, CHF, AUD} are accepted.
     */
    @Test
    public void testConvertBlackBox() {
        ArrayList<Currency> currencies = new ArrayList<>();
        // assumes that constructor for Currency will not change
        Currency usd = new Currency("US Dollar", "USD");
        usd.setExchangeValues("EUR", 0.93);

        Currency eur = new Currency("Euro", "EUR");
        eur.setExchangeValues("USD", 1.073);

        currencies.add(usd);
        currencies.add(eur);

        // testing behavior with unaccepted currency, should not convert
        assertEquals(-1.0 , MainWindow.convert("Japanese Yen", "US Dollar", currencies, 100.0));
        assertEquals(-1.0 , MainWindow.convert( "US Dollar", "Japanese Yen", currencies, 100.0));

        // testing behavior according to amounts
        // typical and border values in [0, 1 000 000], should convert
        assertEquals(0.0, MainWindow.convert("US Dollar", "Euro", currencies, 0.0));
        assertEquals(465000.0, MainWindow.convert("US Dollar", "Euro", currencies, 500000.0));
        assertEquals(930000.0, MainWindow.convert("US Dollar", "Euro", currencies, 1000000.0));

        // typical and border values not in [0, 1 000 000], should not convert
        assertNotEquals(-2066666.46, MainWindow.convert("US Dollar", "Euro", currencies, -2222222.0));
        assertNotEquals(-0.08, MainWindow.convert("US Dollar", "Euro", currencies, -0.09));
        assertNotEquals(930000.01, MainWindow.convert("US Dollar", "Euro", currencies, 1000000.01));
        assertNotEquals(20666666.46, MainWindow.convert("US Dollar", "Euro", currencies, 2222222.0));

    }
}