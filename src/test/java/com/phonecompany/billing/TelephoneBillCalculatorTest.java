package com.phonecompany.billing;

import static org.junit.Assert.*;
import org.junit.Test;

import java.math.BigDecimal;

public class TelephoneBillCalculatorTest {

    @Test
    public void testCalculate() {
        TelephoneBillCalculatorClass calculator = new TelephoneBillCalculatorClass();

        assertEquals(new BigDecimal("0"), calculator.calculate("420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n"));

        assertEquals(new BigDecimal("1.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00\n"));

        assertEquals(new BigDecimal("0.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 06:59:20,18-01-2020 06:59:20\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 08:00:00,18-01-2020 08:00:00\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 10:59:20,18-01-2020 10:59:20\n"));

        assertEquals(new BigDecimal("0.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 16:00:00,18-01-2020 16:00:00\n"));

        assertEquals(new BigDecimal("0.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 18:59:20,18-01-2020 18:59:20\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 06:59:20,18-01-2020 07:00:20\n"));

        assertEquals(new BigDecimal("2.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 08:00:00,18-01-2020 08:01:00\n"));

        assertEquals(new BigDecimal("2.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 10:59:20,18-01-2020 11:00:20\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 16:00:00,18-01-2020 16:01:00\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 18:59:20,18-01-2020 19:00:20\n"));

        assertEquals(new BigDecimal("0.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 07:57:40\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 07:58:50\n"));

        assertEquals(new BigDecimal("1.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 07:59:45\n"));

        assertEquals(new BigDecimal("2.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 08:00:40\n"));

        assertEquals(new BigDecimal("3.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 08:01:50\n"));

        assertEquals(new BigDecimal("3.7"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 08:02:45\n"));

        assertEquals(new BigDecimal("4.7"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:57:30,18-01-2020 08:07:45\n"));

        assertEquals(new BigDecimal("5.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:58:30,18-01-2020 08:07:45\n"));

        assertEquals(new BigDecimal("4.8"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:58:59,18-01-2020 08:07:45\n"));

        assertEquals(new BigDecimal("5.3"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:59:00,18-01-2020 08:07:45\n"));

        assertEquals(new BigDecimal("5.1"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 07:59:59,18-01-2020 08:07:45\n"));

        assertEquals(new BigDecimal("5.6"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 08:00:00,18-01-2020 08:07:45\n"));

        assertEquals(new BigDecimal("29.6"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 09:00:00,18-01-2020 11:07:45\n"));

        assertEquals(new BigDecimal("1.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 15:57:40\n"));

        assertEquals(new BigDecimal("2.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 15:58:50\n"));

        assertEquals(new BigDecimal("3.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 15:59:45\n"));

        assertEquals(new BigDecimal("3.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 16:00:40\n"));

        assertEquals(new BigDecimal("4.0"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 16:01:50\n"));

        assertEquals(new BigDecimal("4.2"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 16:02:45\n"));

        assertEquals(new BigDecimal("5.2"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:57:30,18-01-2020 16:07:45\n"));

        assertEquals(new BigDecimal("4.5"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:58:30,18-01-2020 16:07:45\n"));

        assertEquals(new BigDecimal("4.3"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:58:59,18-01-2020 16:07:45\n"));

        assertEquals(new BigDecimal("3.8"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:59:00,18-01-2020 16:07:45\n"));

        assertEquals(new BigDecimal("3.6"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 15:59:59,18-01-2020 16:07:45\n"));

        assertEquals(new BigDecimal("3.1"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 16:00:00,18-01-2020 16:07:45\n"));

        assertEquals(new BigDecimal("39.1"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562353,18-01-2020 19:00:00,18-01-2020 22:07:45\n"));

        assertEquals(new BigDecimal("23.2"), calculator.calculate(
                "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                        "420774577453,13-02-2020 18:10:15,13-02-2020 18:12:57\n" +
                        "420776562354,18-01-2020 06:59:20,18-01-2020 06:59:20\n" +
                        "420776562355,18-01-2020 08:00:00,18-01-2020 08:00:00\n" +
                        "420776562356,18-01-2020 10:59:20,18-01-2020 10:59:20\n" +
                        "420776562357,18-01-2020 16:00:00,18-01-2020 16:00:00\n" +
                        "420776562358,18-01-2020 18:59:20,18-01-2020 18:59:20\n" +
                        "420776562359,18-01-2020 06:59:20,18-01-2020 07:00:20\n" +
                        "420776562363,18-01-2020 08:00:00,18-01-2020 08:01:00\n" +
                        "420776562373,18-01-2020 10:59:20,18-01-2020 11:00:20\n" +
                        "420776562383,18-01-2020 16:00:00,18-01-2020 16:01:00\n" +
                        "420776562393,18-01-2020 18:59:20,18-01-2020 19:00:20\n" +
                        "420776562453,18-01-2020 07:57:30,18-01-2020 07:57:40\n" +
                        "420776562553,18-01-2020 07:57:30,18-01-2020 07:58:50\n" +
                        "420776562653,18-01-2020 07:57:30,18-01-2020 07:59:45\n" +
                        "420776562753,18-01-2020 07:57:30,18-01-2020 08:00:40\n" +
                        "420776562853,18-01-2020 07:57:30,18-01-2020 08:01:50\n" +
                        "420776562953,18-01-2020 07:57:30,18-01-2020 08:02:45\n"));
    }
}
