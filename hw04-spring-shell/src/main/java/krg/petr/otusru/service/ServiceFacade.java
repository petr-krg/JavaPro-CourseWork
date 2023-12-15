package krg.petr.otusru.service;

import krg.petr.otusru.domain.Student;
import krg.petr.otusru.domain.TestResult;
import org.springframework.stereotype.Component;

@Component
public class ServiceFacade {

    private final TestRunnerService testRunnerService;

    private final StudentService studentService;

    private final ResultService resultService;

    public ServiceFacade(TestRunnerService testRunnerService, StudentService studentService,
                         ResultService resultService) {
        this.testRunnerService = testRunnerService;
        this.studentService = studentService;
        this.resultService = resultService;
    }

    public Student determineCurrentStudent() {
        return studentService.determineCurrentStudent();
    }

    public TestResult run(Student student) {
        return testRunnerService.run(student);
    }

    public void showResult(TestResult testResult) {
        resultService.showResult(testResult);
    }
}