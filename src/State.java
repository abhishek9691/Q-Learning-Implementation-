/**
 * Created by abhi on 11/5/2014.
 */
public class State {
    private int row;
    private int col;
    //0 = up, 1 = down, 2 = left, 3 = right for the actions
    private double qValues[];
    private int reward;

    public State(int row, int col, double qValue, int reward) {
        this.row = row;
        this.col = col;
        qValues = new double[4];
        for(int i = 0; i < 4; i++){
            this.qValues[i] = qValue;
        }
        this.reward = reward;
    }

    public double getQ(int action){
        return qValues[action];
    }

    public void setQ(int action, double val){
        qValues[action] = val;
    }

    public double getMaxQ(){
        double max = qValues[0];
        for(int i = 0; i < 4; i++){
            if(qValues[i] > max){
                max = qValues[i];
            }
        }
        return max;
    }

    public int getMaxAction(){
        if(this.row == 0 && this.col == 0){
            int action;
            if(qValues[3] > qValues[1]){
                action = 3;
            } else {
                action = 1;
            }
            return action;
        } else if(this.row == 0 && this.col > 0){
            double max = qValues[1];
            int action = 1;
            for(int i = 1; i < 4; i++){
                if(qValues[i] > max){
                    max = qValues[i];
                    action = i;
                }
            }
            return action;
        } else if(this.col == 0 && this.row > 0){
            double max = qValues[0];
            int action = 0;
            for(int i = 0; i < 4; i++){
                if(i == 2){
                    continue;
                }
                if(qValues[i] > max){
                    max = qValues[i];
                    action = i;
                }
            }
            return action;
        } else {
            double max = qValues[0];
            int action = 0;
            for(int i = 0; i < 4; i++){
                if(qValues[i] > max){
                    max = qValues[i];
                    action = i;
                }
            }
            return action;
        }
        //System.out.println(action);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double[] getqValues() {
        return qValues;
    }

    public void setqValue(double[] qValue) {
        this.qValues = qValue;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String toString(){
        String toRet = "";
        for(int i = 0; i < 4; i++){
            toRet += qValues[i] +", ";
        }
        return toRet+ "" +reward;
    }
}
