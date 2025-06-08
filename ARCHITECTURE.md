
---

## 🧱 2. ARCHITECTURE.md
XouDouQi/
├── pom.xml
├── README.md
├── ARCHITECTURE.md
├── TASKS.md
└── src/
    └── main/
        └── java/
            └── com/
                └── junglechess/
                    ├── model/
                    │   ├── Rank.java
                    │   ├── Player.java
                    │   ├── Piece.java
                    │   ├── SquareType.java
                    │   ├── Square.java
                    │   └── Board.java
                    ├── view/
                    │   └── ConsoleView.java
                    ├── controller/
                    │   └── GameController.java
                    ├── game/
                    │   └── Game.java
                    ├── db/
                    │   └── DatabaseManager.java
                    └── Main.java
```markdown
# Xou Dou Qi: Software Architecture

This project strictly follows the **Model-View-Controller (MVC)** design pattern to ensure a clean separation of concerns.

## 📁 Directory Structure

All code resides under the base package `com.junglechess`, structured as follows:
## 🧩 Component Breakdown

### 1. Model (`com.junglechess.model`)

Represents the **game state** and contains core data structures.

- `Rank.java`: An `enum` representing animal hierarchy (`ELEPHANT: 8 -> RAT: 1`)
- `Piece.java`: Represents a single animal piece
  - Attributes: `Rank rank`, `Player owner`
  - Method: `boolean canCapture(Piece target)` — includes Rat vs Elephant exception
- `Player.java`: Represents a player
- `SquareType.java`: An `enum` for board zones (`NORMAL`, `RIVIERE`, `PIEGE`, `SANCTUAIRE_RED`, `SANCTUAIRE_BLUE`)
- `Board.java`: Manages the 9x7 grid and initial layout

### 2. View (`com.junglechess.view`)

Handles all **console output** only.

- `ConsoleView.java`: Static methods for rendering the game
  - `displayBoard(Board board)`
  - `showMessage(String message)`
  - `displayHelp()` *(optional)*

### 3. Controller (`com.junglechess.controller`)

Manages **user input** and coordinates between model and view.

- `GameController.java`:
  - Handles login/create account flow
  - Contains the main game loop
  - Parses user commands
  - Calls game logic and updates view

### 4. Game Engine (`com.junglechess.game`)

Contains the **rule engine** that validates and executes moves.

- `Game.java`:
  - Validates movement rules (adjacent, river, jump, trap)
  - Handles capture logic
  - Tracks current player
  - Detects win condition

### 5. Database (`com.junglechess.db`)

Handles **persistence** using SQLite via JDBC.

- `DatabaseManager.java`:
  - Connects to `junglechess.db`
  - Sets up tables (`players`, `game_history`)
  - Methods:
    - `createPlayer()`
    - `loginPlayer()`
    - `saveGameResult()`
    - `getPlayerHistory()`