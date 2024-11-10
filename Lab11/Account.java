import java.util.ArrayList;

public class Account {
    public int balance;
    public ArrayList<Transaction> transactions;

    public Account(){
        balance=0;
        transactions=new ArrayList<>();
    }
    public void setBalance(int arg) throws Exception{
        if(balance<0){
            throw new Exception("Invalid Balance");
        }
    }
    public int getBalance(){
        return balance;
    }

}
