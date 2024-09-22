package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OccurrenceVerifierTest extends VerifierTest {
    Code secretCode = new Code("101");
    Verifier verifier;

    @BeforeEach
    void setUp() {
        verifier = new OccurrenceVerifier("8", secretCode, 1);
    }

    @Test
    @DisplayName("check() should return true if the given code has two 1")
    void checkMatchingTrue() {
        boolean res = check("110", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the given code has no 1")
    void checkMatchingFalse() {
        boolean res = check("325", verifier);
        assertFalse(res);
    }

}