package Learning.models.split;

import Learning.models.User;

public class SplitPercent extends Split{
    private double percent;
    public SplitPercent(User user, double percent){
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
