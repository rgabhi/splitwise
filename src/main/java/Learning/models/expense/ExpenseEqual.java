package Learning.models.expense;

import Learning.models.User;
import Learning.models.split.Split;
import Learning.models.split.SplitEqual;

import java.util.List;

public class ExpenseEqual  extends  Expense{
    public ExpenseEqual(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(amount, paidBy, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        for(Split split : this.getSplits()) {
            if (!(split instanceof SplitEqual)) return false;
        }
        return true;
    }

}
