package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.LedAction;
import edu.radboud.ai.r2u2.action.util.LedColor;

/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class LedActionPool extends ActionPool<LedAction> {

    private static LedActionPool instance = null;

    private LedActionPool(R2U2Controller controller) {
        super(controller);
    }

    //A singleton pattern is applied here
    public static synchronized LedActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new LedActionPool(controller);
        return instance;
    }

    public LedAction acquire(LedColor color) {
        LedAction ledAction = acquire(); //retrieve LedAction
        ledAction.setColor(color); //set right color
        return ledAction; //return it
    }

    @Override
    protected LedAction create() {
        return new LedAction(controller);
    }
}
