package Learning.services;

import Learning.models.User;
import Learning.models.expense.*;
import Learning.models.split.Split;
import Learning.models.split.SplitPercent;

import java.util.List;

public class ExpenseServices {

    public static Expense addExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        switch (expenseType){
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = (((double)Math.round(amount*100/ totalSplits))/100.0);
                for(Split split : splits){
                    split.setAmount(splitAmount);
                }
                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new ExpenseEqual(amount, paidBy, splits, expenseMetaData);
            case EXACT:
                return new ExpenseExact(amount, paidBy, splits, expenseMetaData);
            case PERCENT:
                for(Split split : splits){
                    SplitPercent splitPercent = (SplitPercent) split;
                    split.setAmount((amount*splitPercent.getPercent())/100.0);
                }
                return new ExpensePercent(amount, paidBy, splits, expenseMetaData);
        }
        return null;
    }

}
