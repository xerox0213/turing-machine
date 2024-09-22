package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitVerifierTest extends VerifierTest {
    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new DigitVerifier("3", secretCode, 0, 3);
    }

    @Test
    @DisplayName("check() should return true if the given code is of the same category as the secret code")
    void checkMatchingTrue() {
        boolean res = check("225", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the given code is not of the same category as the secret code")
    void checkMatchingFalse() {
        boolean res = check("325", verifier);
        assertFalse(res);
    }

}