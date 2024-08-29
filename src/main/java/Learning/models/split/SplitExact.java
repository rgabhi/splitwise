package Learning.models.split;

import Learning.models.User;

public class SplitExact extends Split{

    public SplitExact(User user, Double amount){
        super(user);
        this.amount = amount;
    }

}
