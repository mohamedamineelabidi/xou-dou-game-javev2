# 🐾 Xou Dou Qi (Jungle Chess) - Java Implementation

A complete two-player command-line implementation of **Xou Dou Qi** (鬥獸棋), also known as *Dou Shou Qi* or *Jungle Chess*. This classic Chinese strategy board game features animal pieces with unique movement and capture rules in a jungle-themed battlefield.

## 🎮 Game Overview

Xou Dou Qi is an ancient Chinese board game where two players command armies of animals trying to reach the opponent's sanctuary. Each animal has different strengths and special abilities, creating a rich strategic experience.

### 🏆 Victory Conditions
- **Primary Goal**: Move any of your pieces into the opponent's sanctuary (den)
- **Alternative**: Capture all opponent pieces (rare)

## 🐯 Game Pieces and Hierarchy

Animals are ranked by strength, with higher-ranked animals capturing lower-ranked ones:

| Rank | Animal | Strength | Special Abilities |
|------|--------|----------|-------------------|
| 8 | 🐘 Elephant | Highest | Cannot be captured by any animal except Rat |
| 7 | 🦁 Lion | Very High | Can jump across rivers horizontally/vertically |
| 6 | 🐅 Tiger | High | Can jump across rivers horizontally/vertically |
| 5 | 🐆 Panther | Medium-High | Standard movement |
| 4 | 🐕 Dog | Medium | Standard movement |
| 3 | 🐺 Wolf | Medium-Low | Standard movement |
| 2 | 🐱 Cat | Low | Standard movement |
| 1 | 🐭 Rat | Lowest | Can enter rivers, can capture Elephant |

### 🎯 Special Rules
- **Rat vs Elephant**: The tiny Rat can capture the mighty Elephant!
- **River Access**: Only Rats can enter and move through river squares
- **Jumping**: Lions and Tigers can jump over rivers (if no Rat blocks the path)
- **Traps**: Pieces in enemy traps lose all defensive power and can be captured by any enemy piece

## 🗺️ Board Layout

```
   A B C D E F G
7  [T] - - [S] - - [T]  ← RED Sanctuary & Traps
6  - - - [R] - - -
5  - [R] [R] - [R] [R] -
4  [R] [R] [R] - [R] [R] [R]  ← River
3  - [R] [R] - [R] [R] -
2  - - - [R] - - -
1  [T] - - [S] - - [T]  ← BLUE Sanctuary & Traps
```

**Legend:**
- `[S]` = Sanctuary (Den) - Goal destination
- `[T]` = Trap - Pieces lose defensive power here
- `[R]` = River - Only Rats can enter
- `-` = Normal land square

## 🔍 Features

### 🎮 Core Game Features
- **Complete Game Logic**: Full implementation of all Jungle Chess rules
- **Enhanced Console UI**: Professional ANSI-colored interface with comprehensive legend
- **Input Validation**: Comprehensive move validation with helpful error messages
- **Interactive Help System**: Built-in command reference with detailed rules and tips
- **Enhanced User Experience**: Clear prompts, formatted current player display, and move feedback
- **MVC Architecture**: Clean separation of model, view, and controller
- **Turn Management**: Automatic player switching with visual indicators

### 🗄️ Database Integration Features
- **🔗 SQLite Integration**: Complete database persistence with JDBC driver
- **👤 User Authentication**: Secure player registration and login system
- **📊 Match History Tracking**: Automatic game result storage with timestamps
- **📈 Player Statistics**: Real-time win/loss calculation and display
- **🔒 Data Persistence**: Robust SQLite database with proper schema
- **🛡️ Error Handling**: Comprehensive database exception management
- **✅ Data Validation**: Username uniqueness and credential verification
- **🔧 Resource Management**: Proper connection handling and cleanup

### 🚀 Distribution Features
- **Executable JAR**: Ready-to-run JAR file for easy distribution
- **Batch Scripts**: Convenient launch scripts with proper classpath
- **Cross-Platform**: Works on Windows, macOS, and Linux

## 🎮 Game Commands

Once the game starts, you can use these commands:

- **`move <from> <to>`** - Move a piece (e.g., `move A1 A2`, `move G7 F7`)
- **`help`** - Display comprehensive help with rules, tips, and examples
- **`quit`** or **`exit`** - Exit the game (with confirmation prompt)

### 📋 Coordinate System
- **Columns**: A-G (left to right)
- **Rows**: 1-9 (top to bottom)  
- **Examples**: A1 (top-left), G9 (bottom-right), D5 (center)

## 🎨 Console Interface Features

The enhanced console interface includes:
- **🏷️ Interactive Legend**: Displays once at startup with color-coded symbols
- **🎯 Current Player Display**: Clear turn indicators with player colors
- **💬 Enhanced Prompts**: Helpful input guidance with command examples
- **✅ Move Feedback**: Success confirmations and detailed error explanations
- **📚 Comprehensive Help**: Complete rules reference with beginner tips

## ⚙️ Tech Stack

- **Language:** Java 11+
- **Build Tool:** Maven 3.6+
- **Database:** SQLite 3.36.0.3 with JDBC driver
- **Architecture:** Model-View-Controller (MVC)
- **Testing:** JUnit + Custom Database Tests
- **Data Persistence:** Local SQLite database file (`junglechess.db`)
- **UI:** Enhanced console interface with ANSI colors
- **Dependencies:** SQLite JDBC, JUnit (test scope)

## 💾 Database Features

### 🔐 User Management System
- **Player Registration**: Create accounts with unique usernames
- **Secure Authentication**: Password-based login system
- **Duplicate Prevention**: Automatic handling of existing username conflicts
- **Account Validation**: Username and password requirement enforcement

### 📊 Match Tracking System
- **Automatic Result Storage**: Game outcomes saved immediately after completion
- **Timestamp Recording**: Each match includes precise completion timestamp
- **Winner/Loser Attribution**: Clear result tracking linked to player accounts
- **Historical Data**: Persistent record of all matches played

### 📈 Statistics System
- **Real-time Calculation**: Win/loss counts computed dynamically from game history
- **Pre-game Display**: Statistics shown before each match starts
- **Post-game Updates**: Updated statistics displayed after match completion
- **Player Profiles**: Individual statistics tracking per registered user

### 🗃️ Database Schema

#### Players Table
```sql
CREATE TABLE IF NOT EXISTS players (
    id INTEGER PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);
```

#### Game History Table
```sql
CREATE TABLE IF NOT EXISTS game_history (
    id INTEGER PRIMARY KEY,
    winner TEXT NOT NULL,
    loser TEXT NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🛠️ Build and Run Instructions

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Quick Start

1. **Clone and Navigate:**
   ```bash
   cd "c:\Users\elabi\xoudougamev2\XouDouQi"
   ```

2. **Run with Database Support:**
   ```bash
   java -cp "target\classes;sqlite-jdbc-3.36.0.3.jar" com.junglechess.Main
   ```

3. **Run with Batch Script (Recommended):**
   ```bash
   run-game.bat
   ```

4. **Run Database Tests:**
   ```bash
   java -cp "target\classes;sqlite-jdbc-3.36.0.3.jar" com.junglechess.test.DatabaseTest
   ```

### First Time Setup

When you first run the game, you'll be prompted to:
1. **Create accounts** for both players (username and password)
2. **Login** with your credentials for future sessions
3. **View statistics** from previous games (if any)
4. **Play the game** with persistent match history

### Game Flow with Database

1. **Player 1 Authentication**: Login or create new account
2. **Player 2 Authentication**: Login or create new account  
3. **Statistics Display**: Shows current win/loss records
4. **Game Play**: Standard Jungle Chess gameplay
5. **Result Saving**: Automatic match result storage
6. **Updated Statistics**: Post-game win/loss display

### Build Instructions

**Compile from Source:**
```bash
# Compile all Java files including database components
javac -cp "sqlite-jdbc-3.36.0.3.jar" -d target/classes src/main/java/com/junglechess/model/*.java src/main/java/com/junglechess/game/*.java src/main/java/com/junglechess/view/*.java src/main/java/com/junglechess/controller/*.java src/main/java/com/junglechess/db/*.java src/main/java/com/junglechess/*.java
```

**Download SQLite JDBC Dependency:**
```bash
# Download SQLite JDBC driver (if not already present)
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar" -OutFile "sqlite-jdbc-3.36.0.3.jar"
```

## ✅ Final Release Status (Phase 5 Completed)

### Phase 5: Finalization ✅
- **✅ Main.java Entry Point**: Completed with proper database initialization and authentication flow
- **✅ Unit Testing Framework**: Created comprehensive JUnit test suite for core game logic
  - **PieceTest.java**: Tests capture logic, rank hierarchy, and special rules (Rat vs Elephant, trapped pieces)
  - **GameTest.java**: Tests move validation, river access, jumping mechanics, and win conditions
- **✅ Final Testing**: Build verification, run testing, and end-to-end gameplay validation
- **✅ Documentation**: Updated README with final build instructions and feature confirmation
- **✅ Code Quality**: Enhanced error handling and input validation throughout the application

### All Features Confirmed Working ✅
- **🎮 Complete Game Logic**: All Jungle Chess rules implemented and tested
- **👤 Player Account Management**: Registration, login, and authentication system
- **🎨 Enhanced Console UI**: Professional ANSI-colored interface with comprehensive help
- **📊 Game History & Statistics**: Persistent match tracking with SQLite database
- **🏗️ Clean Architecture**: Well-structured MVC design with proper separation of concerns
- **🧪 Unit Tests**: Comprehensive test coverage for critical game components

## 🚀 Final Build and Run Instructions

### Quick Start (Recommended)
```bash
# To build (if Maven is available):
mvn clean install

# To run the game:
java -jar target/XouDouQi-1.0-SNAPSHOT.jar
```

### Alternative Run Methods
```bash
# Method 1: Using batch script (Windows)
run-game.bat

# Method 2: Direct command line
java -cp "target\classes;sqlite-jdbc-3.36.0.3.jar" com.junglechess.Main

# Method 3: Using executable JAR
java -jar target/XouDouQi-executable.jar
```

### Build from Source
```bash
# Compile all components
javac -cp "sqlite-jdbc-3.36.0.3.jar" -d target/classes src/main/java/com/junglechess/model/*.java src/main/java/com/junglechess/game/*.java src/main/java/com/junglechess/view/*.java src/main/java/com/junglechess/controller/*.java src/main/java/com/junglechess/db/*.java src/main/java/com/junglechess/*.java

# Run the compiled application
java -cp "target\classes;sqlite-jdbc-3.36.0.3.jar" com.junglechess.Main
```

## ✅ Recent Improvements (Completed)

### Phase 4: Database Integration ✅
- **✅ SQLite Integration**: Complete database persistence with JDBC driver
- **✅ User Authentication**: Secure player registration and login system
- **✅ Match History Tracking**: Automatic game result storage with timestamps
- **✅ Player Statistics**: Real-time win/loss calculation and display
- **✅ Database Schema**: Proper tables for players and game history
- **✅ Error Handling**: Robust database exception management
- **✅ Data Validation**: Username uniqueness and credential verification
- **✅ Resource Management**: Proper connection handling and cleanup

### Console UI Enhancements ✅
- **✅ Board Rendering**: Fixed alignment issues and improved visual clarity
- **✅ Interactive Legend**: Added comprehensive symbol and piece legend that displays once at startup
- **✅ Enhanced User Prompts**: Clear input guidance with command examples and format hints  
- **✅ Comprehensive Help System**: Detailed rules reference with beginner tips and game strategies
- **✅ JAR Execution**: Fixed ClassNotFoundException and created working executable JAR
- **✅ Enhanced Error Handling**: Helpful move validation feedback with specific guidance
- **✅ Professional Formatting**: ANSI colors, bordered displays, and improved readability

### User Experience Improvements ✅
- **🎯 Clear Turn Indicators**: Visual player turn display with colored boxes
- **💡 Helpful Prompts**: Input examples and command suggestions
- **📖 Interactive Help**: Comprehensive rules and tips accessible via `help` command
- **✅ Move Feedback**: Success confirmations and detailed error explanations
- **🚪 Graceful Exit**: Confirmation prompts for quitting with thank you messages
- **👤 User Accounts**: Persistent player profiles with statistics tracking
- **📊 Statistics Display**: Pre and post-game win/loss statistics

## 🏗️ Project Architecture

```
src/main/java/com/junglechess/
├── Main.java                    # Application entry point
├── model/                       # Game data structures
│   ├── Board.java              # 9x7 game board with special squares
│   ├── Piece.java              # Animal pieces with ranks and abilities  
│   ├── Player.java             # Player information and piece ownership
│   ├── Rank.java               # Animal hierarchy enum (Elephant to Rat)
│   ├── Square.java             # Individual board squares
│   └── SquareType.java         # Square types (Normal, River, Trap, Sanctuary)
├── game/                       # Core game logic
│   └── Game.java               # Move validation, turn management, win conditions
├── view/                       # User interface
│   └── ConsoleView.java        # Enhanced console display with ANSI colors
├── controller/                 # Input handling
│   └── GameController.java     # Command parsing, game loop, and database integration
├── db/                         # Database layer
│   └── DatabaseManager.java   # SQLite connection, authentication, and data persistence
└── test/                       # Testing utilities
    └── DatabaseTest.java       # Comprehensive database functionality tests
```

## 📊 Database Schema

### Players Table
```sql
CREATE TABLE IF NOT EXISTS players (
    id INTEGER PRIMARY KEY,
    username TEXT UNIQUE,
    password TEXT
);
```

### Game History Table  
```sql
CREATE TABLE IF NOT EXISTS game_history (
    id INTEGER PRIMARY KEY,
    winner TEXT,
    loser TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🎮 Database Features

### User Management
- **Registration**: Create new player accounts with unique usernames
- **Authentication**: Secure login with username/password validation
- **Duplicate Prevention**: Automatic handling of existing username conflicts

### Match Tracking
- **Automatic Saving**: Game results stored immediately after completion
- **Timestamp Recording**: Each match includes completion timestamp
- **Winner/Loser Tracking**: Clear result attribution to player accounts

### Statistics System
- **Real-time Calculation**: Win/loss counts computed from game history
- **Pre-game Display**: Show statistics before each match starts
- **Post-game Updates**: Display updated statistics after match completion
- **Historical Data**: Persistent record of all matches played

## 🧪 Testing

### Database Testing
```bash
# Run comprehensive database tests
java -cp "target\classes;sqlite-jdbc-3.36.0.3.jar" com.junglechess.test.DatabaseTest
```

**Test Coverage:**
- ✅ Database connection and table creation
- ✅ User registration and duplicate handling  
- ✅ Authentication (valid/invalid credentials)
- ✅ Game result storage and retrieval
- ✅ Player statistics calculation
- ✅ Error handling and edge cases

## 📁 Project Files

### Core Game Files
- `src/main/java/com/junglechess/Main.java` - Application entry point
- `src/main/java/com/junglechess/controller/GameController.java` - Main game controller with database integration
- `src/main/java/com/junglechess/db/DatabaseManager.java` - Complete database management system

### Database Files
- `junglechess.db` - SQLite database file (auto-created)
- `sqlite-jdbc-3.36.0.3.jar` - SQLite JDBC driver dependency

### Utility Scripts
- `run-game.bat` - Launch script with proper classpath
- `demo-database.bat` - Database integration demonstration
- `pom.xml` - Maven configuration with SQLite dependency

## 🎮 Gameplay Example

### Sample Game Session
```
=== WELCOME TO XOU DOU QI (JUNGLE CHESS) ===

Player 1 Authentication:
> Username: alice
> Password: ****
Login successful!

Player 2 Authentication:
> Username: bob  
> Password: ****
Login successful!

=== PLAYER STATISTICS ===
alice: 3 wins, 1 loss
bob: 1 win, 3 losses

Starting game with alice (Red) vs bob (Blue)...

   A B C D E F G
7  🐘 🦁 🐅 [S] 🐆 🐕 🐺  ← BLUE Pieces
6  - - - 🐱 - - -
5  - [~] [~] - [~] [~] -
4  [~] [~] [~] - [~] [~] [~]  ← River
3  - [~] [~] - [~] [~] -
2  - - - 🐭 - - -
1  🐺 🐕 🐆 [S] 🐅 🦁 🐘  ← RED Pieces (Your pieces)

🔴 RED Player (alice) - Your Turn
Enter command (move <from> <to>, help, quit): move D2 D3
✅ Moved RAT from D2 to D3

🔵 BLUE Player (bob) - Your Turn  
Enter command (move <from> <to>, help, quit): move D6 D5
✅ Moved CAT from D6 to D5

🔴 RED Player (alice) - Your Turn
Enter command (move <from> <to>, help, quit): move D3 D4
✅ Moved RAT into river at D4

# ... game continues until victory condition ...

🎉 GAME OVER! 🎉
🔴 RED Player (alice) WINS by reaching the enemy sanctuary!

=== UPDATED STATISTICS ===
alice: 4 wins, 1 loss  
bob: 1 win, 4 losses

Thanks for playing Xou Dou Qi!
```