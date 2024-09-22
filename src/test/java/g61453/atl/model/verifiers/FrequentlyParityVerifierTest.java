package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequentlyParityVerifierTest extends VerifierTest {

    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new FrequentlyParityVerifier("16", secretCode);
    }

    @Test
    @DisplayName("should return true if the given code has 2 odd digit")
    void checkMatchingTrue() {
        boolean res = check("345", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("should return false if the given code has 2 even digit")
    void checkMatchingFalse() {
        boolean res = check("245", verifier);
        assertFalse(res);
    }

}