package bg.tu_varna.sit;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateBalance(short numberOfPaymentsMade) {
        float monthInterest = getMonthInterest();
        short numberOfPayments = getNumberOfPayments();

        double balance = principal
                * (Math.pow(1 + monthInterest, numberOfPayments) - Math.pow(1 + monthInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthInterest, numberOfPayments) - 1);

        return balance;
    }

    public double calculateMortgage() {
        float monthInterest = getMonthInterest();
        short numberOfPayments = getNumberOfPayments();

        double mortgage = principal
                * (monthInterest * Math.pow(1 + monthInterest, numberOfPayments))
                /(Math.pow(1 + monthInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private short getNumberOfPayments() {
        return (short) (years * MONTHS_IN_YEAR);
    }

    private float getMonthInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
