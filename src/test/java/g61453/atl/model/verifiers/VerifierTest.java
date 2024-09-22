package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

public class VerifierTest {
    public boolean check(String codeValue, Verifier verifier) {
        Code givenCode = new Code(codeValue);
        return verifier.check(givenCode);
    }
}
