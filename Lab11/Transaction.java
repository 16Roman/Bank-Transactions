public class Transaction {
    public String description;
    public int amount;

    public Transaction(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }
}
