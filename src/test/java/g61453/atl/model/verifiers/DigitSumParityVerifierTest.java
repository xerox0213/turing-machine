package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitSumParityVerifierTest extends VerifierTest {
    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new DigitSumParityVerifier("18", secretCode);
    }

    @Test
    @DisplayName("check() should return true if the sum of digits is even")
    void checkMatchingTrue() {
        boolean res = check("103", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the sum of digits is odd")
    void checkMatchingFalse() {
        boolean res = check("113", verifier);
        assertFalse(res);
    }
}