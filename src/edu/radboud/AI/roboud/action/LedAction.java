package edu.radboud.ai.roboud.action;

import com.wowwee.robome.RoboMeCommands.RobotCommand;
import edu.radboud.AI.roboud.action.LedColor;
import edu.radboud.ai.roboud.RoboudController;
import edu.radboud.ai.roboud.action.AbstractAction;
import edu.radboud.ai.roboud.action.LedColor;
import edu.radboud.ai.roboud.scenario.Scenario;

import java.util.Observer;

import static com.wowwee.robome.RoboMeCommands.RobotCommand.*;

public class LedAction extends AbstractAction {

    @Override
    public void doActions(Scenario scenario, Observer abstractBehaviour, RoboudController roboudController) {
        addObserver(abstractBehaviour);
        roboudController.sendCommand(kRobot_ShowMoodOff);
        roboudController.sendCommand(kRobot_HeartBeatOff);
        //Some how get color
        LedColor color = LedColor.BLUE;
        switch(color){
            case BLUE:
                roboudController.sendCommand(kRobot_RGBHeartBlue);
                break;
            case CYAN:
                roboudController.sendCommand(kRobot_RGBHeartCyan);
                break;
            case GREEN:
                roboudController.sendCommand(kRobot_RGBHeartGreen);
                break;
            case ORANGE:
                roboudController.sendCommand(kRobot_RGBHeartOrange);
                break;
            case RED:
                roboudController.sendCommand(kRobot_RGBHeartRed);
                break;
            case WHITE:
                roboudController.sendCommand(kRobot_RGBHeartWhite);
                break;
            case YELLOW:
                roboudController.sendCommand(kRobot_RGBHeartYellow);
                break;
        }
        setChanged();
        notifyObservers();
    }
}
