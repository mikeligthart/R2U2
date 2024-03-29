package edu.radboud.ai.r2u2.module.behaviorModules;

import android.util.Log;
import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.behaviour.BehaviorFactory;
import edu.radboud.ai.r2u2.behaviour.behaviors.AbstractBehavior;
import edu.radboud.ai.r2u2.module.Module;
import edu.radboud.ai.r2u2.util.Scenario;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Mike Ligthart on 28-6-2014.
 */
public abstract class AbstractBehaviorModule extends Observable implements Observer, Module, Runnable {

    public static final String TAG = "AbstractBehaviorModule";
    public static int UPDATE_INTERVAL = 500;

    protected BehaviorFactory behaviorFactory;
    protected AbstractBehavior currentBehavior;
    protected boolean behaviorReady;
    protected R2U2Controller controller;
    private boolean running;
    private Thread moduleThread;

    public AbstractBehaviorModule(R2U2Controller controller, Scenario scenario) {
        this.controller = controller;
        running = false;
        moduleThread = new Thread(this);
        behaviorFactory = BehaviorFactory.getInstance(scenario, controller);
    }

    protected abstract AbstractBehavior firstBehavior();

    public void startRunning() {
        if (!running) {
            running = true;
            currentBehavior = firstBehavior();

            behaviorReady = true;
            if (moduleThread.getState() == Thread.State.NEW) {
                moduleThread.start();
            } else {
                moduleThread = new Thread(this);
                moduleThread.start();
            }
        } else {
            Log.w(TAG, "Module is already running");
        }

    }

    public void stopRunning() {
        stopBehavior();
        running = false;
        //TODO save information??.
    }

    protected abstract void stopBehavior();

    public boolean isRunning() {
        return running;
    }

    protected void finished() {
        running = false;
        setChanged();
        notifyObservers();
    }

    @Override
    public void run() {
        while (running) {
            if (behaviorReady && moduleThread != null) {
                behaviorReady = false;
                Log.d(TAG, "executing behavior " + currentBehavior.getClass().getSimpleName());
                currentBehavior.executeBehaviour();
            } else {
                try {
                    Thread.sleep(UPDATE_INTERVAL);
                } catch (InterruptedException e) {
                    Log.e(TAG, "InterruptedException in module is thrown", e);
                }
            }
        }
    }
}
