# AtomicBomber
# Atomic Bomber

**Atomic Bomber** is a simple yet engaging game developed as a project for the Advanced Programming (Object-Oriented) course at Sharif University of Technology. The game is implemented using **Java** and **JavaFX**, following the **MVC architecture**. In this game, you control an airplane and must destroy ground targets by dropping various types of bombs.

## Key Features

- **Attractive Graphics**: Utilizes JavaFX for a beautiful and interactive user interface.
- **Simple Controls**: Control the airplane using keyboard keys.
- **Multiple Bomb Types**: Ability to drop regular, radioactive, and cluster bombs.
- **Game Phases**: The game is divided into multiple waves, each introducing new challenges.
- **Scoring System**: Scores are calculated based on accuracy and mission completion speed.
- **Save and Load**: Ability to save user data and high scores for future sessions.

## Prerequisites

To run this project, you need to install the following:

- **JDK 11 or higher**
- **JavaFX SDK**

## How to Run

1. First, clone the repository:

```bash
git clone https://github.com/Mazm28/AtomicBomber.git
```

2. Navigate to the project directory:

```bash
cd AtomicBomber
```

3. Compile and run the project:

```bash
javac --module-path path/to/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml *.java
java --module-path path/to/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml Main
```

(Note: Replace `path/to/javafx-sdk-XX` with the path to your JavaFX SDK installation.)

## Controls

- **Move Left**: `Left Arrow` or `A`
- **Move Right**: `Right Arrow` or `D`
- **Move Up**: `Up Arrow` or `W`
- **Move Down**: `Down Arrow` or `S`
- **Drop Regular Bomb**: `Space`
- **Drop Radioactive Bomb**: `R`
- **Drop Cluster Bomb**: `C`
- **Freeze Mode**: `Tab`
- **Pause Game**: `Pause` or `Esc`

## Project Structure

The project follows the **MVC architecture**, and the file structure is as follows:

- **Model**: Contains classes related to game data such as `Player`, `Bomb`, `Enemy`, etc.
- **View**: Includes FXML files and JavaFX controllers for the user interface.
- **Controller**: Contains controller classes that manage the game logic.

## Main Sections of the Project

### 1. Menus
- **User Account**: Register, login, and play as a guest.
- **Profile Menu**: Change username, password, and delete account.
- **Avatar Menu**: Choose an avatar from predefined images or upload a custom one.
- **Main Menu**: Start a new game, continue a saved game, view the leaderboard, and access settings.
- **Leaderboard**: Display the top 10 players with their scores and final wave.
- **Settings**: Adjust difficulty level, mute sound, and change control keys.

### 2. Main Game
- **Airplane Movement**: Control the airplane using keyboard keys.
- **Targets**: Tanks, trucks, buildings, bunkers, and trees.
- **Bomb Types**: Regular, radioactive, and cluster bombs.
- **Freeze Mode**: Temporarily pause the game for better planning.
- **Game Waves**: Three waves with different challenges.

### 3. Cheat Codes
- **Skip Wave**: Press `P`
- **Get Radioactive Bomb**: Press `G`
- **Get Cluster Bomb**: Press `Ctrl`
- **Add Tank**: Press `T`
- **Extra Life**: Press `H`

### 4. Visual Details
- **Animations**: Bomb explosions, airplane catching fire, and target destruction.
- **Data Saving**: Save user data and high scores.
- **Sound Effects**: Sound effects for shooting, explosions, and target destruction.

## Contribution

If you are interested in contributing to this project, feel free to create new issues or submit pull requests. Any contributions and feedback are welcome and will help improve the project.

## Contact Me

If you have any questions or suggestions, you can reach out to me via [email](mailto:meschi.ma24@egmail.com) or my [GitHub profile](https://github.com/Mazm28).

---

Enjoy playing **Atomic Bomber**! 🚀
