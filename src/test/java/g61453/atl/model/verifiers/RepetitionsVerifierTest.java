package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepetitionsVerifierTest extends VerifierTest {

    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new RepetitionsVerifier("20", secretCode);
    }

    @Test
    @DisplayName("check() should return true if there is no repetition of digit in the given code")
    void checkMatchingTrue() {
        boolean res = check("450", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if there is repetition of digit in the given code")
    void checkMatchingFalse() {
        boolean res = check("454", verifier);
        assertFalse(res);
    }
}