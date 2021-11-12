package ru.krasnopolsky;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.krasnopolsky.AppStarter.WRONG_3RD_ARG_MSG;
import static ru.krasnopolsky.AppStarter.WRONG_NUMBER_OF_ARGS_MSG;

public class AppStarterTest {

    private final AppStarter appStarter = new AppStarter();

    @Test
    void startWithNoArgumentsTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> appStarter.start(null));
        assertEquals(WRONG_NUMBER_OF_ARGS_MSG, exception.getMessage());
    }

    @Test
    void startWithWrongNumberOfArgumentsTest() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> appStarter.start(new String[] {"someArgument"}));
        assertEquals(WRONG_NUMBER_OF_ARGS_MSG, exception.getMessage());
    }

    @Test
    void startWithWrong3rdArgumentTest() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> appStarter.start(new String[] {"inputPath", "outputPath", "x"}));
        assertEquals(WRONG_3RD_ARG_MSG, exception.getMessage());
    }
}
