package g61453.atl.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    Round round = new Round();

    @Test
    @DisplayName("setPlayerCode() should set the given player code")
    void setPlayerCodeShouldSetTheGivenPlayerCode() {
        String code = "124";
        round.setPlayerCode(code);
        assertEquals(code, round.getPlayerCode());
    }

    @Test
    @DisplayName("setPlayerCode() should throw an turing machine exception if there is selected verifier")
    void setPlayerCodeShouldThrowAnException() {
        String code = "125";
        String verifierNo = "1";
        round.addVerifierNo(verifierNo);
        assertThrows(TuringMachineException.class, () -> round.setPlayerCode(code));
        assertNull(round.getPlayerCode());
    }

    @Test
    @DisplayName("addVerifierNo() should add verifier n°")
    void addVerifierShouldAddVerifierNo() {
        String verifierNo = "1";
        round.addVerifierNo(verifierNo);
        assertEquals(1, round.getNumberSelectedVerifier());
    }

    @Test
    @DisplayName("isVerifierAlreadyUsed() should return true if the verifier is already used")
    void isVerifierAlreadyUsedShouldReturnTrue() {
        String verifierNo = "1";
        round.addVerifierNo(verifierNo);
        assertTrue(round.isVerifierAlreadyUsed(verifierNo));
    }

    @Test
    @DisplayName("isVerifierAlreadyUsed() should return false if the verifier is not already used")
    void isVerifierAlreadyUsedShouldReturnFalse() {
        String verifierNo = "1";
        assertFalse(round.isVerifierAlreadyUsed(verifierNo));
    }

    @Test
    @DisplayName("unselectVerifier() should remove the given verifier n° of the selected verifier n°")
    void unselectVerifier() {
        String verifierNo = "1";
        round.addVerifierNo(verifierNo);
        round.unselectVerifier(verifierNo);
        assertEquals(0, round.getNumberSelectedVerifier());
    }
}