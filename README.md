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

- **Complete Game Logic**: Full implementation of all Jungle Chess rules
- **Enhanced Console UI**: Professional ANSI-colored interface with comprehensive legend
- **Input Validation**: Comprehensive move validation with helpful error messages
- **Interactive Help System**: Built-in command reference with detailed rules and tips
- **Enhanced User Experience**: Clear prompts, formatted current player display, and move feedback
- **MVC Architecture**: Clean separation of model, view, and controller
- **Turn Management**: Automatic player switching with visual indicators
- **Executable JAR**: Ready-to-run JAR file for easy distribution

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
- **Build Tool:** Maven
- **Architecture:** Model-View-Controller (MVC)
- **Testing:** JUnit (future implementation)
- **Database:** SQLite (planned for user accounts)

## 🛠️ Build and Run Instructions

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher

### Quick Start

1. **Clone and Navigate:**
   ```bash
   cd "c:\Users\elabi\xoudougamev2\XouDouQi"
   ```

2. **Run with Java (Classpath):**
   ```bash
   java -cp "target\classes" com.junglechess.Main
   ```

3. **Run Executable JAR:**
   ```bash
   java -jar target\XouDouQi-executable.jar
   ```

### Build Instructions

**Compile from Source:**
```bash
javac -d target/classes -cp target/classes src/main/java/com/junglechess/model/*.java src/main/java/com/junglechess/game/*.java src/main/java/com/junglechess/view/*.java src/main/java/com/junglechess/controller/*.java src/main/java/com/junglechess/*.java
```

## ✅ Recent Improvements (Completed)

### Console UI Enhancements
- **✅ Board Rendering**: Fixed alignment issues and improved visual clarity
- **✅ Interactive Legend**: Added comprehensive symbol and piece legend that displays once at startup
- **✅ Enhanced User Prompts**: Clear input guidance with command examples and format hints  
- **✅ Comprehensive Help System**: Detailed rules reference with beginner tips and game strategies
- **✅ JAR Execution**: Fixed ClassNotFoundException and created working executable JAR
- **✅ Enhanced Error Handling**: Helpful move validation feedback with specific guidance
- **✅ Professional Formatting**: ANSI colors, bordered displays, and improved readability

### User Experience Improvements
- **🎯 Clear Turn Indicators**: Visual player turn display with colored boxes
- **💡 Helpful Prompts**: Input examples and command suggestions
- **📖 Interactive Help**: Comprehensive rules and tips accessible via `help` command
- **✅ Move Feedback**: Success confirmations and detailed error explanations
- **🚪 Graceful Exit**: Confirmation prompts for quitting with thank you messages

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
└── controller/                 # Input handling
    └── GameController.java     # Command parsing and game loop management
```