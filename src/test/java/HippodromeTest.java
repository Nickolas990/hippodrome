import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    public void nullHorses() {
        List<Horse> horses = null;
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }
}
