public class Main {

    public static void main(String[] args){
        final int n = 10;
        final int k = 3;

        LearnEnglish test = new LearnEnglish(k);
        try {
            for (int i = 0; i < n; i++)
                test.day();
        } catch (Exception e){

        }
    }
}
