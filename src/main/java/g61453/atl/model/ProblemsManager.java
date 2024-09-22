package g61453.atl.model;

import g61453.atl.oo.Observable;
import g61453.atl.oo.Observer;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages the collection and retrieval of problems in the Turing Machine game.
 * This class is responsible for reading problems from an external data source, maintaining a list of problems,
 * and notifying observers when problems are read. It implements the Observable interface to allow observers
 * to receive updates when new problems are available.
 */
public class ProblemsManager implements Observable {

    /**
     * The list of problems managed by the ProblemsManager.
     */
    private final List<Problem> problems;

    /**
     * The list of observers subscribed to receive updates from this ProblemsManager.
     */
    private final List<Observer> observers;

    /**
     * Constructs a ProblemsManager with an initial capacity for problems and observers.
     */
    public ProblemsManager() {
        problems = new ArrayList<>(16);
        observers = new ArrayList<>();
    }

    /**
     * Reads problems from an external data source (CSV file) and notifies observers.
     * If problems have already been read, this method does nothing.
     * The data source should follow a specific format: problemNo,difficulty,luck,secretCode,verifier1,verifier2,...
     * This method uses known_problems.csv as the default data source.
     *
     * @throws TuringMachineException if the specified data source file is not found.
     */
    public void readProblems() {
        if (problems.isEmpty()) {
            String fileName = "known_problems.csv";
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream(fileName);
            if (is == null) {
                throw new TuringMachineException("The file doesn't exist");
            }
            try {
                Scanner scanner = new Scanner(is);
                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    problems.add(createProblem(line));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        notifyObserver("problems-read", getProblems());
    }

    /**
     * Retrieves a specific problem based on its unique problem number.
     *
     * @param problemId The unique identifier of the problem to retrieve.
     * @return A copy of the specified problem if found, or null if not found.
     */
    public Problem getSpecificProblem(String problemId) {
        for (Problem problem : problems) {
            if (problem.getProblemNo().equals(problemId)) {
                return new Problem(problem);
            }
        }
        return null;
    }

    /**
     * Retrieves a random problem.
     *
     * @return A copy of the specified problem if found, or null if not found.
     */
    public Problem getRandomProblem() {
        Random rand = new Random();
        String randProblemNo = Integer.toString(rand.nextInt(16) + 1);
        return getSpecificProblem(randProblemNo);
    }

    /**
     * Adds an observer to the list of observers interested in receiving updates.
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies all subscribed observers about an event and the current state.
     *
     * @param event The event identifier.
     * @param state The current state (problems in this context).
     */
    @Override
    public void notifyObserver(String event, Object state) {
        observers.forEach(observer -> observer.update("problems-read", getProblems()));
    }

    /**
     * Gets a copy of the list of problems.
     *
     * @return A list of problems.
     */
    public List<Problem> getProblems() {
        return problems.stream().map(Problem::new).collect(Collectors.toList());
    }

    /**
     * Creates a Problem instance based on a CSV-formatted line.
     * The CSV line format should be: problemNo,difficulty,luck,secretCode,verifier1,verifier2,...
     *
     * @param line The CSV-formatted line containing problem information.
     * @return A new Problem instance created from the line.
     */
    private Problem createProblem(String line) {
        String[] strings = line.split(",");
        Queue<String> problemData = new LinkedList<>(Arrays.asList(strings));
        String problemId = problemData.remove();
        String difficulty = problemData.remove();
        String luck = problemData.remove();
        String secretCode = problemData.remove();
        List<String> verifierIds = new ArrayList<>(problemData);
        return new Problem(problemId, luck, difficulty, verifierIds, secretCode);
    }
}
