package by.rudenko.activityworker.service.impl;

import by.rudenko.activityworker.dto.WeatherDTO;
import by.rudenko.activityworker.util.WeatherServiceException;
import by.rudenko.activityworker.service.RequestWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RequestWeatherServiceImpl implements RequestWeatherService {

    @Value("${weather.appid}")
    private String appid;
    @Value("${weather.url}")
    private String weatherUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public RequestWeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Optional<WeatherDTO> get(String city) {
        Optional<WeatherDTO> optionalWeatherDTO = Optional.empty();
        String url = createUrl(city);
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode object = mapper.readTree(response);
            String tempString = String.valueOf(object.get("main").get(0).get("temp"));
            Float temperature = Float.parseFloat(tempString);
            optionalWeatherDTO = Optional.of(new WeatherDTO(city, temperature));
        } catch (JsonProcessingException e) {
            throw new WeatherServiceException(HttpStatus.NOT_FOUND);
        }
        return optionalWeatherDTO;
    }

    private String createUrl(String city) {
        StringBuilder stringBuilder = new StringBuilder("https://openweathermap.org/data/2.5/weather");
        return stringBuilder
                .append("?q=")
                .append(city)
                .append("&appid=")
                .append("439d4b804bc8187953eb36d2a8c26a02")
                .append("&units=metric")
                .toString();

    }
}
