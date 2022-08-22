package by.rudenko.workflowlauncher;

import by.rudenko.activityworker.activity.impl.RequestWeatherActivityImpl;
import by.rudenko.activityworker.activity.impl.SaveWeatherActivityImpl;
import by.rudenko.activityworker.service.RequestWeatherService;
import by.rudenko.activityworker.service.SaveWeatherService;
import by.rudenko.workflowworker.WeatherWorkflowWorkerImp;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static by.rudenko.activityworker.configuration.Constants.DOMAIN;
import static by.rudenko.activityworker.configuration.Constants.TASKS;

@SpringBootApplication
public class LauncherApplication {
    public static void main(String[] args) {
        SpringApplication.run(LauncherApplication.class, args);
    }
    @Bean
    public WorkflowClient workflowClient() {
        IWorkflowService service = new WorkflowServiceTChannel(ClientOptions.defaultInstance());

        WorkflowClientOptions workflowClientOptions = WorkflowClientOptions.newBuilder()
                .setDomain(DOMAIN)
                .build();
        return WorkflowClient.newInstance(service, workflowClientOptions);
    }
    @Bean
    CommandLineRunner commandLineRunner(WorkflowClient workflowClient, RequestWeatherService requestWeatherService, SaveWeatherService saveWeatherService) {
        return args -> {
            WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
            Worker worker = factory.newWorker(TASKS);
            worker.registerWorkflowImplementationTypes(WeatherWorkflowWorkerImp.class);
            worker.registerActivitiesImplementations(new RequestWeatherActivityImpl(requestWeatherService), new SaveWeatherActivityImpl(saveWeatherService));
            factory.start();
        };
    }
}
