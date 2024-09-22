package g61453.atl;


import g61453.atl.controller.ConsoleController;
import g61453.atl.model.Game;
import g61453.atl.model.ProblemsManager;
import g61453.atl.model.TuringMachine;
import g61453.atl.view.console.ViewConsole;

public class MainConsole {
    public static void main(String[] args) {
        ViewConsole viewConsole = new ViewConsole();
        ProblemsManager problemsManager = new ProblemsManager();
        Game game = new Game(problemsManager);
        TuringMachine turingMachine = new TuringMachine(game);
        ConsoleController consoleController = new ConsoleController(viewConsole, turingMachine);
        problemsManager.addObserver(viewConsole);
        game.addObserver(viewConsole);
        problemsManager.readProblems();
        consoleController.startGame();
    }
}
