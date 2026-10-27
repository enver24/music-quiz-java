# Music Quiz Project 🎵

A simple quiz game written in Java using **Greenfoot**.  
Built for the Programming Fundamentals unit of my Diploma of ICT at Western Sydney University.  

## Features
- Two game modes:
  1. **Guess Count Game** – user guesses how many song/artist combos are incorrect.
  2. **Song Quiz Game** – user answers true/false for each combo.
- Uses **arrays, loops, methods, and input validation**.
- Console report cards with scoring and percentage.

## Files
All files are in the `MusicQuizProject` folder:
- `MusicQuizActor.java` – main game logic (song data, game modes, input handling and scoring)
- `MyWorld.stride` / `MyWorld.java` – the Greenfoot world the quiz runs in
- `project.greenfoot` – the Greenfoot project file

## How to Run
1. Download or clone this repository and install [Greenfoot](https://www.greenfoot.org/download).
2. In Greenfoot, choose **Scenario → Open** and select the `MusicQuizProject` folder.
3. Click **Compile**, then right-click `MusicQuizActor`, create a new one and place it in the world.
4. Click **Act** to play the game. Results are printed to the Greenfoot terminal.

## Example Output
Song Quiz mode with 3 songs, as printed to the Greenfoot console:

```
=== Song Quiz Game ===
=== Quiz Report ===
Score: 2/3 (66.67%)
Blinding Lights - The Weeknd | Correct: true | You: true
Someone Like You - Sam Smith | Correct: false | You: true
Poker Face - Lady Gaga | Correct: true | You: true
```
