# Q-Learning-Implementation

This is an implementation of the Q-Learning reinforcement learning algorithm. This algorithm follows the function: 

Q(st, at) <- Q(st, at) + alpha * [Rt+1 + gamma*(max of Q(st+1, a) - Q(st, at))]

Where each "block" in a grid has some Q value associated with it. As a bot travels across the grid from entrance to exit, the bot learns whether each spot is ideal or if another route should be taken. Initially, all the blocks are randomly initialized, allowing the bot to kind of randomly roam around to get to the exit. This process is repeated iteratively for a certain number of rounds, or until convergence occurs such that each time the bot goes to the exit in the same number of steps through an ideal route. The learning rate provides a good idea of how long it will take, and can be adjusted. 
The program is written in Java, and contains files for each portion (learning, agent, and state) 
