import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by abhi on 11/5/2014.
 */
public class QLearn {
    public static State[][] world;
    public static int size = 15;
    private static double discount = 0.9;//discount for traveling through the grid
    private static double learningRate= 0.2;//the learning rate
    private static int episodes = 600;//total number of episodes
    private static ArrayList<Point> data = new ArrayList<Point>();

    public static void main(String args[]){
        initializeTable();
        //implement the q-learning algorithm
        for(int i = 0; i < episodes; i++){
            Agent agent = new Agent(0, 0);
            int steps = 0;
            //iterate until the agent is in the goal state
            while((agent.getRow() != size-1) || (agent.getCol() != size-1)){
                //compute the q value, and set it
                State s = world[agent.getRow()][agent.getCol()];
                //choose an action
                int action = chooseAction(s, steps);
                //System.out.println(action);
                double currState_qVal = s.getQ(action);
                //make a copy of the agent so that we can use that to get the new state values we need
                Agent copyAgent = new Agent(agent);
                //move the copy agent first and get the new values to compute Q
                copyAgent.moveAgent(action);
                State nextOne = world[copyAgent.getRow()][copyAgent.getCol()];
                //get all componenets needed to compute the second term of the q-learning equation
                double reward = nextOne.getReward();
                double maxQ = (nextOne.getMaxQ())*(discount);
                double secondTerm = (learningRate)*(reward + maxQ - currState_qVal);
                //the resulting q value
                double finalQ = currState_qVal + secondTerm;
                //set the new Q value for the movement
                s.setQ(action, finalQ);

                //move the agent accordingly
                agent.moveAgent(action);
                steps++;
            }
            data.add(new Point(i, steps));//episodes, steps to reach end
        }
        try {
            PrintWriter writer = new PrintWriter("hw7_data_600.txt", "UTF-8");
            for(int i = 0; i < episodes; i++){
                writer.println(data.get(i).getX()+"\t"+data.get(i).getY());
            }
            writer.close();
        } catch (Exception e){e.printStackTrace();}
        //printWorld();
    }

    private static void printWorld(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.println(i+"; "+j+"; "+world[i][j]);
            }
        }
    }

    private static int chooseAction(State s, int steps){
        //0 = up, 1 = down, 2 = left, 3 = right for the actions
        //must choose based on epsilon value
        //normally pick the greedy choice
        int action = s.getMaxAction();
        //want to pick a random choice every 10% of the time
        if(steps % 10 == 0) {
            action = (int) (Math.random() * 4);
        }
        return action;
    }

    private static void initializeTable(){
        double qValue = 2;
        world = new State[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                //rewards are randomly set for all states except:
                //1. the last one (most positive)
                //2. the first one (most negative)
                int reward = -1;
                world[i][j] = new State(i, j, qValue, reward);
            }
        }
        //set greatest reward for the final state
        world[size-1][size-1].setReward(100);
        //set smallest reward for the initial state
        world[0][0].setReward(-100);
    }
}
