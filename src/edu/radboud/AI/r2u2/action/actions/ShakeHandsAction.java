package edu.radboud.ai.r2u2.action.actions;

import edu.radboud.ai.r2u2.R2U2Model;
import edu.radboud.ai.r2u2.R2U2Controller;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by mikel_000 on 3-7-2014.
 */
public class ShakeHandsAction extends AbstractAction implements Observer {


    private final static String TAG = "ShakeHandsAction";
    private R2U2Model model;
    private boolean succes;

    public ShakeHandsAction(R2U2Controller controller) {
        super(controller);
        model = controller.getModel();
        succes = false;
    }

    @Override
    public void doActions(Object information) {
        model.addObserver(this);
    }

    @Override
    public Object getInformation() {
        return succes;
    }

    public boolean getSucces() {
        return succes;
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof R2U2Model){
            if (model.getRobomeHandshakeStatus() >= 0){
                succes = true;
                setChanged();
                notifyObservers();
            }
        }
    }
}
