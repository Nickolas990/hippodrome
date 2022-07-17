import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    void whenHorseNameNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
    }

    @Test
    void whenHorseNameNullMessage() {
        String expected = "Name cannot be null.";
        try {
            new Horse(null, 1);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\t", "", "\n", "\r"})
    void whenEmptyNameException(String argument) {
        assertThrows(IllegalArgumentException.class, ()-> new Horse(argument, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\t", "", "\n", "\r"})
    void whenBlankNameMessage(String argument) {
        String expected = "Name cannot be blank.";
        try {
           new Horse(argument, 1);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    void whenSpeedIsNegativeException() {
        int testSpeed = -1;
        assertThrows(IllegalArgumentException.class, () -> new Horse("Emma", testSpeed));
    }
    @Test
    void whenSpeedIsNegativeMessage() {
        String expected = "Speed cannot be negative.";
        try {
            new Horse("Emma", -1);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    void whenDistanceNegativeException() {
        int testDistance = -1;
        assertThrows(IllegalArgumentException.class, ()-> new Horse("Emma", 5.0, testDistance));
    }

    @Test
    void whenDistanceIsNegativeMessage() {
        String expected = "Distance cannot be negative.";
        try {
            new Horse("Emma", 1, -1);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
    @Test
    void isGetNameCorrect () {
        String expected = "Emma";
        Horse horse = new Horse(expected, 1);
        assertEquals(expected, horse.getName());
    }

    @Test
    void isGetSpeedCorrect() {
        Double speed = 5.0;
        Horse horse = new Horse("Emma", speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void isDistanceCorrect() {
        Double distance = 5.0;
        assertEquals(distance, new Horse("Emma", 1, distance).getDistance());
        assertEquals(0, new Horse("Emma", 1).getDistance());
    }

    @Test
    void isMoveCorrect() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Emma", 1);
            double distance = horse.getDistance();
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.2);
            horse.move();
            assertEquals(horse.getDistance(), distance + horse.getSpeed() * 0.2);
        }
    }

}
