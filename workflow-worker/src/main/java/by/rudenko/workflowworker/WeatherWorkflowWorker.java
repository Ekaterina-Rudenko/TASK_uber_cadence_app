package by.rudenko.workflowworker;

import by.rudenko.activityworker.dto.WeatherDTO;
import com.uber.cadence.workflow.WorkflowMethod;

import java.util.Optional;

public interface WeatherWorkflowWorker {
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 10, taskList = "weatherTasks")
    Optional<WeatherDTO> formWorkflow(String city);
}
