package by.rudenko.activityworker.activity.impl;

import by.rudenko.activityworker.activity.SaveWeatherActivity;
import by.rudenko.activityworker.dto.WeatherDTO;
import by.rudenko.activityworker.service.SaveWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveWeatherActivityImpl implements SaveWeatherActivity {
    private final SaveWeatherService saveWeatherService;

    @Autowired
    public SaveWeatherActivityImpl(SaveWeatherService saveWeatherService) {
        this.saveWeatherService = saveWeatherService;
    }
    @Override
    public void save(WeatherDTO weatherDTO) {
        saveWeatherService.save(weatherDTO);
    }
}
