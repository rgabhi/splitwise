package Learning;

import Learning.models.User;
import Learning.models.expense.ExpenseType;
import Learning.models.split.Split;
import Learning.models.split.SplitEqual;
import Learning.models.split.SplitExact;
import Learning.models.split.SplitPercent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);
         while (true) {
             String command = scanner.nextLine();
             String[] commands = command.split(" ");
             switch (commands[0]){
                 case "ADD":
                     User user = new User(commands[1], commands[2], commands[3], commands[4]);
                     expenseManager.addUser(user);
                     break;
                 case "SHOW" :
                     if(commands.length == 1){
                         expenseManager.showBalanceSheetAll();
                     }
                     else{
                         expenseManager.showBalanceSheet(commands[1]);
                     }
                     break;
                 case "EXPENSE":
                    String paidBy = commands[1];
                    double amount = Double.parseDouble(commands[2]);
                    int numberOfSplits = Integer.parseInt(commands[3]);
                    List<Split> splits = new ArrayList<>();
                    String expenseType = commands[4 + numberOfSplits];
                    switch(expenseType){
                        case ("EXACT"):
                            for(int i =0; i < numberOfSplits; i++){
                                splits.add(new SplitExact(expenseManager.userMap.get(commands[4+i]), Double.parseDouble(commands[5+numberOfSplits+i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits,null);
                            break;
                        case ("EQUAL"):
                            for(int i =0; i < numberOfSplits; i++){
                                splits.add(new SplitEqual(expenseManager.userMap.get(commands[4+i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits,null);
                            break;
                        case ("PERCENT"):
                            for(int i =0; i < numberOfSplits; i++){
                                splits.add(new SplitPercent(expenseManager.userMap.get(commands[4+i]), Double.parseDouble(commands[5+numberOfSplits+i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits,null);
                            break;
                    }
             }
         }
    }
}
