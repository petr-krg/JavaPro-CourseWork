package krg.petr.otusru;

import krg.petr.otusru.config.TestFileNameProvider;
import krg.petr.otusru.dao.CsvQuestionDao;
import krg.petr.otusru.domain.Question;
import krg.petr.otusru.exceptions.QuestionReadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static krg.petr.otusru.TestHelper.loadQuestionFromFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Test CsvQuestionDao")
public class CsvQuestionDaoTest {

    private TestFileNameProvider testFileProvider;
    private CsvQuestionDao csvQuestionDao;
    private final String testFileName = "/questions.csv";

    @BeforeEach
    public void setUp() {
        testFileProvider = Mockito.mock(TestFileNameProvider.class);
        BDDMockito.given(testFileProvider.getTestFileName()).willReturn(testFileName);
        csvQuestionDao = new CsvQuestionDao(testFileProvider);
    }

    @Test
    public void testFindAllSuccess() {

        List<Question> expectedQuestions = new ArrayList<>();

        expectedQuestions = loadQuestionFromFile("/questions.csv");

        List<Question> actualQuestions = csvQuestionDao.findAll();

        Assertions.assertIterableEquals(expectedQuestions, actualQuestions);
    }

    @Test
    public void testFindAllFailure() {
        String wrongFileName = "/nonexistent-questions.csv";
        when(testFileProvider.getTestFileName()).thenReturn(wrongFileName);
        CsvQuestionDao csvQuestionDao = new CsvQuestionDao(testFileProvider);

        assertThrows(QuestionReadException.class, () -> {
            List<Question> actualQuestions = csvQuestionDao.findAll();
        });
    }
}
