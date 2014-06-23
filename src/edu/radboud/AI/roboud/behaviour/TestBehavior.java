package edu.radboud.ai.roboud.behaviour;

import edu.radboud.ai.roboud.RoboudController;
import edu.radboud.ai.roboud.action.actions.MotorAction;
import edu.radboud.ai.roboud.action.actions.SpeakAction;
import edu.radboud.ai.roboud.task.TaskFactory;

import java.util.Observer;

/**
 * Created by Pieter Marsman on 4-6-2014.
 */
public class TestBehavior extends AbstractBehavior {

    public final static String TAG = "TestBehavior";
    private MotorAction forward, backward;
    private SpeakAction speak;

    public TestBehavior(RoboudController controller, TaskFactory taskFactory, Observer observer) {
        super(controller, taskFactory, observer);
//        blocks.add(taskFactory.getDutchFlagLedTask());
//        forward = MotorActionPool.getInstance(controller).acquire(RobotDirection.FORWARD, RobotSpeed.NORMAL);
//        backward = MotorActionPool.getInstance(controller).acquire(RobotDirection.BACKWARD, RobotSpeed.NORMAL);
//        speak = SpeakActionPool.getInstance(controller).acquire(SpeechRepertoire.textIntroduceMyself);
//        blocks.add(forward);
//        blocks.add(speak);
//        blocks.add(backward);
    }

    @Override
    public void releaseActions() {
//        MotorActionPool.getInstance(controller).release(forward);
//        MotorActionPool.getInstance(controller).release(backward);
//        SpeakActionPool.getInstance(controller).release(speak);
    }
}
// Tested and working:
// LEDAction
// RandomWander
// ShowTextAction
