package by.rudenko.activityworker.activity.impl;

import by.rudenko.activityworker.activity.RequestWeatherActivity;
import by.rudenko.activityworker.dto.WeatherDTO;
import by.rudenko.activityworker.service.RequestWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestWeatherActivityImpl implements RequestWeatherActivity {
    private final RequestWeatherService requestWeatherService;
    @Autowired
    public RequestWeatherActivityImpl(RequestWeatherService requestWeatherService) {
        this.requestWeatherService = requestWeatherService;
    }
    @Override
    public Optional<WeatherDTO> get(String city) {
        return  requestWeatherService.get(city);
    }
}
