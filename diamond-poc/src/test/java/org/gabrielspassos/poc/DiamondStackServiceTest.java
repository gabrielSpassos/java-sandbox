package org.gabrielspassos.poc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiamondStackServiceTest {

    @Test
    void shouldThrowErrorForInvalidNumberInput() {
        var input = "12";
        var diamondStackService = new DiamondStackService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamondStackService.createDiamond(input));

        assertEquals("Invalid input 12", exception.getMessage());
    }

    @Test
    void shouldThrowErrorForInvalidLettersInput() {
        var input = "Ab";
        var diamondStackService = new DiamondStackService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamondStackService.createDiamond(input));

        assertEquals("Invalid input Ab", exception.getMessage());
    }

    @Test
    void shouldThrowErrorForInvalidCharacterInput() {
        var input = "$";
        var diamondStackService = new DiamondStackService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamondStackService.createDiamond(input));

        assertEquals("Invalid input $", exception.getMessage());
    }

    @Test
    void shouldCreateDiamond() {
        var input = "C";
        var diamondStackService = new DiamondStackService();
        var expectedOutput = "\t\tA\t\t\n\tB\t\tB\t\nC\t\t\t\tC\n\tB\t\tB\t\n\t\tA\t\t\n";

        var output = diamondStackService.createDiamond(input);

        assertEquals(expectedOutput, output);
    }

    @Test
    void shouldCreateDiamondWithInputLowerCase() {
        var input = "e";
        var diamondStackService = new DiamondStackService();
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

        var output = diamondStackService.createDiamond(input);

        assertEquals(expectedOutput, output);
    }

}