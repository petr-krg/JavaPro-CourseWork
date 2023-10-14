package krg.petr.otusru;

import krg.petr.otusru.dao.QuestionDao;
import krg.petr.otusru.domain.Question;
import krg.petr.otusru.service.IOService;
import krg.petr.otusru.service.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static krg.petr.otusru.TestHelper.loadQuestionFromFile;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Test TestServiceImp")
public class TestServiceImpTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private IOService ioService;

    @InjectMocks
    private TestServiceImpl testService;

    @BeforeEach
    public void SetUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuestion() {

        List<Question> expectedQuestions;

        expectedQuestions = loadQuestionFromFile("/questions.csv");

        when(questionDao.findAll()).thenReturn(expectedQuestions);

        testService.executeTest();

        verify(ioService).printLine("");
        verify(ioService).printFormattedLine("Please answer the questions below%n");

        for (Question question : expectedQuestions) {
            verify(ioService).printLine(question.getText());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                verify(ioService).printFormattedLine("%d) %s", i + 1, question.getAnswers().get(i).getText());
            }
        }
   }
}
