package edu.radboud.ai.r2u2.action.pools;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.actions.MotorAction;
import edu.radboud.ai.r2u2.action.util.RobotDirection;
import edu.radboud.ai.r2u2.action.util.RobotSpeed;


/**
 * Created by Mike Ligthart on 22-6-2014.
 */
public class MotorActionPool extends ActionPool<MotorAction> {

    private static MotorActionPool instance = null;

    private MotorActionPool(R2U2Controller controller) {
        super(controller);
    }

    public static synchronized MotorActionPool getInstance(R2U2Controller controller) {
        if (instance == null)
            instance = new MotorActionPool(controller);
        return instance;
    }

    public MotorAction acquire(RobotDirection dir, RobotSpeed speed) {
        MotorAction motorAction = acquire();
        motorAction.setDirAndSpeed(dir, speed);
        return motorAction;
    }

    @Override
    protected MotorAction create() {
        return new MotorAction(controller);
    }
}
