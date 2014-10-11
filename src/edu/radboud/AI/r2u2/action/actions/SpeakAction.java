package edu.radboud.ai.r2u2.action.actions;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.behaviour.util.SpeechRepertoire;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Pieter Marsman on 27-5-2014.
 */
public class SpeakAction extends AbstractAction implements Observer {


    private final static String TAG = "SpeakAction";
    private String text;

    public SpeakAction(R2U2Controller controller) {
        super(controller);
    }

    @Override
    public void doActions(Object information) {
        if (information != null) {
            if (information instanceof String) {
                text = (String) information;
            } else if (information instanceof String[]) {
                text = SpeechRepertoire.randomChoice((String[]) information);
            }
        }
        if (text == null) {
            throw new NullPointerException("text cannot be null");
        }
        Log.i(TAG, "Going to speak: " + text);
        controller.speakText(this, text);
    }

    @Override
    public Object getInformation() {
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String[] texts) {
        this.text = SpeechRepertoire.randomChoice(texts);
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update(Observable observable, Object data) {
        // Done with speaking
        setChanged();
        notifyObservers();
    }
}
