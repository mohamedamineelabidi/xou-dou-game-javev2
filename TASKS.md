# Xou Dou Qi: Implementation Tasks

We will build the project by completing the following tasks in sequential order.

## 🧰 Phase 0: Project Setup

- **Task 0.1:** Create a new Maven project
- **Task 0.2:** Set up the package structure as defined in `ARCHITECTURE.md`

## 🧱 Phase 1: Core Model Implementation

- **Task 1.1:** Create `Rank.java` enum in `model` package
- **Task 1.2:** Create `Player.java` class in `model` package
- **Task 1.3:** Create `Piece.java` class in `model` package with `canCapture` method
- **Task 1.4:** Create `SquareType.java` enum in `model` package
- **Task 1.5:** Create `Board.java` class in `model` package with initial setup logic

## 🎮 Phase 2: Game Engine Logic

- **Task 2.1:** Create `Game.java` class in `game` package
- **Task 2.2:** Implement full move validation and execution logic:
  - Movement
  - River access
  - Jumping over river (Lion/Tiger)
  - Traps
  - Captures
  - Win condition

## 💻 Phase 3: UI and Controller Implementation

- **Task 3.1:** Create `ConsoleView.java` in `view` package with display methods
- **Task 3.2:** Create `GameController.java` in `controller` package
- **Task 3.3:** Implement main game loop in `GameController` to handle:
  - Login/Create account
  - Input parsing
  - Move execution
  - Board refresh
  - Turn switching
  - Game end detection

## 💾 Phase 4: Database Integration

- **Task 4.1:** Add `sqlite-jdbc` dependency to `pom.xml`
- **Task 4.2:** Create `DatabaseManager.java` in `db` package with:
  - `connect()`
  - `setupTables()`
  - `createPlayer()`
  - `loginPlayer()`
  - `saveGameResult()`
  - `getPlayerHistory()`
- **Task 4.3:** Integrate `DatabaseManager` into `GameController` for:
  - User authentication
  - Saving match results
  - Displaying history/stats

## 🏁 Phase 5: Finalization

- **Task 5.1:** Create `Main.java` to start the app
- **Task 5.2:** Perform final testing
- **Task 5.3:** Update `README.md` with final build/run instructions
- **Task 5.4:** Write unit tests for core logic (e.g., `canCapture`, move validation)