package g61453.atl.view.console;

import g61453.atl.model.GameData;
import g61453.atl.model.GameStatus;
import g61453.atl.model.Problem;
import g61453.atl.model.VerifierResult;
import g61453.atl.oo.Observer;

import java.util.List;

public class ViewConsole implements Observer {
    private static final String RESET = "\u001B[0m";
    private static final String RED_BOLD = "\033[1;31m";
    private static final String BLUE_BOLD = "\033[1;34m";
    private static final String YELLOW_BOLD = "\033[1;33m";
    private static final String PURPLE_BOLD = "\033[1;35m";

    public void initGame(GameData gameData) {
        Problem problem = gameData.problem();
        displaySelectedProblem(problem);
        displayCommands();
        displayVerifiers(problem.getVerifierNos());
        GameStatus gameStatus = gameData.gameStatus();
        displayGameStatus(gameStatus);
    }

    public void displayCommands() {
        displayTitle("=== TURING MACHINE GAME COMMANDS ===");
        System.out.println("- enter code: e <code>");
        System.out.println("- select verifier: s <verifier>");
        System.out.println("- move to next round: m");
        System.out.println("- guess code: g <code>");
        System.out.println("    code: 3-digit code from 1 to 5 each");
        System.out.println("    verifier: verifier number available in the given list");
    }

    public void displayAllProblems(List<Problem> problems) {
        displayTitle("=== LIST OF TURING MACHINE GAME PROBLEMS ===");
        for (Problem problem : problems) {
            displayProblem(problem);
        }
        System.out.println("- Random problem");
    }

    public void displaySelectedProblem(Problem problem) {
        displayTitle("=== SELECTED PROBLEM ===");
        displayProblem(problem);
    }

    public void displayVerifiers(List<String> verifierNos) {
        displayTitle("=== LIST OF VERIFIERS ===");
        for (String verifierNo : verifierNos) {
            System.out.println("- " + getVerifierRepresentation(verifierNo));
        }
    }

    public void displayGameStatus(GameStatus gameStatus) {
        String score = "Score: " + gameStatus.score();
        String verifierVerifiers = "Verified validators: " + gameStatus.verifiedValidators();
        String roundNo = "Rounds: " + gameStatus.roundNo();
        String code = "Your code: " + (gameStatus.playerCode() == null ? "?" : gameStatus.playerCode());
        String separator = " | ";
        displayTitle("=== GAME STATUS ===");
        System.out.println(score + separator + verifierVerifiers + separator + roundNo + separator + code);
    }

    public void displayVerifierResult(VerifierResult verifierResult) {
        String verifierNo = "Verifier n°" + verifierResult.verifierNo();
        boolean result = verifierResult.result();
        displayTitle("=== VERIFIER RESULT ===");
        System.out.println(verifierNo + " result: " + result);
    }

    public void displayError(String message) {
        displayTitle("=== ERROR ===");
        System.out.println(RED_BOLD + message + RESET);
    }

    public void displayEnterCommandMsg() {
        displayBlankLine();
        System.out.print(YELLOW_BOLD + "Enter a command: " + RESET);
    }

    public void displayAskSelectProblem() {
        displayBlankLine();
        System.out.print(YELLOW_BOLD + "Please, make your choice (give a problem n° or write random problem): " + RESET);
    }

    public void displayWinOrLose(boolean result) {
        displayTitle("=== GUESS CODE RESULT ===");
        if (result) {
            System.out.println(PURPLE_BOLD + "Bingo! You've cracked the code! Excellent job!" + RESET);
        } else {
            System.out.println(RED_BOLD + "Wops, it's not the secret code, you have been defeated by the machine!" + RESET);
        }
    }

    @Override
    public void update(String event, Object state) {
        switch (event) {
            case "problems-read":
                List<Problem> problems = (List<Problem>) state;
                displayAllProblems(problems);
                break;
            case "game-init":
                GameData gameData = (GameData) state;
                initGame(gameData);
                break;
            case "game-status":
                GameStatus gameStatus = (GameStatus) state;
                displayGameStatus(gameStatus);
                break;
            case "verifier-result":
                VerifierResult verifierResult = (VerifierResult) state;
                displayVerifierResult(verifierResult);
                break;
            case "guess-secret-code":
                boolean result = (boolean) state;
                displayWinOrLose(result);
        }
    }

    private void displayTitle(String title) {
        displayBlankLine();
        System.out.println(BLUE_BOLD + title + RESET);
    }

    private void displayProblem(Problem problem) {
        System.out.println("- " + problem);
    }

    private void displayBlankLine() {
        System.out.println();
    }

    private String getVerifierRepresentation(String verifierNo) {
        String n = "verifier n°" + verifierNo + ":";
        return switch (verifierNo) {
            case "1" -> n + " verifies the first digit to 1 => " + "(smaller | equal | larger)";
            case "2" -> n + " verifies the first digit to 3 => " + "(smaller | equal | larger)";
            case "3" -> n + " verifies the second digit to 3 => " + "(smaller | equal | larger)";
            case "4" -> n + " verifies the second digit to 4 => " + "(smaller | equal | larger)";
            case "5" -> n + " verifies if the first digit is even or odd => " + "(odd | even)";
            case "6" -> n + " verifies if the second digit is even or odd =>  " + "(odd | even)";
            case "7" -> n + " verifies if the third digit is even or odd => " + "(odd | even)";
            case "8" -> n + " verifies the number of 1s in the code => " + "(none | one | two | three)";
            case "9" -> n + " verifies the number of 3s in the code => " + "(none |one | two | three)";
            case "10" -> n + " verifies the number of 4s in the code => " + "(none | one | two | three)";
            case "11" ->
                    n + " verifies the first number compared to the second number => " + "(smaller | equal | larger)";
            case "12" ->
                    n + " verifies the first number compared to the third number => " + "(smaller | equal | larger)";
            case "13" ->
                    n + " verifies the second number compared to the third number => " + "(smaller | equal | larger)";
            case "14" ->
                    n + " verifies which number is smaller than the others => " + "(none | first | second | third)";
            case "15" -> n + " verifies which number is larger than the others => " + "(none | first | second | third)";
            case "16" ->
                    n + " verifies the number of even numbers compared to the number of odd number => " + "(majority odd | majority even)";
            case "17" -> n + " verifies how many even numbers there are in the code => " + "(none | one | two | three)";
            case "18" -> n + " verifies if the sum of all the numbers is even or odd => " + "(odd | even)";
            case "19" ->
                    n + " verifies the sum of the first and second number compared to 6 => " + "(smaller | larger | equal)";
            case "20" -> n + " verifies if a number repeats itself in the code => " + "(none | double | triple)";
            case "21" -> n + " verifies if there is a number present exactly twice => " + "(true | false)";
            default ->
                    n + " verifies if the 3 numbers in the code are in ascending order, descending order, or no order => " +
                            "(none | ascending | descending)";
        };
    }
}
