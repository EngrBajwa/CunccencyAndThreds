public class ATM_Accounts {
    public static void main(String argv[]) {

        SavingsAccount savings = new SavingsAccount(4321,531);

        ATM huddsBranch = new ATM(savings);
        ATM leedsBranch = new ATM(savings);

        huddsBranch.start();
        leedsBranch.start();

    }  // method main
}
class SavingsAccount {

    private int accountNumber;
    private int balance;

    public SavingsAccount(int accountNum, int initialDeposit) {
        accountNumber = accountNum;
        balance = initialDeposit;
    } // constructor SavingsAccount

    // only one thread at a time can execute a synchronised method
    public synchronized  boolean withdrawal(int amount) {

        boolean result = false;

        System.out.println ("Withdrawal from account " + accountNumber );
        System.out.println("Amount : " + amount);

        try {
            Thread.sleep(200);
        }
        catch(Exception e) {}

        if (amount <= balance) {
            balance -= amount;
            result = true ;
            System.out.println("New balance : " + balance);
        }
        else
            System.out.println("Insufficient funds");

        System.out.println();
        return result;
    }  // method withdrawal
    public synchronized void deposit(int DepositAmount){
        balance+=DepositAmount;
        System.out.println("Amount has been deposit: " + DepositAmount);
        System.out.println("Now total balance is: " + balance);
        System.out.println();
    }  // deposit method
}  // class SavingsAccount


class ATM extends Thread {

    public ATM(SavingsAccount savings) {
        account = savings;
    }  // constructor ATM

    public void run() {
        account.withdrawal(300);
        account.deposit(200);
    } // method run

    SavingsAccount account;

} // class ATM