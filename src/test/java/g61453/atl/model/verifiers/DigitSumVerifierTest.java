package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigitSumVerifierTest extends VerifierTest {

    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new DigitSumVerifier("19", secretCode);
    }

    @Test
    @DisplayName("check() should return true if the sum of the first digit and the second digit of the given code is strictly less than 6")
    void checkMatchingTrue() {
        boolean res = check("115", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the sum of the first digit and the second digit of the given code is not strictly less than 6")
    void checkMatchingFalse() {
        boolean res = check("515", verifier);
        assertFalse(res);
    }

}