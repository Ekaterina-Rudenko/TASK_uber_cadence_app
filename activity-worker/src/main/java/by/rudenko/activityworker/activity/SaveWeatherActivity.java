package by.rudenko.activityworker.activity;

import by.rudenko.activityworker.dto.WeatherDTO;
import com.uber.cadence.activity.ActivityMethod;

import static by.rudenko.activityworker.configuration.Constants.TASKS;


public interface SaveWeatherActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 30, name = TASKS)
    void save(WeatherDTO weatherDTO);

}
