package g61453.atl.controller;

import g61453.atl.model.TuringMachine;
import g61453.atl.model.TuringMachineException;
import g61453.atl.view.console.ViewConsole;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleController {
    private final ViewConsole viewConsole;
    private final TuringMachine turingMachine;
    private final Pattern CODE_PATTERN = Pattern.compile("[1-5]{3}");
    private final Pattern VERIFIER_PATTERN = Pattern.compile("[1-9][0-9]*");
    private final Pattern ENTER_CODE_COMMAND_PATTERN = Pattern.compile("^e " + CODE_PATTERN + "$");
    private final Pattern SELECT_VERIFIER_COMMAND_PATTERN = Pattern.compile("^s " + VERIFIER_PATTERN + "$");
    private final Pattern NEXT_ROUND_COMMAND_PATTERN = Pattern.compile("^m$");
    private final Pattern GUESS_CODE_COMMAND_PATTERN = Pattern.compile("^g " + CODE_PATTERN + "$");

    public ConsoleController(ViewConsole viewConsole, TuringMachine turingMachine) {
        this.viewConsole = viewConsole;
        this.turingMachine = turingMachine;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        askSelectProblem(scanner);
        while (true) {
            viewConsole.displayEnterCommandMsg();
            String command = scanner.nextLine();
            checkCommand(command);
        }
    }

    private void askSelectProblem(Scanner scanner) {
        Pattern RANDOM_PROBLEM_PATTERN = Pattern.compile("Random problem", Pattern.CASE_INSENSITIVE);
        Pattern ANSWER_PATTERN = Pattern.compile("^" + VERIFIER_PATTERN + "|" + RANDOM_PROBLEM_PATTERN + "$", Pattern.CASE_INSENSITIVE);
        String answer;
        do {
            viewConsole.displayAskSelectProblem();
            answer = scanner.nextLine();
            answer = answer.trim();
        } while (!ANSWER_PATTERN.matcher(answer).find());

        try {
            if (RANDOM_PROBLEM_PATTERN.matcher(answer).find()) {
                turingMachine.initGame();
            } else {
                turingMachine.initGame(answer);
            }
        } catch (TuringMachineException tme) {
            viewConsole.displayError(tme.getMessage());
            askSelectProblem(scanner);
        }
    }

    private void checkCommand(String command) {
        command = command.trim();
        if (isMatchingEnterCodeCommand(command)) {
            String code = extractOperands(command)[0];
            enterCode(code);
        } else if (isMatchingSelectVerifierCommand(command)) {
            String verifierNo = extractOperands(command)[0];
            selectVerifier(verifierNo);
        } else if (isMatchingNextRoundCommand(command)) {
            nextRound();
        } else if (isMatchingGuessCodeCommand(command)) {
            String code = extractOperands(command)[0];
            guessSecretCode(code);
        } else {
            viewConsole.displayError("The command doesn't exist");
        }
    }

    private void enterCode(String code) {
        try {
            turingMachine.enterCode(code);
        } catch (TuringMachineException tme) {
            viewConsole.displayError(tme.getMessage());
        }
    }

    private void selectVerifier(String verifierNo) {
        try {
            turingMachine.selectVerifier(verifierNo);
        } catch (TuringMachineException tme) {
            viewConsole.displayError(tme.getMessage());
        }
    }

    private void nextRound() {
        turingMachine.nextRound();
    }

    private void guessSecretCode(String code) {
        turingMachine.guessSecretCode(code);
        quitGame();
    }

    private void quitGame() {
        System.exit(0);
    }

    private String[] extractOperands(String command) {
        Pattern reg = Pattern.compile("^[a-z] ");
        command = command.replaceAll(reg.toString(), "");
        return command.split(" ");
    }

    private boolean isMatchingEnterCodeCommand(String command) {
        return ENTER_CODE_COMMAND_PATTERN.matcher(command).find();
    }

    private boolean isMatchingSelectVerifierCommand(String command) {
        return SELECT_VERIFIER_COMMAND_PATTERN.matcher(command).find();
    }

    private boolean isMatchingNextRoundCommand(String command) {
        return NEXT_ROUND_COMMAND_PATTERN.matcher(command).find();
    }

    private boolean isMatchingGuessCodeCommand(String command) {
        return GUESS_CODE_COMMAND_PATTERN.matcher(command).find();
    }
}
