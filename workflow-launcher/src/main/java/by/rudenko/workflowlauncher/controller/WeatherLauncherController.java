package by.rudenko.workflowlauncher.controller;


import by.rudenko.activityworker.dto.WeatherDTO;
import by.rudenko.activityworker.util.WeatherServiceException;
import by.rudenko.workflowworker.WeatherWorkflowWorker;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Optional;

import static by.rudenko.activityworker.configuration.Constants.TASKS;

@RestController
@RequestMapping()
public class WeatherLauncherController {
    private final WorkflowClient workflowClient;


    @Autowired
    public WeatherLauncherController(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }


    @GetMapping("/launch")
    public ResponseEntity<WeatherDTO> startWorkflow(@RequestParam String city) {
       WeatherWorkflowWorker workflow = workflowClient.newWorkflowStub(
                WeatherWorkflowWorker.class,
                new WorkflowOptions.Builder()
                        .setExecutionStartToCloseTimeout(Duration.ofSeconds(2))
                        .setTaskList(TASKS)
                        .build()
        );
        Optional<WeatherDTO> weatherInfo = workflow.formWorkflow(city);
        return ResponseEntity.ok(weatherInfo.orElseThrow(() -> new WeatherServiceException("City not found", HttpStatus.NOT_FOUND)));
    }

}
