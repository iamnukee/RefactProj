package LaneViewState;

import javax.swing.*;

/**
 * Created by ormarr on 5/6/16.
 */
public class OpenFrameLaber implements LabelingState {
    @Override
    public void labelScore(JLabel[][] scoreLabel, JLabel[][] ballLabel, int[] cumulScore, int k, int i) {
        ballLabel[k][i].setText("" + cumulScore[i]);
    }
}
