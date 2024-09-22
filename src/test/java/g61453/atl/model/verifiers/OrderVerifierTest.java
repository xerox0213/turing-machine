package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderVerifierTest extends VerifierTest {

    Code secretCode;
    Verifier verifier;

    @BeforeEach
    void setUp() {
        secretCode = new Code("123");
        verifier = new OrderVerifier("22", secretCode);
    }

    @Test
    @DisplayName("check() should return true if the digits are ordered in ascending order")
    void checkMatchingTrue() {
        boolean res = check("345", verifier);
        assertTrue(res);
    }

    @Test
    @DisplayName("check() should return false if the digits are not ordered in ascending order")
    void checkMatchingFalse() {
        boolean res = check("443", verifier);
        assertFalse(res);
    }
}