package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.ShowTextAction;


/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class ShowTextActionPool extends ActionPool<ShowTextAction> {

    private static ShowTextActionPool instance = null;

    private ShowTextActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized ShowTextActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new ShowTextActionPool(controller);
        return instance;
    }

    public ShowTextAction acquire(String text) {
        ShowTextAction showTextAction = acquire();
        showTextAction.setText(text);
        return showTextAction;
    }

    public ShowTextAction acquire(String[] texts) {
        ShowTextAction showTextAction = acquire();
        showTextAction.setText(texts);
        return showTextAction;
    }

    @Override
    protected ShowTextAction create() {
        return new ShowTextAction(controller);
    }
}
