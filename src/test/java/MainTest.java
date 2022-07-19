import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Disabled
    @Test
    @Timeout(22)
    void mainTimeOut() throws Exception {
        String[] args = new String[0];
        Main.main(args);
    }
}
