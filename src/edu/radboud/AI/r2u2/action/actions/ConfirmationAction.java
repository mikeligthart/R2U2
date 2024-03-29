package edu.radboud.ai.r2u2.action.actions;

import android.app.Activity;
import android.content.Intent;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.util.ConfirmationActionActivity;
import edu.radboud.ai.r2u2.util.ActivityResultProcessor;

/**
 * Created by Pieter Marsman on 9-6-2014.
 */
public class ConfirmationAction extends AbstractAction implements ActivityResultProcessor {

    public static final String TAG = "ConfirmationAction";
    public static final int REQUEST_CODE = 12;
    public static final String DATA_NAME = "Question";

    private String question;
    private boolean result;

    public ConfirmationAction(R2U2Controller controller) {
        super(controller);
    }

    @Override
    public void processData(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            result = data.getBooleanExtra(ConfirmationActionActivity.RETURN_NAME, false);
            setChanged();
            notifyObservers();
        } else {
            // TODO what todo if the activity stopped without letting the user choose
        }
    }

    @Override
    public void doActions(Object information) {
        boolean doAction = true;
        if (information != null) {
            if (information instanceof Boolean){
                doAction = (Boolean) information;
            }
            if (information instanceof String) {
                question = (String) information;
            }
        }
        if (question == null) {
            throw new NullPointerException("Question cannot be null");
        }
        if (doAction) {
            Intent i = new Intent(controller, ConfirmationActionActivity.class);
            i.putExtra(ConfirmationActionActivity.EXTRAS_TEXT, question);
            controller.startNewActivityForResult(i, REQUEST_CODE, this);
        }
        else{
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public Object getInformation() {
        return result;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getResult() {
        return result;
    }

    public String getQuestion(){
        return question;
    }
}
