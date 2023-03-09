package org.gabrielspassos.poc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiamondServiceTest {

    @Test
    void shouldThrowErrorForInvalidNumberInput() {
        var input = "12";
        var diamondService = new DiamondService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamondService.createDiamond(input));

        assertEquals("Invalid input 12", exception.getMessage());
    }

    @Test
    void shouldThrowErrorForInvalidLettersInput() {
        var input = "Ab";
        var diamondService = new DiamondService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamondService.createDiamond(input));

        assertEquals("Invalid input Ab", exception.getMessage());
    }

    @Test
    void shouldThrowErrorForInvalidCharacterInput() {
        var input = "$";
        var diamondService = new DiamondService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamondService.createDiamond(input));

        assertEquals("Invalid input $", exception.getMessage());
    }

    @Test
    void shouldCreateDiamond() {
        var input = "C";
        var diamondService = new DiamondService();
        var expectedOutput = "\t\tA\t\t\n\tB\t\tB\t\nC\t\t\t\tC\n\tB\t\tB\t\n\t\tA\t\t\n";

        var output = diamondService.createDiamond(input);

        assertEquals(expectedOutput, output);
    }

    @Test
    void shouldCreateDiamondWithInputLowerCase() {
        var input = "e";
        var diamondService = new DiamondService();
        var expectedOutput =
                """
                \t\t\t\tA\t\t\t\t
                \t\t\tB\t\tB\t\t\t
                \t\tC\t\t\t\tC\t\t
                \tD\t\t\t\t\t\tD\t
                E\t\t\t\t\t\t\t\tE
                \tD\t\t\t\t\t\tD\t
                \t\tC\t\t\t\tC\t\t
                \t\t\tB\t\tB\t\t\t
                \t\t\t\tA\t\t\t\t
                """;

        var output = diamondService.createDiamond(input);

        assertEquals(expectedOutput, output);
    }

}