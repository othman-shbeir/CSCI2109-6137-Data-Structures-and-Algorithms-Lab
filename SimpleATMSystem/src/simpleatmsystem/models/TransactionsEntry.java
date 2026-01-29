
package simpleatmsystem.models;

public class TransactionsEntry {
    private double amount;
    private String msg;

    public TransactionsEntry(double amount, String msg) {
        this.amount = amount;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        return sb.toString();
    }
    
    
    
}
