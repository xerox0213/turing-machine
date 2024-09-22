package g61453.atl.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProblemsManagerTest {

    ProblemsManager problemsManager = new ProblemsManager();

    @BeforeEach
    void readProblems() {
        problemsManager.readProblems();
    }

    @Test
    @DisplayName("readProblems() should fetch turing game problem on the known_problems file")
    void readProblemsShouldFetchTuringGameProblems() {
        int listSize = problemsManager.getProblems().size();
        assertEquals(16, listSize);
    }

    @RepeatedTest(value = 16, name = "getSpecificProblem() should return the problem n°{currentRepetition} if the given problem n° is {currentRepetition}")
    void getSpecificProblemShouldReturnSpecificProblem(RepetitionInfo repetitionInfo) {
        String problemNo = Integer.toString(repetitionInfo.getCurrentRepetition());
        Problem problem = problemsManager.getSpecificProblem(problemNo);
        assertEquals(problemNo, problem.getProblemNo());
    }

    @Test
    @DisplayName("getSpecificProblem() should return null if the given problem n° does not exist")
    void getSpecificProblemShouldReturnNull() {
        Problem problem = problemsManager.getSpecificProblem("12112");
        assertNull(problem);
    }

    @Test
    @DisplayName("getRandomProblem() should return a random problem")
    void getRandomProblemShouldReturnRandomProblem() {
        Problem problem = problemsManager.getRandomProblem();
        assertNotNull(problem);
    }
}