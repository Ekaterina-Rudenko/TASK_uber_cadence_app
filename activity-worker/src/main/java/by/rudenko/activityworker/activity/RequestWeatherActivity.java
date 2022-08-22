package by.rudenko.activityworker.activity;

import by.rudenko.activityworker.dto.WeatherDTO;
import com.uber.cadence.activity.ActivityMethod;

import java.util.Optional;

import static by.rudenko.activityworker.configuration.Constants.TASKS;

public interface RequestWeatherActivity {
    @ActivityMethod(scheduleToCloseTimeoutSeconds = 30, name = TASKS)
    Optional<WeatherDTO> get(String city);
}
