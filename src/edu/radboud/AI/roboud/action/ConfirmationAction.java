package edu.radboud.ai.roboud.action;

import android.app.Activity;
import android.content.Intent;
import edu.radboud.ai.roboud.RoboudController;
import edu.radboud.ai.roboud.util.ActivityResultProcessor;

import java.util.Observer;

/**
 * Created by Pieter Marsman on 9-6-2014.
 */
public class ConfirmationAction extends AbstractAction implements ActivityResultProcessor {

    public static final String TAG = "ConfirmationAction";
    public static final int REQUEST_CODE = 12;
    public static final String DATA_NAME = "Question";

    private String question;
    private boolean result;

    public ConfirmationAction(RoboudController controller, String question) {
        super(controller);
        this.question = question;
    }

    @Override
    public void processData(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            result = data.getBooleanExtra(ChoiceActionActivity.RETURN_NAME, false);
            setChanged();
            notifyObservers();
        } else {
            // TODO what todo if the activity stopped without letting the user choose
        }
    }

    @Override
    public void doActions(Observer abstractBehavior) {
        addObserver(abstractBehavior);
        Intent i = new Intent(controller, ConfirmationActionActivity.class);
        i.putExtra(DATA_NAME, question);
        controller.startNewActivityForResult(i, REQUEST_CODE, this);
    }

    public boolean getResult() {
        return result;
    }
}
