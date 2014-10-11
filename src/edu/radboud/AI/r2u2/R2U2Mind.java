package edu.radboud.ai.r2u2;

import android.util.Log;
import edu.radboud.ai.r2u2.behaviour.util.PostTweet;
import edu.radboud.ai.r2u2.module.behaviorModules.AbstractBehaviorModule;
import edu.radboud.ai.r2u2.module.behaviorModules.CountNrPeopleBehaviorModule;
import edu.radboud.ai.r2u2.module.behaviorModules.IntroductionBehaviorModule;
import edu.radboud.ai.r2u2.module.behaviorModules.TurnMeOffBehaviorModule;
import edu.radboud.ai.r2u2.module.functionModules.AbstractFunctionModule;
import edu.radboud.ai.r2u2.module.functionModules.ConnectedFunctionModule;
import edu.radboud.ai.r2u2.util.Scenario;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.*;

/**
 * Created by Pieter Marsman on 24-5-2014.
 */
public class R2U2Mind implements Observer {

    public static final String TAG = "R2U2Mind";

    private static R2U2Mind instance = null;

    private R2U2Model model;
    private R2U2Controller controller;
    private Scenario scenario;

    private List<AbstractFunctionModule> functionModules;
    private AbstractBehaviorModule behaviorModule;

    private boolean running;

    private R2U2Mind(R2U2Controller controller, Scenario scenario) {
        this.controller = controller;
        model = controller.getModel();
        this.scenario = scenario;
        running = false;

        //The first module
        behaviorModule = IntroductionBehaviorModule.getInstance(controller, scenario);
        behaviorModule.addObserver(this);

        //Add all necessary function modules
        functionModules = new LinkedList<AbstractFunctionModule>();
        functionModules.add(ConnectedFunctionModule.getInstance(controller));
    }

    public static synchronized R2U2Mind getInstance(R2U2Controller controller, Scenario scenario) {
        if (instance == null)
            instance = new R2U2Mind(controller, scenario);
        return instance;
    }

    @Override
    public void update(Observable observable, Object data) {
        controller.updateScenario();
        Log.i(TAG, "==Mind is updated== by " + observable.getClass().getSimpleName());
        Log.i(TAG, "model headPhoneConnected = " + model.isRobomeHeadsetPluggedIn());
        if (observable instanceof CountNrPeopleBehaviorModule) {
            CountNrPeopleBehaviorModule oldModule = (CountNrPeopleBehaviorModule) observable;
            Log.i(TAG, "Updated by CountNrPeopleBehaviorModule that is in phase: " + model.getCountNrPeopleBehaviorPhase());
            oldModule.deleteObserver(this);
            oldModule.stopRunning();
            behaviorModule = TurnMeOffBehaviorModule.getInstance(controller, scenario);
            behaviorModule.addObserver(this);
            behaviorModule.startRunning();

        } else if (observable instanceof IntroductionBehaviorModule) {
            IntroductionBehaviorModule oldModule = (IntroductionBehaviorModule) observable;
            oldModule.deleteObserver(this);
            oldModule.stopRunning();
            behaviorModule = CountNrPeopleBehaviorModule.getInstance(controller, scenario);
            behaviorModule.addObserver(this);
            behaviorModule.startRunning();
        } else if (observable instanceof ConnectedFunctionModule) {
            ConnectedFunctionModule connectedFunctionModule = (ConnectedFunctionModule) observable;

            if (!connectedFunctionModule.getConnected()) {
                behaviorModule.stopRunning();
                behaviorModule.deleteObserver(this);
            } else {
                if (!behaviorModule.isRunning()) {
                    Log.i(TAG, "behavior module running = " + behaviorModule.isRunning());
                    behaviorModule.addObserver(this);
                    behaviorModule.startRunning();
                }
            }
        }
    }

    private void tempPostTweet(String myTweet) {
        PostTweet postMyTweet;
        try {
            postMyTweet = new PostTweet();
            postMyTweet.postTweet(myTweet);
        } catch (
                TwitterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stopRunning() {
        running = false;
        for (Iterator<AbstractFunctionModule> it = functionModules.iterator(); it.hasNext(); ) {
            AbstractFunctionModule functionModule = it.next();
            functionModule.deleteObserver(this);
            functionModule.stopRunning();
        }
        behaviorModule.deleteObserver(this);
        behaviorModule.stopRunning();
    }

    public void startRunning() {
        Log.d(TAG, "Start running r2u2 mind");
        if (!running) {
            running = true;
            for (Iterator<AbstractFunctionModule> it = functionModules.iterator(); it.hasNext(); ) {
                AbstractFunctionModule functionModule = it.next();
                functionModule.addObserver(this);
                functionModule.startRunning();
            }
            if (model.isRobomeHeadsetPluggedIn()) {
                behaviorModule.addObserver(this);
                behaviorModule.startRunning();
            }
        } else {
            Log.w(TAG, "Already running, no need to start it again");
        }
    }

    public boolean isRunning() {
        return running;
    }

}
