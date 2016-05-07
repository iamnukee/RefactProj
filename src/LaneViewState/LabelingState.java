package LaneViewState;

import javax.swing.*;

/**
 * Created by ormarr on 5/6/16.
 */
public interface LabelingState {

    void labelScore(JLabel[][] scoreLabel, JLabel[][] ballLabel, int[] cumulScore, int k, int i);
}
