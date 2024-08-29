package Learning.models.expense;

import Learning.models.User;
import Learning.models.split.Split;
import Learning.models.split.SplitPercent;

import java.util.List;

public class ExpensePercent extends Expense {
    public ExpensePercent(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(amount, paidBy, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {

        double totalPercent = 100;
        double splitPercent = 0;
        for(Split split : this.getSplits()) {
            if(!(split instanceof SplitPercent)){
                return false;
            }
            splitPercent = ((SplitPercent) split).getPercent();
        }
        if(splitPercent != totalPercent)return false;
        return true;
    }
}
