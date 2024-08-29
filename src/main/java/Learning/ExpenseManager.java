package Learning;

import Learning.models.User;
import Learning.models.expense.Expense;
import Learning.models.expense.ExpenseMetaData;
import Learning.models.expense.ExpenseType;
import Learning.models.split.Split;
import Learning.services.ExpenseServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    Map<String, User> userMap = new HashMap<>();
    Map<String, Map<String, Double>> balanceSheet = new HashMap<>();
    List<Expense> expenseList = new ArrayList<>();

    public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
            Expense expense = ExpenseServices.addExpense(expenseType, amount,userMap .get(paidBy), splits, expenseMetaData);
            expenseList.add(expense);
            for(Split split : expense.getSplits()) {
                Map<String, Double> mapBalances = balanceSheet.get(expense.getPaidBy().getId());
                String paidTo = split.getUser().getId();
                if(!mapBalances.containsKey(paidTo)){
                    mapBalances.put(paidTo, 0.0);
                }
                mapBalances.put(paidTo, mapBalances.get(paidTo) + split.getAmount());
                mapBalances = balanceSheet.get(paidTo);

                if(!mapBalances.containsKey(paidBy)){
                    mapBalances.put(paidBy, 0.0);
                }
                mapBalances.put(paidBy, mapBalances.get(paidBy) - split.getAmount());
            }
    }

    public void showBalanceSheet(String userId){
        boolean isEmpty = true;
        for(Map.Entry<String, Double> entry : balanceSheet.get(userId).entrySet()){
            isEmpty = false;
            printBalance(userId, entry.getKey(), entry.getValue());
        }
        if(isEmpty){
            System.out.println("No balance found for user " + userId);
        }
    }

    public void showBalanceSheetAll(){
        boolean isEmpty = true;
        for( Map.Entry<String, User> entry : userMap.entrySet()){
            String userId = entry.getKey();
            for(Map.Entry<String, Double> balance : balanceSheet.get(userId).entrySet()){
                isEmpty = false;
                if(balance.getValue() > 0){
                    printBalance(userId, balance.getKey(), balance.getValue());
                }
            }
            if(isEmpty){
                System.out.println("No balance found for user " + userId);
            }
        }
    }


    private void printBalance(String userId1, String userId2, double amount){
        String user1Name = userMap.get(userId1).getName();
        String user2Name = userMap.get(userId2).getName();
        if(amount > 0){
            System.out.println(user2Name + " owes " + user1Name + " : Rs. " + amount);
        }
        else if(amount < 0){
            System.out.println(user1Name + " owes " + user2Name + " : Rs. " + -amount);
        }
    }

}
