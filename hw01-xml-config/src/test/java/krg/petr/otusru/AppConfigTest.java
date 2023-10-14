package krg.petr.otusru;

import krg.petr.otusru.config.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test AppConfig")
public class AppConfigTest {

    @Test
    public void testGetFileName() {
        AppConfig config = new AppConfig("questions.csv");
        assertThat("questions.csv").isEqualTo(config.getTestFileName());
    }

}
