import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot and MouseInfo
public class MusicQuizActor extends Actor
{
    // Parallel arrays
    String[] songs = {
        "Shape of You", "Blinding Lights", "No Tears Left to Cry", 
        "Rolling in the Deep", "Bad Guy", "Believer", 
        "Someone Like You", "Poker Face", "Thriller", "Lose Yourself"
    };
    
    String[] artists = {
        "Ed Sheeran", "The Weeknd", "Justin Timberlake", 
        "Adele", "Billie Eilish", "Imagine Dragons", 
        "Sam Smith", "Lady Gaga", "Justin Timberlake", "Eminem"
    };
    
    boolean[] isCorrect = {
        true, true, false, true, true, true, false, true, false, true
    };
    
    boolean started = false;
    
    public void act() {
        if (!started) {
            started = true;
            startGame();
        }
    }
    
    private void startGame() {
        int mode = chooseGameMode();
        int num = chooseSongCount();
        int[] chosen = pickSongs(num);
        
        if (mode == 1) {
            playGuessCountGame(chosen);
        } else {
            playSongQuizGame(chosen);
        }
        
        Greenfoot.ask("Game finished! Press OK to close.");
    }
    
    // --- Input Helpers ---
    
    private int chooseGameMode() {
        while (true) {
            String input = Greenfoot.ask("Choose game: 1 = Guess Count, 2 = Song Quiz (default 1)");
            if (input.equals("")) input = "1";
            try {
                int val = Integer.parseInt(input);
                if (val == 1 || val == 2) return val;
            } catch (Exception e) {}
            Greenfoot.ask("Invalid input. Please enter 1 or 2.");
        }
    }
    
    private int chooseSongCount() {
        while (true) {
            String input = Greenfoot.ask("How many songs? (1-10, default 3)");
            if (input.equals("")) input = "3";
            try {
                int val = Integer.parseInt(input);
                if (val >= 1 && val <= 10) return val;
            } catch (Exception e) {}
            Greenfoot.ask("Invalid. Enter a number from 1 to 10.");
        }
    }
    
    private int[] pickSongs(int num) {
        int[] result = new int[num];
        boolean[] used = new boolean[songs.length];
        int count = 0;
        while (count < num) {
            int r = Greenfoot.getRandomNumber(songs.length);
            if (!used[r]) {
                used[r] = true;
                result[count] = r;
                count++;
            }
        }
        return result;
    }
    
    // --- Game Mode 1: Guess Count ---
    
    private void playGuessCountGame(int[] list) {
        System.out.println("=== Guess Count Game ===");
        for (int i = 0; i < list.length; i++) {
            System.out.println((i+1) + ". " + songs[list[i]] + " - " + artists[list[i]]);
        }
        
        int guess = -1;
        while (true) {
            try {
                guess = Integer.parseInt(Greenfoot.ask("How many combos are incorrect?"));
                break;
            } catch (Exception e) {}
        }
        
        int actual = countIncorrect(list);
        if (guess == actual) {
            Greenfoot.ask("Correct! There are " + actual + " incorrect combos.");
        } else {
            Greenfoot.ask("Wrong. The answer is " + actual + ". Check console for details.");
            showResults(list);
        }
    }
    
    private int countIncorrect(int[] list) {
        int count = 0;
        for (int i : list) {
            if (!isCorrect[i]) count++;
        }
        return count;
    }
    
    private void showResults(int[] list) {
        System.out.println("--- Results ---");
        for (int i : list) {
            System.out.println(songs[i] + " - " + artists[i] + " [" + (isCorrect[i] ? "TRUE" : "FALSE") + "]");
        }
    }
    
    // --- Game Mode 2: Song Quiz ---
    
    private void playSongQuizGame(int[] list) {
        System.out.println("=== Song Quiz Game ===");
        boolean[] answers = new boolean[list.length];
        int score = 0;
        
        for (int i = 0; i < list.length; i++) {
            int idx = list[i];
            String ans = Greenfoot.ask("Is this correct? (true/false)\n" + songs[idx] + " - " + artists[idx]);
            answers[i] = ans.equalsIgnoreCase("true");
            if (answers[i] == isCorrect[idx]) score++;
        }
        
        double percent = (score * 100.0) / list.length;
        Greenfoot.ask("You got " + score + "/" + list.length + " correct (" + String.format("%.2f", percent) + "%). Check console.");
        
        showQuizReport(list, answers, score, percent);
    }
    
    private void showQuizReport(int[] list, boolean[] answers, int score, double percent) {
        System.out.println("=== Quiz Report ===");
        System.out.println("Score: " + score + "/" + list.length + " (" + String.format("%.2f", percent) + "%)");
        for (int i = 0; i < list.length; i++) {
            int idx = list[i];
            System.out.println(songs[idx] + " - " + artists[idx] +
                " | Correct: " + isCorrect[idx] +
                " | You: " + answers[i]);
        }
    }
}
