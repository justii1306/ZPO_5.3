public class Word {
    private final String word;
    private final int day;

    public Word(String word, int day) {
        this.word = word;
        this.day = day;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Word)) {
            return false;
        }
        Word word = (Word)o;
        return word.word == this.word;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + word.hashCode();
        return result;
    }

    public String getWord() {
        return word;
    }

    public int getDay() {
        return day;
    }
}
