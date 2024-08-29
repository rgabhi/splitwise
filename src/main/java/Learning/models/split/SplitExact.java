package Learning.models.split;

import Learning.models.User;

public class SplitExact extends Split{

    SplitExact(User user, Integer amount){
        super(user);
        this.amount = amount;
    }

}
