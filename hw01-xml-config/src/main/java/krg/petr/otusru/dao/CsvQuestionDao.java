package krg.petr.otusru.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import krg.petr.otusru.config.TestFileNameProvider;
import krg.petr.otusru.dao.dto.QuestionDto;
import krg.petr.otusru.domain.Question;
import krg.petr.otusru.exceptions.QuestionReadException;

import java.io.Reader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    public CsvQuestionDao(TestFileNameProvider fileNameProvider) {
        this.fileNameProvider = fileNameProvider;
    }

    @Override
    public List<Question> findAll() {

        try (InputStream inputStream = getClass().getResourceAsStream(fileNameProvider.getTestFileName())) {
            try (Reader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                List<QuestionDto> questionDtos = new CsvToBeanBuilder<QuestionDto>(bufferedReader)
                        .withType(QuestionDto.class)
                        .withSkipLines(1)
                        .withSeparator(';')
                        .build()
                        .parse();

                return questionDtos.stream()
                        .map(QuestionDto::toDomainObject)
                        .collect(Collectors.toList());

            }
        } catch (Exception e) {
            throw new QuestionReadException("Could not load questions from the resource", e);
        }
    }
}
