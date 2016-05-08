import LaneViewState.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Vector;

public class LaneViewContext {

    private static LaneViewContext context;
    private LaneEvent laneEvent;
    private int numBowlers;
    private int[][] cumulScore;
    private int[] scores;
    private Vector bowlers;

    public static LaneViewContext getContext(){
        if(context == null){
            context = new LaneViewContext();
        }
        return context;
    }

    public void update(LaneEvent laneEvent, int numBowlers, int[][] cumulScore, Vector bowlers){
        this.laneEvent = laneEvent;
        this.numBowlers = numBowlers;
        this.cumulScore = cumulScore;
        this.bowlers = bowlers;
    }

    public void labelFrame(JLabel[][] scoreLabel, JLabel[][] ballLabel){
        for(int k = 0; k < numBowlers; k++){
            for(int i = 0; i <= laneEvent.getFrameNum() - 1; i++){
                if(cumulScore[k][i] != 0){
                    scoreLabel[k][i].setText("" + cumulScore[k][i]);
                }
            }
            scores = ((int[]) ((HashMap) laneEvent.getScore()).get(bowlers.get(k)));
            for(int i = 0; i < 21; i ++){
                if(scores[i] != -1) {
                    LabelingState state = pickState(i);
                    state.labelScore(scoreLabel, ballLabel, scores, k, i);
                }
            }
        }

    }


    private LabelingState pickState(int ball) {
        if(scores[ball] == 10){
            //If its a Strike
            return new StrikeLabler();
        }else if (ball % 2 == 1 && (scores[ball-1] + scores[ball] == 10 )){
            //If there is a spare
            return new SpareLabler();
        }else if(scores[ball] == -2){
            //Fowl
            return new FoulLabeler();
        } else {
            //its an open frame
            return new OpenFrameLaber();
        }
    }
}
