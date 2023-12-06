package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MainWindowTest {

    @Test
    public void testConvert() {
        ArrayList<Currency> currencies = Currency.init();

        assertEquals(-2066666.46, MainWindow.convert("US Dollar", "Euro", currencies, -2222222.0));
        assertEquals(-0.08, MainWindow.convert("US Dollar", "Euro", currencies, -0.09));
        assertEquals(0.0, MainWindow.convert("US Dollar", "Euro", currencies, 0.0));
        assertEquals(465000.0, MainWindow.convert("US Dollar", "Euro", currencies, 500000.0));
        assertEquals(930000.0, MainWindow.convert("US Dollar", "Euro", currencies, 1000000.0));
        assertEquals(930000.009, MainWindow.convert("US Dollar", "Euro", currencies, 1000000.01));
        assertEquals(20666666.46, MainWindow.convert("US Dollar", "Euro", currencies, 2222222.0));

    }
}