package edu.radboud.ai.r2u2.behaviour.behaviors;

import edu.radboud.ai.r2u2.action.ActionFactory;
import edu.radboud.ai.r2u2.action.actions.AbstractAction;
import edu.radboud.ai.r2u2.action.util.LedColor;
import edu.radboud.ai.r2u2.util.Scenario;

/**
 * Created by mikel_000 on 29-6-2014.
 */
public class TurnMeOffBehavior extends AbstractBehavior {

    public TurnMeOffBehavior(ActionFactory actionFactory, Scenario scenario) {
        super(actionFactory, scenario);
        actions.add(actionFactory.getLedAction(LedColor.YELLOW));
        actions.add(actionFactory.getSpeakAction("To save my battery it is best to turn me off now"));
        actions.add(actionFactory.getShowTextAction("See you soon, bye!"));
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
