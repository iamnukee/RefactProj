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
        //        System.out.println("CumulScore:");
//        for(int i = 0; i < cumulScore.length; i++){
//            System.out.print(cumulScore[0][i] + ",");
//        }
//        System.out.println();
//        for(int x = 0; x < cumulScore.length; x++){
//            for(int y = 0; y < cumulScore[0].length; y++){
//                System.out.print(cumulScore[x][y] + ",");
//            }
//            System.out.println("\n");
//        }
//
//        for (int k = 0; k < numBowlers; k++) {
//            int[] scores = ((int[]) ((HashMap) laneEvent.getScore()).get(bowlers.get(k)));
//            for(int x = 0 ; x < scores.length; x++){
//                System.out.print(scores[x] + ",");
//            }
//            System.out.println("\n");
//        }
    }

    public void labelFrame(JLabel[][] scoreLabel, JLabel[][] ballLabel){
        for(int k = 0; k < numBowlers; k++){
            for(int i = 0; i <= laneEvent.getFrameNum() - 1; i++){
//                LabelingState state = pickState(k,i);
//                state.labelScore(scoreLabel, ballLabel, cumulScore, k, (2*i));
//                if(i != 10)state.labelScore(scoreLabel, ballLabel, cumulScore, k, ((2*i)+1));
                if(cumulScore[k][i] != 0){
                    scoreLabel[k][i].setText("" + cumulScore[k][i]);
                }
            }
            scores = ((int[]) ((HashMap) laneEvent.getScore()).get(bowlers.get(k)));
            for(int i = 0; i < 21; i ++){
                if(scores[i] != -1) {
                    LabelingState state = pickState(i);
//                    System.out.println("Score:" + scores[i] +  "  State: " + state.toString());
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
            return new FowlLabler();
        } else {
            //its an open frame
            return new OpenFrameLaber();
        }
    }
}
