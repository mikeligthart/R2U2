package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.SleepAction;


/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class SleepActionPool extends ActionPool<SleepAction> {

    private static SleepActionPool instance = null;

    private SleepActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized SleepActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new SleepActionPool(controller);
        return instance;
    }

    public SleepAction acquire(long time) {
        SleepAction sleepAction = acquire();
        sleepAction.setTime(time);
        return sleepAction;
    }

    @Override
    protected SleepAction create() {
        return new SleepAction(controller);
    }
}
