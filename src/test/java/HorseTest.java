import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {
    @Test
    void whenHorseNameNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
    }

    @Test
    void whenHorseNameNullMessage() {
        String expected = "Name cannot be null.";
        try {
            String nullName = null;
            Horse nullHorse = new Horse(nullName, 1);
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
            Horse horse = new Horse(argument, 1);
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
            Horse horse = new Horse("Emma", -1);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }

}
