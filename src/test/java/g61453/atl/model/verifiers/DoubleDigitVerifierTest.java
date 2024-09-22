package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleDigitVerifierTest extends VerifierTest {

    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new DoubleDigitVerifier("21", secretCode);
    }

    @Test
    @DisplayName("check() should return true if there is no exactly 2 same digits in the given code")
    void checkMatchingTrue() {
        boolean res = check("444", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if there is exactly 2 same digits in the given code")
    void checkMatchingFalse() {
        boolean res = check("454", verifier);
        assertFalse(res);
    }
}