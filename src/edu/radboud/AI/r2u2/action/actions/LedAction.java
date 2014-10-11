package edu.radboud.ai.r2u2.action.actions;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.util.LedColor;

import static com.wowwee.robome.RoboMeCommands.RobotCommand.*;

/**
 * @author Mike Ligthart
 * Action class that sends a RoboMe command to the robot to change its current led color into the specific LedColor.
 */
public class LedAction extends AbstractAction {

    private final static String TAG = "LedAction";
    private LedColor color;

    public LedAction(R2U2Controller controller) {
        super(controller);
    }

    /**
     * This implementation of doActions resets the current led color and changes it to the specified color.
     * @param information (last minute information can change the current color into a new LedColor.)
     */
    @Override
    public void doActions(Object information) {
        if (information != null && information instanceof LedColor) {
            color = (LedColor) information;
        }
        Log.d(TAG, "doActions() is called");
        controller.sendCommand(kRobot_ResetMood);
        controller.sendCommand(kRobot_HeartBeatOff);
        if (color != null) {
            switch (color) {
                case BLUE:
                    controller.sendCommand(kRobot_RGBHeartBlue);
                    break;
                case CYAN:
                    controller.sendCommand(kRobot_RGBHeartCyan);
                    break;
                case GREEN:
                    controller.sendCommand(kRobot_RGBHeartGreen);
                    break;
                case ORANGE:
                    controller.sendCommand(kRobot_RGBHeartOrange);
                    break;
                case RED:
                    controller.sendCommand(kRobot_RGBHeartRed);
                    break;
                case WHITE:
                    controller.sendCommand(kRobot_RGBHeartWhite);
                    break;
                case YELLOW:
                    controller.sendCommand(kRobot_RGBHeartYellow);
                    break;
                default: //OFF
                    break;
            }
        }
        //Notifies the observers it has changed the led color.
        setChanged();
        notifyObservers();
    }

    @Override
    public Object getInformation() {
        return null;
    }

    public void setColor(LedColor color) {
        this.color = color;
    }
}
