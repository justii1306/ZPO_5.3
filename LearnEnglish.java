import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class LearnEnglish {
    private final int wordsToLearn = 2;
    private final int wordsToForget = 2;

    private final int k;
    private int currentDay;
    //private LinkedHashMap<String, Integer> words = new LinkedHashMap<String, Integer>();
    private LinkedList<Word> words = new LinkedList<>();

    public LearnEnglish(int k) {
        this.k = k;
        this.currentDay = 1;
    }

    public void day() throws IOException{
        System.out.println("Day " + getCurrentDay());
        System.out.print("New words: ");
        for (int i = 0; i < getWordsToLearn(); i++) {
            System.out.print(learnWord() + " ");
        }
        System.out.println();
        System.out.print("Forgotten words: ");
        if (getK() < getCurrentDay()) {
            Random rand = new Random();
            for (int i = 0; i < getWordsToForget(); i++) {
                String wordToMaybeForget = chooseWordToForget();
                if (rand.nextBoolean()) {
                    Word wordToForget = new Word(wordToMaybeForget, 0);
                    System.out.print(wordToMaybeForget + " ");
                    getWords().remove(wordToForget);
                }
            }
        }
        System.out.println();
        for (int i = 0; i < getWords().size(); i++){
            System.out.print(getWords().get(i).getWord() + " : ");
        }
        System.out.println();
        System.out.println();
        this.currentDay++;
    }

    public String learnWord() throws IOException{
        String word;
        boolean isAdded = false;
        do {
            word = randomWord();

            if (!(getWords().contains(word))){
                Word learnedWord = new Word (word, getCurrentDay());
                getWords().add(learnedWord);
                isAdded = true;
            }
        } while (isAdded == false);
        return word;
    }

    public String chooseWordToForget(){
        String result = null;
        Random rand = new Random();
        boolean isSelected = false;
        do {
            int randomIndex = rand.nextInt(words.size());
            if ((getCurrentDay() - words.get(randomIndex).getDay()) >= getK()){
                result = words.get(randomIndex).getWord();
                //words.remove(randomIndex);
                isSelected = true;
            }
        } while (isSelected == false);
        return result;
    }

    private String randomWord() throws IOException{
        URL lastNames = new URL("http://szgrabowski.kis.p.lodz.pl/zpo18/1500.txt");
        String result = null;
        Random rand = new Random();
        int n = 0;

        BufferedReader in = new BufferedReader(
                new InputStreamReader(lastNames.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            ++n;
            if(rand.nextInt(n) == 0)
                result = inputLine;
        }
        in.close();
        return result;
    }

    public LinkedList<Word> getWords() {
        return words;
    }

    public int getWordsToLearn() {
        return wordsToLearn;
    }

    public int getWordsToForget() {
        return wordsToForget;
    }

    public int getK() {
        return k;
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
