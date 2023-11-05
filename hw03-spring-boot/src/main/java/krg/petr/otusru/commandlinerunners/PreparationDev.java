package krg.petr.otusru.commandlinerunners;

import krg.petr.otusru.service.TestRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PreparationDev implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreparationDev.class);

    private final TestRunnerService testRunnerService;

    public PreparationDev(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Start: testRunnerService.run()");
        testRunnerService.run();
        LOGGER.info("Stop: testRunnerService.run()");
    }
}
