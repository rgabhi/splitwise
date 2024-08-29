package Learning.models.expense;

import Learning.models.User;
import Learning.models.split.Split;
import Learning.models.split.SplitExact;

import java.util.List;

public class ExpenseExact extends Expense{

    public ExpenseExact(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData){
        super(amount, paidBy, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        double totalAmount = this.getAmount();
        double splitAmount = 0;
        for(Split split : getSplits()){
            if(!(split instanceof SplitExact)){
                return false;
            }
            splitAmount += split.getAmount();
        }
        if(splitAmount != totalAmount) return false;
        return true;
    }
}
