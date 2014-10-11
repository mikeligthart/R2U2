package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.AbstractAction;
import edu.radboud.ai.r2u2.action.actions.CombineAction;

/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class CombineActionPool extends ActionPool<CombineAction> {

    private static CombineActionPool instance = null;

    private CombineActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized CombineActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new CombineActionPool(controller);
        return instance;
    }

    public CombineAction acquire(AbstractAction a, AbstractAction b) {
        CombineAction combineAction = acquire();
        combineAction.setActions(a, b);
        return combineAction;
    }

    @Override
    protected CombineAction create() {
        return new CombineAction(controller);
    }
}
