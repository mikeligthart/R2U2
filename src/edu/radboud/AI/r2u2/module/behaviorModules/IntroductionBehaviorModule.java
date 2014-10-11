package edu.radboud.ai.r2u2.module.behaviorModules;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Model;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.behaviour.behaviors.*;
import edu.radboud.ai.r2u2.module.util.IntroductionBehaviorPhase;
import edu.radboud.ai.r2u2.util.Scenario;

import java.util.Observable;

/**
 * Created by mikel_000 on 28-6-2014.
 */
public class IntroductionBehaviorModule extends AbstractBehaviorModule {

    public static final String TAG = "IntroductionBehaviorModule";
    private static IntroductionBehaviorModule ourInstance = null;

    private IntroductionBehaviorModule(R2U2Controller controller, Scenario scenario) {
        super(controller, scenario);
    }

    public synchronized static IntroductionBehaviorModule getInstance(R2U2Controller controller, Scenario scenario) {
        if (ourInstance == null)
            ourInstance = new IntroductionBehaviorModule(controller, scenario);
        return ourInstance;
    }

    @Override
    protected AbstractBehavior firstBehavior() {
        R2U2Model model = controller.getModel();
        model.setIntroductionBehaviorPhase(IntroductionBehaviorPhase.KNOWNUSER);
        AreWeFamiliarBehavior firstBehavior = behaviorFactory.getAreWeFamiliarBehavior();
        firstBehavior.addObserver(this);
        return firstBehavior;
    }

    @Override
    protected void stopBehavior() {
        if (currentBehavior != null) {
            currentBehavior.deleteObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        R2U2Model model = controller.getModel();
        Log.i(TAG, "==Introduction Module is updated== by " + observable.getClass().getSimpleName()
                + " and the phase is at " + model.getIntroductionBehaviorPhase());

        if (model.getIntroductionBehaviorPhase() == IntroductionBehaviorPhase.KNOWNUSER) {
            if (observable instanceof AreWeFamiliarBehavior) {
                AreWeFamiliarBehavior previousBehavior = (AreWeFamiliarBehavior) observable;
                previousBehavior.deleteObserver(this);
                if (previousBehavior.isFamiliar()) {
                    model.setIntroductionBehaviorPhase(IntroductionBehaviorPhase.SELECTEXISTINGUSER);
                    currentBehavior = behaviorFactory.getExistingUserBehavior(controller);
                    currentBehavior.addObserver(this);
                    behaviorReady = true;
                } else {
                    model.setIntroductionBehaviorPhase(IntroductionBehaviorPhase.NEWUSER);
                    currentBehavior = behaviorFactory.getIntroduceBehavior();
                    currentBehavior.addObserver(this);
                    behaviorReady = true;
                }
            }
        }
        if (model.getIntroductionBehaviorPhase() == IntroductionBehaviorPhase.SELECTEXISTINGUSER) {
            if (observable instanceof SelectExistingUserBehavior) {
                SelectExistingUserBehavior previousBehavior = (SelectExistingUserBehavior) observable;
                previousBehavior.deleteObserver(this);
                if (previousBehavior.isSuccess()) {
                    model.setIntroductionBehaviorPhase(IntroductionBehaviorPhase.GETSETTINGS);
                    Log.i(TAG, "Phase is set to GETSETTINGS");
                    currentBehavior = behaviorFactory.getSettingsBehavior();
                }
                else{
                    model.setIntroductionBehaviorPhase(IntroductionBehaviorPhase.NEWUSER);
                    Log.i(TAG, "Phase is set to NEWUSER");
                    currentBehavior = behaviorFactory.getIntroduceBehavior();
                }
                currentBehavior.addObserver(this);
                behaviorReady = true;
            }
        }
        if (model.getIntroductionBehaviorPhase() == IntroductionBehaviorPhase.NEWUSER){
            if (observable instanceof IntroduceBehavior) {
                IntroduceBehavior previousBehavior = (IntroduceBehavior) observable;
                previousBehavior.deleteObserver(this);
                model.setIntroductionBehaviorPhase(IntroductionBehaviorPhase.GETSETTINGS);
                currentBehavior = behaviorFactory.getSettingsBehavior();
                currentBehavior.addObserver(this);
                behaviorReady = true;
            }
        }
        if (model.getIntroductionBehaviorPhase() == IntroductionBehaviorPhase.GETSETTINGS) {
            if (observable instanceof SettingsBehavior) {
                SettingsBehavior previousBehavior = (SettingsBehavior) observable;
                previousBehavior.deleteObserver(this);
                Log.i(TAG, "and we're finished");
                finished();
            }
        }
    }
}
