/**
 * Created by abhi on 11/5/2014.
 */
public class Agent {
    private int row = 0;
    private int col = 0;

    public Agent(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Agent(Agent agent) {
        this.row = agent.getRow();
        this.col = agent.getCol();
    }

    public void moveAgent(int action){
        //0 = up, 1 = down, 2 = left, 3 = right for the actions
        if(action == 0){
            this.moveUp();
        } else if(action == 1){
            this.moveDown();
        } else if(action == 2){
            this.moveLeft();
        } else if(action == 3){
            this.moveRight();
        }
    }

    public void moveLeft(){
        if(col > 0){
            col--;
        }
    }
    public void moveRight(){
        if(col < (QLearn.size -1)){
            col++;
        }
    }
    public void moveUp(){
        if(row > 0){
            row--;
        }
    }
    public void moveDown(){
        if(row < (QLearn.size - 1)){
            row++;
        }
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

    public String toString(){
        return row+", "+col;
    }
}
