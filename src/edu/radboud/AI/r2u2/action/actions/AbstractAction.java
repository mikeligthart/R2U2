package edu.radboud.ai.r2u2.action.actions;

import edu.radboud.ai.r2u2.R2U2Controller;

import java.util.Observable;

/**
 * Created by Pieter Marsman on 2-6-2014.
 */
public abstract class AbstractAction extends Observable {

    protected R2U2Controller controller;

    public AbstractAction(R2U2Controller controller) {
        this.controller = controller;
    }

    public abstract void doActions(Object information);

    public abstract Object getInformation();

}
