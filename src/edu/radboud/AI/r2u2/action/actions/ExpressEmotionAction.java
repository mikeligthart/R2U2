package edu.radboud.ai.r2u2.action.actions;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.util.FaceExpression;

/**
 * Created by Pieter Marsman on 24-6-2014.
 */
public class ExpressEmotionAction extends AbstractAction {

    private static final String TAG = "ExpressEmotionAction";
    FaceExpression expression;

    public ExpressEmotionAction(R2U2Controller controller) {
        super(controller);
    }

    @Override
    public void doActions(Object information) {
        controller.displayFaceExpression(expression);
        setChanged();
        notifyObservers();
    }

    @Override
    public Object getInformation() {
        return null;
    }

    public void setExpression(FaceExpression expression) {
        this.expression = expression;
    }
}
