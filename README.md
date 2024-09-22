# Turing Machine - Java Recreation

### Description
This project is a recreation of the **Turing Machine** board game in Java. Players must solve logical problems using **verifiers** to test different hypotheses. This version includes the first **16 problems** from the original game, along with additional features such as **undo/redo** functionality. Each problem has an associated level of **difficulty** and **luck**, and players can either select a specific problem or let the computer randomly choose one.

### Features
- Faithful implementation of the first 16 problems from the Turing Machine game.
- Verifiers that use the same validation criteria as in the original game.
- Graphical interface built with **JavaFX**.
- **Undo/Redo** functionality for navigating through previous actions.
- **Problem selection**: Players can choose a problem from the 16 available or let the computer select one randomly.
- Each problem has an associated **difficulty** and **luck** level.
- Victory or defeat mechanics:
  - If the player guesses the correct code, they win.
  - If the player guesses incorrectly, they lose.

### Requirements
- **Java 21** or later.
- **Maven** for dependency management.
- **JavaFX** for the graphical user interface.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/xerox0213/turing-machine.git
   ```
2. Navigate to the project directory:
   ```bash
   cd turing-machine
   ```
3. Compile and run the project using Maven:
   ```bash
   mvn clean javafx:run
   ```

### Usage
- Launch the game through the graphical interface.
- The player can choose a problem from the 16 available or let the computer randomly select one.
- Each problem is associated with a difficulty and luck level.
- Use the cards and verifiers to deduce the correct code.
- The **undo** and **redo** features allow players to reverse or replay previous actions.
- The game ends when the player successfully guesses the correct code or fails.

### Game Rules
This project follows the original rules of the **Turing Machine** board game. For a complete overview of the rules, please refer to [this link](https://cdn.1j1ju.com/medias/a6/12/e4-turing-machine-rulebook.pdf).
