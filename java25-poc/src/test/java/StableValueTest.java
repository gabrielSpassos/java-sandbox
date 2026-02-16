import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StableValueTest {

    @Test
    void shouldCreateStableValue() {
        StableValue<Boolean> value = StableValue.of();

        assertFalse(value.isSet());
    }

    @Test
    void shouldSetStableValue() {
        StableValue<String> value = StableValue.of();

        value.trySet("hello world!");

        assertTrue(value.isSet());
        assertEquals("hello world!", value.orElseThrow());
    }
}
