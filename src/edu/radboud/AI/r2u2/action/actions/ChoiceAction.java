package edu.radboud.ai.r2u2.action.actions;

import android.app.Activity;
import android.content.Intent;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.util.ChoiceActionActivity;
import edu.radboud.ai.r2u2.util.ActivityResultProcessor;

import java.util.List;

/**
 * Presents a choice option list to the user through an activity on the screen of the connected Android phone.
 * The result selected option is returned here.
 * Created by Pieter Marsman on 4-6-2014.
 */
public class ChoiceAction extends AbstractAction implements ActivityResultProcessor {

    public static final String TAG = "ChoiceAction";
    public static final int REQUEST_CODE = 11;

    private List<String> options;
    private String question;

    private String resultString;

    public ChoiceAction(R2U2Controller controller) {
        super(controller);
    }

    /**
     * This implementation of doActions instantiates an activity in order to prompt the user on the Android phone with
     * a option choice list.
     * @param information (last minute information can change the current list of options.)
     */
    @Override
    public void doActions(Object information) {
        if (information != null && information instanceof List) {
            options = (List) information;
        }

        if (options == null) {
            throw new NullPointerException("Options not properly initialized");
        }

        //Initializing the activity.
        Intent i = new Intent(controller, ChoiceActionActivity.class);
        String[] optionsArray = new String[options.size()];
        optionsArray = options.toArray(optionsArray);
        i.putExtra(ChoiceActionActivity.EXTRAS_OPTIONS, optionsArray);
        i.putExtra(ChoiceActionActivity.EXTRAS_TEXT, question);
        controller.startNewActivityForResult(i, REQUEST_CODE, this);
    }

    @Override
    public Object getInformation() {
        return resultString;
    }

    @Override
    public void processData(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            //Retrieve the result.
            resultString = data.getStringExtra(ChoiceActionActivity.RETURN_NAME);
            //Notify the observers an option has been selected.
            setChanged();
            notifyObservers();
        } else {
            // TODO what todo if the activity stopped without letting the user choose
        }
    }

    public String getResult() {
        return resultString;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
