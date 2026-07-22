public class JavaApplication2 {
    double SaleB;
    int numDays;
    
    public JavaApplication2(){
        SaleB = 651.50;
        numDays = 3;
    }
    
    public double computeAverage(double sum, double days){
        double ave;
        ave = sum/days;
        System.out.print("\nThe total sales for "+days+" is "+sum+".");
        return ave;
    }
    
    public int getSale(double SoldAmt1){
        double SaleC = 1148.25, AveSale, totalSale;
        int countDays;
        System.out.print("\n\nThe sales per day are "+SoldAmt1+", "+SaleB+" and "+SaleC+".");
        totalSale = SoldAmt1 + SaleB + SaleC;
        countDays = numDays;
        AveSale = computeAverage(totalSale, countDays);
        System.out.print("\nThe computed average is "+AveSale+".");
        return countDays;
    }
    
    public void displayGreeting(String pangalan){
        System.out.print("\nGood day "+pangalan+"! \nWelcome to MFG Grocery Store!");
    }
    
    public static void main(String[] args) {
        double SaleA = 1500.25;
        int count;
        String name = "Shaan Masangcap";
        JavaApplication2 ExerObj = new JavaApplication2();
        ExerObj.displayGreeting(name);
        count = ExerObj.getSale(SaleA);
        System.out.println("\nThe total number of days is "+count+".");
    }
}
