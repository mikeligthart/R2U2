package edu.radboud.ai.r2u2.action.actions;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.senses.AndroidMicrophone;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Pieter Marsman on 2-6-2014.
 */
public class ListenAction extends AbstractAction implements Observer {

    private static final String TAG = "ListenAction";
    private ArrayList<String> results;

    public ListenAction(R2U2Controller controller) {
        super(controller);
    }

    @Override
    public void doActions(Object information) {
        controller.listenToSpeech(this);
    }

    @Override
    public Object getInformation() {
        return results;
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d(TAG, "Update received from " + observable.getClass().toString());
        if (observable instanceof AndroidMicrophone) {
            results = (ArrayList<String>) data;
            Log.d(TAG, "Result of ListenAction is: " + results);
            setChanged();
            notifyObservers();
        }
    }
}
