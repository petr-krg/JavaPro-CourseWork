package krg.petr.otusru.service;

import krg.petr.otusru.domain.Student;
import krg.petr.otusru.service.LocalizedIOService;
import krg.petr.otusru.service.StudentServiceImpl;
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
    private LocalizedIOService localizedIOService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        when(localizedIOService.readStringWithPromptLocalized("StudentService.input.first.name")).thenReturn("Joshua");
        when(localizedIOService.readStringWithPromptLocalized("StudentService.input.last.name")).thenReturn("Bloch");
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
