package machine_coding.parking_lot.factories;

import machine_coding.parking_lot.repositories.*;
import machine_coding.parking_lot.services.*;
import machine_coding.parking_lot.strategies.pricing_strategy.*;

import java.util.*;

public class CalculateFeeStrategyFactory {
    private SlabService slabService;

    public CalculateFeeStrategyFactory(SlabService slabService) {
        this.slabService = slabService;
    }

    public CalculateFeeStrategy getCalculateFeesStrategy(Date exitDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitDate);

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = day == Calendar.SATURDAY || day == Calendar.SUNDAY;
        CalculateFeeStrategy calculateFeeStrategy;
        if(isWeekend){
            calculateFeeStrategy = new WeekendStrategy(slabService);
        } else {
            calculateFeeStrategy = new WeekDayStrategy();
        }

        return calculateFeeStrategy;
    }

}
