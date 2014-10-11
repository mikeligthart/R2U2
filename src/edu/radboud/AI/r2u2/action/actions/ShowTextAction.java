package edu.radboud.ai.r2u2.action.actions;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.behaviour.util.SpeechRepertoire;

/**
 * Created by Pieter Marsman on 27-5-2014.
 */
public class ShowTextAction extends AbstractAction {

    private String text;

    public ShowTextAction(R2U2Controller controller) {
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
        controller.showText(text);
        setChanged();
        notifyObservers();
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
}
