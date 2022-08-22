package by.rudenko.activityworker.service;

import by.rudenko.activityworker.dto.WeatherDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface RequestWeatherService {
    Optional<WeatherDTO> get(String city);
}
