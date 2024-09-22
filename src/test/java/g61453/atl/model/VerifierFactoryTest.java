package g61453.atl.model;

import g61453.atl.model.verifiers.Verifier;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifierFactoryTest {

    VerifierFactory verifierFactory = new VerifierFactory("123");

    @RepeatedTest(value = 16, name = "createVerifier() should create the verifier n°{currentRepetition}")
    void createVerifier(RepetitionInfo repetitionInfo) {
        String verifierNo = Integer.toString(repetitionInfo.getCurrentRepetition());
        Verifier verifier = verifierFactory.createVerifier(verifierNo);
        assertNotNull(verifier);
        assertEquals(verifierNo, verifier.getVerifierNo());
    }
}