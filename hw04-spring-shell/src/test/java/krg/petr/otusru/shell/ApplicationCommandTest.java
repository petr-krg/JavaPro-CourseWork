package krg.petr.otusru.shell;

import krg.petr.otusru.config.AppConfig;
import krg.petr.otusru.domain.Student;
import krg.petr.otusru.domain.TestResult;
import krg.petr.otusru.service.ResultService;
import krg.petr.otusru.service.StudentService;
import krg.petr.otusru.service.TestRunnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
public class ApplicationCommandTest {

    @Mock
    private AppConfig appConfig;
    @Mock
    private StudentService studentService;
    @Mock
    private ResultService resultService;
    @Mock
    private TestRunnerService runnerService;
    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private ApplicationCommands commands;

    @BeforeEach
    void SetUp() {
        when(messageSource.getMessage(anyString(), any(), any(Locale.class)))
                .thenAnswer(i -> String.format("Mocked message for %s", i.getArguments()[0]));

        when(runnerService.run(any(Student.class))).thenReturn(new TestResult(new Student("Test", "Student")));
    }

    @Test
    public void testShowLocale() {
        when(appConfig.getLocale()).thenReturn(Locale.ENGLISH);
        String result = commands.showLocale();
        assertEquals("Mocked message for command.current.locale", result);
    }


    @Test
    public void testLogin() {
        Student mockStudent = new Student("Joshua", "Bloch");
        when(studentService.determineCurrentStudent()).thenReturn(mockStudent);
        commands.login();
        verify(studentService).determineCurrentStudent();
    }

    @Test
    public void testShowResultTest() {
        Student mockStudent = new Student("Joshua", "Bloch");
        when(studentService.determineCurrentStudent()).thenReturn(mockStudent);

        commands.login();
        commands.runTest();

        commands.showResultTest();
        verify(resultService).showResult(any(TestResult.class));
    }
}
