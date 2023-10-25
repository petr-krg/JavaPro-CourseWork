package krg.petr.otusru.service;

import krg.petr.otusru.dao.QuestionDao;
import krg.petr.otusru.domain.Student;
import krg.petr.otusru.domain.TestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            var isAnswerValid = false;
            var answerCount = question.getAnswers().size();
            ioService.printLine(question.getText());
            for (int i = 0; i < answerCount; i++) {
                ioService.printFormattedLine("%d) %s", i + 1, question.getAnswers().get(i).getText());
            }

            var answerNumber = ioService.readIntForRangeWithPrompt(1, answerCount,
                    "Enter your answer number: ",
                    String.format(
                            "Your input is invalid. Please provide a number within the specified range, from 1 to %d!",
                            answerCount));
            isAnswerValid = question.answers().get(--answerNumber).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}
