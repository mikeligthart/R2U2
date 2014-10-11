package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.HeadAction;
import edu.radboud.ai.r2u2.action.util.HeadDirection;

/**
 * Created by Guido on 02-07-14.
 */
public class HeadActionPool extends ActionPool<HeadAction> {

    private static HeadActionPool instance = null;

    private HeadActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized HeadActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new HeadActionPool(controller);
        return instance;
    }

    public HeadAction acquire(HeadDirection headDirection) {
        HeadAction headAction = acquire();
        headAction.setHeadDirection(headDirection);
        return headAction;
    }

    @Override
    protected HeadAction create() {
        return new HeadAction(controller);
    }
}


