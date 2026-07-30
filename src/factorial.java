public class factorial {
    public static void main(String[] args) {
        System.out.println(getFactorial(5));
        System.out.println(recursiveFactorial(5));
    }

    static int getFactorial(int num){
        int finalAnswer = 1;
        for (int i = 0; i <= 4; i++) {
            finalAnswer = finalAnswer * (num-i);
        }
        return finalAnswer;
    }

    static int recursiveFactorial(int num){
        int finalAnswer = 1;
        if (num == 0) {
            return 1;
        } else {
            finalAnswer = finalAnswer * (num * recursiveFactorial(num-1)); 
        }

        return finalAnswer;
    }
}
