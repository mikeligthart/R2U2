package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.SpeakAction;


/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class SpeakActionPool extends ActionPool<SpeakAction> {

    private static SpeakActionPool instance = null;

    private SpeakActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized SpeakActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new SpeakActionPool(controller);
        return instance;
    }

    public SpeakAction acquire(String text) {
        SpeakAction speakAction = acquire();
        speakAction.setText(text);
        return speakAction;
    }

    public SpeakAction acquire(String[] texts) {
        SpeakAction speakAction = acquire();
        speakAction.setText(texts);
        return speakAction;
    }

    @Override
    protected SpeakAction create() {
        return new SpeakAction(controller);
    }
}
