package edu.radboud.ai.r2u2.action.actions;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Controller;

/**
 * Created by mikel_000 on 20-6-2014.
 */
public class SleepAction extends AbstractAction {

    private final static String TAG = "SleepAction";

    private long time;

    public SleepAction(R2U2Controller controller) {
        super(controller);
    }

    @Override
    public void doActions(Object information) {
        if (information != null && information instanceof Long) {
            time = (Long) information;
        }
        if (time == 0) {
            throw new NullPointerException("Time cannot be 0");
        }
        Log.d(TAG, "Going to sleep");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Log.e(TAG, "sleep failed", e);
        }
        Log.d(TAG, "Waking up and notifying");
        setChanged();
        notifyObservers();
    }

    @Override
    public Object getInformation() {
        return null;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
