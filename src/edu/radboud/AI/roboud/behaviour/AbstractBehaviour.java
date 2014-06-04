package edu.radboud.ai.roboud.behaviour;

import edu.radboud.ai.roboud.RoboudController;
import edu.radboud.ai.roboud.scenario.Scenario;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Pieter Marsman on 2-6-2014.
 */
public abstract class AbstractBehaviour extends Observable implements Behaviour, Observer {

    protected List<BehaviourBlock> blocks;
    protected RoboudController controller;
    private int executionIndex;
    private Scenario scenario;

    public List<BehaviourBlock> getBlocks() {
        return blocks;
    }

    public AbstractBehaviour(RoboudController controller) {
        this.controller = controller;
        blocks = new LinkedList<BehaviourBlock>();
        executionIndex = -1;
    }

    /**
     * Execute the BehaviourBlock one by one, starting a new block if the previous block has ended
     *
     * @param scenario
     */
    public void executeBehaviour(Scenario scenario) {
        this.scenario = scenario;
        executeStep();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof BehaviourBlock)
            executeStep();
    }

    private void executeStep() {
        if (executionIndex < blocks.size()) {
            executionIndex++;
            blocks.get(executionIndex).doActions(scenario, this);
        } else {
            setChanged();
            notifyObservers();
        }
    }
}