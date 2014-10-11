package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.ConfirmationAction;


/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class ConfirmationActionPool extends ActionPool<ConfirmationAction> {

    private static ConfirmationActionPool instance = null;

    private ConfirmationActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized ConfirmationActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new ConfirmationActionPool(controller);
        return instance;
    }

    public ConfirmationAction acquire(String question) {
        ConfirmationAction confirmationAction = acquire();
        confirmationAction.setQuestion(question);
        return confirmationAction;
    }

    @Override
    protected ConfirmationAction create() {
        return new ConfirmationAction(controller);
    }
}
