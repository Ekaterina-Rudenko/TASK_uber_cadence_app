package by.rudenko.workflowworker;

import by.rudenko.activityworker.activity.RequestWeatherActivity;
import by.rudenko.activityworker.activity.SaveWeatherActivity;
import by.rudenko.activityworker.dto.WeatherDTO;
import com.uber.cadence.workflow.Workflow;

import java.util.Optional;

public class WeatherWorkflowWorkerImp implements WeatherWorkflowWorker {

    private final RequestWeatherActivity requestWeatherActivity;
    private final SaveWeatherActivity saveWeatherInfoActivity;

    public WeatherWorkflowWorkerImp() {
        this.requestWeatherActivity = Workflow.newActivityStub(RequestWeatherActivity.class);
        this.saveWeatherInfoActivity =  Workflow.newActivityStub(SaveWeatherActivity.class);
    }
    @Override
    public Optional<WeatherDTO> formWorkflow(String city) {
        Optional<WeatherDTO> weatherInfoDtoOptional = requestWeatherActivity.get(city);
        weatherInfoDtoOptional.ifPresent(saveWeatherInfoActivity::save);
        return weatherInfoDtoOptional;
    }
}


