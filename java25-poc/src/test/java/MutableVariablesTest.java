import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutableVariablesTest {

    @Test
    void shouldCreateIntVarThatCanNotChangeType() {
        var x = 1;

        assertEquals(1, x);

        x = 5;

        assertEquals(5, x);

        // Non compilable code
        // x = "Gabriel";
        // x = 1.5;
        // x = true;
    }

    @Test
    void shouldCreateStringVarThatCanNotChangeType() {
        var x = "Gabriel";

        assertEquals("Gabriel", x);

        x = "Foo";

        assertEquals("Foo", x);

        // Non compilable code
        // x = 1;
        // x = 1.5;
        // x = true;
    }
}
