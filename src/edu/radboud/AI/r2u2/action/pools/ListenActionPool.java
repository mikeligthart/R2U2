package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.ListenAction;


/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class ListenActionPool extends ActionPool<ListenAction> {

    private static ListenActionPool instance = null;

    private ListenActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized ListenActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new ListenActionPool(controller);
        return instance;
    }

    @Override
    protected ListenAction create() {
        return new ListenAction(controller);
    }

}
