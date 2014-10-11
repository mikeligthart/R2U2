package edu.radboud.ai.r2u2.behaviour.behaviors;

import edu.radboud.ai.r2u2.action.ActionFactory;
import edu.radboud.ai.r2u2.action.actions.AbstractAction;
import edu.radboud.ai.r2u2.util.Scenario;


/**
 * Created by Pieter Marsman on 4-6-2014.
 */
public class TestBehavior extends AbstractBehavior {

    public final static String TAG = "TestBehavior";


    public TestBehavior(ActionFactory actionFactory, Scenario scenario) {
        super(actionFactory, scenario);
    }


    @Override
    protected Object processInformation(AbstractAction currentAction) {
        return null;
    }

    @Override
    public Object getInformation() {
        return null;
    }
}
// Tested and working:
// LEDAction
// RandomWander
// ShowTextAction
// SpeakAction
// ChoiceAction
// ConfirmationAction
// ExpressEmotionAction
// ListenAction
