package g61453.atl.model.verifiers;

import g61453.atl.model.Code;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtremumVerifierTest extends VerifierTest {
    Code code = new Code("123");
    Verifier verifier;

    @Nested
    @DisplayName("Maximum verifier")
    class MaxVerifierTest {

        @BeforeEach
        void setUp() {
            verifier = new ExtremumVerifier("15", code, ExtremumVerifier.Extremum.MAX);
        }

        @Test
        @DisplayName("check should return true if the last digit is the max digit")
        void checkMatchingTrue() {
            boolean res = check("345", verifier);
            assertTrue(res);
        }

        @Test
        @DisplayName("check should return false if the last digit is not the max digit")
        void checkMatchingFalse() {
            boolean res = check("555", verifier);
            assertFalse(res);
        }
    }

    @Nested
    @DisplayName("Minimum verifier")
    class MinVerifierTest {

        @BeforeEach
        void setUp() {
            verifier = new ExtremumVerifier("14",code, ExtremumVerifier.Extremum.MIN);
        }

        @Test
        @DisplayName("check should return true if the first digit is the min digit")
        void checkMatchingTrue() {
            boolean res = check("345", verifier);
            assertTrue(res);
        }

        @Test
        @DisplayName("check should return false if the first digit is not the min digit")
        void checkMatchingFalse() {
            boolean res = check("101", verifier);
            assertFalse(res);
        }
    }
}