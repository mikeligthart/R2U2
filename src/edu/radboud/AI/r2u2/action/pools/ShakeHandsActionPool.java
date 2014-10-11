package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.ShakeHandsAction;

/**
 * Created by mikel_000 on 3-7-2014.
 */
public class ShakeHandsActionPool extends ActionPool<ShakeHandsAction> {

    private static ShakeHandsActionPool instance = null;

    private ShakeHandsActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized ShakeHandsActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new ShakeHandsActionPool(controller);
        return instance;
    }

    @Override
    protected ShakeHandsAction create() {
        return new ShakeHandsAction(controller);
    }
}
