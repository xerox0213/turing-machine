package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvenDigitCounterVerifierTest extends VerifierTest {

    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new EvenDigitCounterVerifier("17", secretCode);
    }

    @Test
    @DisplayName("check() should return true if the given code has a single even digit")
    void checkMatchingTrue() {
        boolean res = check("453", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the given code has not a single even digit")
    void checkMatchingFalse() {
        boolean res = check("443", verifier);
        assertFalse(res);
    }

}