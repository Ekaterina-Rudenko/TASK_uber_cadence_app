package by.rudenko.activityworker.service.impl;

import by.rudenko.activityworker.dto.WeatherDTO;
import by.rudenko.activityworker.entity.Weather;
import by.rudenko.activityworker.repository.WeatherRepository;
import by.rudenko.activityworker.service.SaveWeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class SaveWeatherServiceImpl implements SaveWeatherService {
    private final WeatherRepository weatherRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaveWeatherServiceImpl(WeatherRepository weatherRepository, ModelMapper modelMapper) {
        this.weatherRepository = weatherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(WeatherDTO weatherDTO) {
        Weather weather = modelMapper.map(weatherDTO, Weather.class);
        enrichWeather(weather);
        weatherRepository.save(weather);
    }

    public void enrichWeather(Weather weather) {
        weather.setRecordDate(LocalDateTime.now());
    }
}
