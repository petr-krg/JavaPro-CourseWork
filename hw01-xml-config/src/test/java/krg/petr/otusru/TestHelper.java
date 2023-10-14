package krg.petr.otusru;

import com.opencsv.bean.CsvToBeanBuilder;
import krg.petr.otusru.dao.dto.QuestionDto;
import krg.petr.otusru.domain.Question;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static List<Question> loadQuestionFromFile(String filePath) {

        List<Question> resultQuestions = new ArrayList<>();
        try (InputStream inputStream = Application.class.getResourceAsStream(filePath)) {
            try (Reader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                List<QuestionDto> questionDtos = new CsvToBeanBuilder<QuestionDto>(bufferedReader)
                        .withType(QuestionDto.class)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build()
                        .parse();

                resultQuestions = questionDtos.stream()
                        .map(QuestionDto::toDomainObject)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultQuestions;
    }
}
