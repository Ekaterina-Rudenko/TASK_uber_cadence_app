package by.rudenko.activityworker.service;

import by.rudenko.activityworker.dto.WeatherDTO;
import org.springframework.stereotype.Component;

public interface SaveWeatherService {
    void save(WeatherDTO weatherDTO);
}
