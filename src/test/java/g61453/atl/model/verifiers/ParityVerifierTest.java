package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParityVerifierTest extends VerifierTest {
    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("101");
        verifier = new ParityVerifier("6", secretCode, 1);
    }

    @Test
    @DisplayName("check() should return true if the given code is of the same category as the secret code")
    void checkMatchingTrue() {
        boolean res = check("120", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the given code is not of the same category as the secret code")
    void checkMatchingFalse() {
        boolean res = check("355", verifier);
        assertFalse(res);
    }

}