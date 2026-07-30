public class factorial {
    public static void main(String[] args) {
        System.out.println(getFactorial(5));
    }

    static int getFactorial(int num){
        int finalAnswer = 1;
        for (int i = 0; i <= 4; i++) {
            finalAnswer = finalAnswer * (num-i);
        }
        return finalAnswer;
    }
}
