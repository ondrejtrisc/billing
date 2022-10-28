package com.phonecompany.billing;

import java.math.BigDecimal;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TelephoneBillCalculatorClass implements TelephoneBillCalculator {

    private static final BigDecimal HIGH_RATE = new BigDecimal("1.0");
    private static final BigDecimal MEDIUM_RATE = new BigDecimal("0.5");
    private static final BigDecimal LOW_RATE = new BigDecimal("0.2");

    @Override
    public BigDecimal calculate(String phoneLog) {
        CSVFormat csvFormat = CSVFormat.DEFAULT;
        try (CSVParser csvParser = CSVParser.parse(phoneLog, csvFormat)) {

            //count of how many calls were made to a given phone number
            HashMap<String, Integer> callCount = new HashMap<>();

            //bill for all the call made to a given number
            HashMap<String, BigDecimal> bill = new HashMap<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            for (CSVRecord csvRecord : csvParser) {

                String phoneNumber = csvRecord.get(0);

                //count a new call made to the phone number
                callCount.merge(phoneNumber, 1, Integer::sum);

                LocalDateTime callStart = LocalDateTime.parse(csvRecord.get(1), formatter);
                LocalDateTime callEnd = LocalDateTime.parse(csvRecord.get(2), formatter);

                //calculate the length of the call
                long callLengthInMilliseconds = Duration.between(callStart, callEnd).toMillis();
                int callLengthInMinutes = (int) Math.ceil((double) callLengthInMilliseconds / 60000);
                if (callLengthInMilliseconds == callLengthInMinutes * 60000L) {
                    /*
                     * It seems to me that there might be a reasonable disagreement about how a call which is recorded as lasting a multiple o 60 seconds should be counted.
                     * If the call is recorded as lasting 60 seconds, then given the limited precision there is no way to determine if it lasted only 59.9 seconds and thus only one minute should be charged, or if it lasted 60.1 seconds and thus, strictly speaking, the second minute has already started and should be charged as well.
                     * The choice has been made to always count the next minute as well.
                     * The reason for the choice is to be consistent with the special case of the call ending the very same second it started, where it seems clear that the first minute should be charged.
                     * */
                    callLengthInMinutes += 1;
                }

                //calculate how many minutes should be charged at what rate
                LocalDateTime highRateStart = callStart.withHour(8).withMinute(0).withSecond(0);
                LocalDateTime highRateEnd = callStart.withHour(16).withMinute(0).withSecond(0);
                int highRateMinutes = 0;
                int mediumRateMinutes = 0;
                int lowRateMinutes = Math.max(callLengthInMinutes - 5, 0);
                if (callStart.isAfter(highRateStart.minusSeconds(1)) && callStart.isBefore(highRateEnd.minusMinutes(4))) {
                    //the first 5 minutes of the call are all charged at the high rate
                    highRateMinutes = Math.min(callLengthInMinutes, 5);
                }
                else if (callStart.isBefore(highRateStart.minusMinutes(4)) || callStart.isAfter(highRateEnd.minusSeconds(1))) {
                    //the first 5 minutes of the call are all charged at the medium rate
                    mediumRateMinutes = Math.min(callLengthInMinutes, 5);
                }
                else if (callStart.isBefore(highRateStart)) {
                    //the call starts at the medium rate but switches to the high rate within the first 5 minutes
                    double mediumRateDuration = (double) Duration.between(callStart, highRateStart).toMillis();
                    mediumRateMinutes = Math.min((int) Math.ceil(mediumRateDuration / 60000), callLengthInMinutes);
                    highRateMinutes = Math.min(callLengthInMinutes - mediumRateMinutes, 5 - mediumRateMinutes);
                }
                else if (callStart.isAfter(highRateEnd.minusMinutes(4).minusSeconds(1))) {
                    //the call starts at the high rate but switches to the medium rate within the first 5 minutes
                    double highRateDuration = (double) Duration.between(callStart, highRateEnd).toMillis();
                    highRateMinutes = Math.min((int) Math.ceil(highRateDuration / 60000), callLengthInMinutes);
                    mediumRateMinutes = Math.min(callLengthInMinutes - highRateMinutes, 5 - highRateMinutes);
                }

                //calculate the charge for the call
                BigDecimal highRateCharge = HIGH_RATE.multiply(BigDecimal.valueOf(highRateMinutes));
                BigDecimal mediumRateCharge = MEDIUM_RATE.multiply(BigDecimal.valueOf(mediumRateMinutes));
                BigDecimal lowRateCharge = LOW_RATE.multiply(BigDecimal.valueOf(lowRateMinutes));
                BigDecimal charge = highRateCharge.add(mediumRateCharge).add(lowRateCharge);

                //add the charge for the call to the bill for the phone number
                bill.merge(phoneNumber, charge, BigDecimal::add);
            }

            //find the most frequently called phone number
            String mostFrequentNumber = null;
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : callCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    mostFrequentNumber = entry.getKey();
                    maxCount = entry.getValue();
                }
                else if (entry.getValue() == maxCount && (Long.parseLong(entry.getKey()) > Long.parseLong(mostFrequentNumber))) {
                    mostFrequentNumber = entry.getKey();
                }
            }

            //add charges for all phone numbers except for the most frequently called one
            BigDecimal totalCharge = BigDecimal.ZERO;
            for (Map.Entry<String, BigDecimal> entry : bill.entrySet()) {
                if (!entry.getKey().equals(mostFrequentNumber)) {
                    totalCharge = totalCharge.add(entry.getValue());
                }
            }

            return totalCharge;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
