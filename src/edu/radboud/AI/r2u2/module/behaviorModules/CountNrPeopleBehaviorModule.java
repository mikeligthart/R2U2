package edu.radboud.ai.r2u2.module.behaviorModules;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Model;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.behaviour.behaviors.AbstractBehavior;
import edu.radboud.ai.r2u2.behaviour.behaviors.CountNrPeopleAssignBehavior;
import edu.radboud.ai.r2u2.behaviour.behaviors.CountNrPeopleEvaluateBehavior;
import edu.radboud.ai.r2u2.module.util.CountNrPeopleBehaviorPhase;
import edu.radboud.ai.r2u2.util.Scenario;

import java.util.Observable;

import static edu.radboud.ai.r2u2.module.util.CountNrPeopleBehaviorPhase.EVALUATEASSIGNMENT;
import static edu.radboud.ai.r2u2.module.util.CountNrPeopleBehaviorPhase.GIVEASSIGNMENT;

/**
 * Created by Guido on 02-07-14.
 */
public class CountNrPeopleBehaviorModule extends AbstractBehaviorModule {
    public static final String TAG = "CountNrPeopleBehaviourModule";

    private static CountNrPeopleBehaviorModule ourInstance = null;

    private CountNrPeopleBehaviorModule(R2U2Controller controller, Scenario scenario) {
        super(controller, scenario);
    }

    public synchronized static CountNrPeopleBehaviorModule getInstance(R2U2Controller controller, Scenario scenario) {
        if (ourInstance == null)
            ourInstance = new CountNrPeopleBehaviorModule(controller, scenario);
        return ourInstance;
    }

    //TODO: provide different scenarios, further, scenarios have to be instantiated (use setter, or provide variable, Pieter?).
    @Override
    protected AbstractBehavior firstBehavior() {
        // temporary solution?
        R2U2Model model = controller.getModel();
        if (model.getCountNrPeopleBehaviorPhase() == null)
            model.setCountNrPeopleBehaviorPhase(GIVEASSIGNMENT);
        switch (model.getCountNrPeopleBehaviorPhase()) {
            case GIVEASSIGNMENT:
                CountNrPeopleAssignBehavior firstBehavior1 = behaviorFactory.getCountNrPeopleAssignBehavior();
                firstBehavior1.addObserver(this);
                return firstBehavior1;
            case EVALUATEASSIGNMENT:
                CountNrPeopleEvaluateBehavior firstBehavior2 = behaviorFactory.getCountNrPeopleEvaluateBehavior();
                firstBehavior2.addObserver(this);
                return firstBehavior2;
            default:
                // This should never happen.
                Log.e(TAG, "CountNrPeopleBehaviorPhase was not initialized but should be");
                return null;
        }

    }

    @Override
    protected void stopBehavior() {
        if (currentBehavior != null) {
            currentBehavior.deleteObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.i(TAG, "==CountNrPeople Module is updated== by " + observable.getClass().getSimpleName()
                + " and the phase is at " + controller.getModel().getCountNrPeopleBehaviorPhase());

        R2U2Model model = controller.getModel();
        if (model.getCountNrPeopleBehaviorPhase() == GIVEASSIGNMENT && observable instanceof CountNrPeopleAssignBehavior) {
            CountNrPeopleAssignBehavior previousBehavior = (CountNrPeopleAssignBehavior) observable;
            previousBehavior.deleteObserver(this);
            model.setCountNrPeopleBehaviorPhase(CountNrPeopleBehaviorPhase.EVALUATEASSIGNMENT);
            finished();
        } else if (model.getCountNrPeopleBehaviorPhase() == EVALUATEASSIGNMENT && observable instanceof CountNrPeopleEvaluateBehavior) {
            CountNrPeopleEvaluateBehavior previousBehavior = (CountNrPeopleEvaluateBehavior) observable;
            previousBehavior.deleteObserver(this);
            model.setCountNrPeopleBehaviorPhase(CountNrPeopleBehaviorPhase.GIVEASSIGNMENT);
            Log.i(TAG, "and we're finished with counting people");
            finished();
        } else
            throw new NullPointerException("CountNrPeopleBehaviorPhase not instantiated (2)");
    }

//    public CountNrPeopleBehaviorPhase getPhase() {
//        return phase;
//    }
}
