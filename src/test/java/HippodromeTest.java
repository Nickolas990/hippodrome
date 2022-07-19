import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    @ParameterizedTest
    @MethodSource("streamOfArrays")
    public void nullHorses(ArrayList argument) {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(argument));
    }

    static Stream<ArrayList> streamOfArrays() {
        return Stream.of(null, new ArrayList());
    }

    @ParameterizedTest
    @MethodSource("streamOfArrays")
    void whenNullAndEmptyMessages(ArrayList argument) {
        try {
            new Hippodrome(argument);
        } catch (Exception e) {
            if (argument == null) {
                assertEquals("Horses cannot be null.", e.getMessage());
            } else if (argument.isEmpty()) {
                assertEquals("Horses cannot be empty.", e.getMessage());
            }
        }
    }

    @Test
    void checkHippodromeContaining() {
        int i = 0;
        List<Horse> horses = new ArrayList<>();
        while (i < 30) {
            horses.add(new Horse("Horse " + i, i + 3, i + 5));
            i++;
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int j = 0; j < hippodrome.getHorses().size(); j++) {
            assertSame(hippodrome.getHorses().get(j), horses.get(j));
        }
    }

    @Test
    void isHorsesMoving() {
        int i = 0;
        List<Horse> mockOfHorses = new ArrayList<>();
        while (i < 50) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockOfHorses.add(mockHorse);
            i++;
        }
        Hippodrome hippodrome = new Hippodrome(mockOfHorses);
        hippodrome.move();
        hippodrome.getHorses().forEach(e -> Mockito.verify(e).move());
    }

    @Test
    void isReallyWinner() {
        int i = 0;
        List<Horse> horses = new ArrayList<>();
        while (i < 3) {
            horses.add(new Horse("Horse " + i, i + 3, i + 5));
            i++;
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        assertEquals(hippodrome.getWinner(), hippodrome.getHorses().stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get());
    }
}
