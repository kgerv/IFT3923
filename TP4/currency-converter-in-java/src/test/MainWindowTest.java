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

    @Test
    public void testConvertWhiteBox() {
        ArrayList<Currency> currencies = new ArrayList<>();
        String currency1 = "US Dollar";
        String currency2 = "Euro";
        String currency3 = "Australian Dollar";

        // empty list currencies, jumps loops
        assertEquals(0.0, MainWindow.convert(currency1, currency2, currencies,1000.0));

        currencies = Currency.init(); // use default currencies list for testing

        // both currencies in list, m1 iterations for first loop, m2 iterations for second
        // mi = index of currency in currencies list + 1
        // USD to EUR rate is 0.93
        assertEquals(0.93*1000.0, MainWindow.convert(currency1, currency2, currencies,1000.0));

        // one currency is not in the list
        assertEquals(0.0, MainWindow.convert(currency3, currency2, currencies,1000.0));
        assertEquals(0.0, MainWindow.convert(currency1, currency3, currencies,1000.0));
    }

    @Test
    void testConvertWhiteBox1() {
        //couverture des instructions
        ArrayList<Currency> currencies = Currency.init();
        String currency1 = "US Dollar";
        String currency2 = "Euro";
        Double amount = 100.0;
        Double result = MainWindow.convert(currency1, currency2, currencies, amount);
        assertEquals(93.0, result);
    }

    @Test
    void testConvertWhiteBox2() {
        //couverture des arcs du graphe du flot de contrôle
        ArrayList<Currency> currencies = Currency.init();
        String currency1 = "British Pound";
        String currency2 = "Swiss Franc";
        String currency3 = "Japanese Yen";
        String currency4 = "Chinese Yuan Renminbi";
        Double amount = 100.0;
        Double result1 = MainWindow.convert(currency1, currency3, currencies, amount);
        Double result2 = MainWindow.convert(currency4, currency2, currencies, amount);
        Double result3 = MainWindow.convert(currency2, currency2, currencies, amount);
        Double result4 = MainWindow.convert(currency3, currency4, currencies, amount);
        assertEquals(18641.0, result1);
        assertEquals(16.0, result2);
        assertEquals(100.0, result3);
        assertEquals(5.10, result4);
    }
}