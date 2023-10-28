package krg.petr.otusru.service;

import krg.petr.otusru.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test StudentServiceImplTest")
public class StudentServiceImplTest {

    @Mock
    private IOService ioService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        when(ioService.readStringWithPrompt("Please input your first name")).thenReturn("Joshua");
        when(ioService.readStringWithPrompt("Please input your last name")).thenReturn("Bloch");
    }

    @Test
    @DisplayName("Create Correct Student")
    public void createCorrectStudent() {
        Student expectedStudent = studentService.determineCurrentStudent();
        assertAll(
                () -> assertEquals("Joshua", expectedStudent.firstName()),
                () -> assertEquals("Bloch", expectedStudent.lastName())
        );
    }
}
