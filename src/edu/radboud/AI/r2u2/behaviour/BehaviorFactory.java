package edu.radboud.ai.r2u2.behaviour;

import edu.radboud.ai.r2u2.R2U2Controller;
import edu.radboud.ai.r2u2.action.ActionFactory;
import edu.radboud.ai.r2u2.behaviour.behaviors.*;
import edu.radboud.ai.r2u2.util.Scenario;

/**
 * Created by Pieter Marsman on 19-6-2014.
 */
public class BehaviorFactory {


    private final static String TAG = "BehaviorFactory";
    private static BehaviorFactory instance = null;
    private ActionFactory actionFactory;
    private Scenario scenario;
    private R2U2Controller controller;

    private BehaviorFactory(Scenario scenario, R2U2Controller controller) {
        this.scenario = scenario;
        this.controller = controller;
        actionFactory = ActionFactory.getInstance(controller);
    }

    public synchronized static BehaviorFactory getInstance(Scenario scenario, R2U2Controller controller) {
        if (instance == null || !instance.equals(scenario))
            instance = new BehaviorFactory(scenario, controller);
        return instance;
    }

    public TestBehavior getTestBehavior() {
        return new TestBehavior(actionFactory, scenario);
    }

    public IntroduceBehavior getIntroduceBehavior() {
        return new IntroduceBehavior(actionFactory, scenario, controller.getModel());
    }

    public AreWeFamiliarBehavior getAreWeFamiliarBehavior() {
        return new AreWeFamiliarBehavior(actionFactory, scenario);
    }

    public CountNrPeopleEvaluateBehavior getCountNrPeopleEvaluateBehavior() {
        return new CountNrPeopleEvaluateBehavior(actionFactory, scenario);
    }

    public CountNrPeopleAssignBehavior getCountNrPeopleAssignBehavior() {
        return new CountNrPeopleAssignBehavior(actionFactory, scenario);
    }

    public SelectExistingUserBehavior getExistingUserBehavior(R2U2Controller controller) {
        // TODO is it "good" to use the controller like this?
        return new SelectExistingUserBehavior(actionFactory, scenario, controller);
    }

    public SettingsBehavior getSettingsBehavior() {
        return new SettingsBehavior(actionFactory, scenario);
    }

    public TurnMeOffBehavior getTurnMeOffBehavior() {
        return new TurnMeOffBehavior(actionFactory, scenario);
    }

    public WanderBehavior getRandomWanderBehavior() {
        return new WanderBehavior(actionFactory, scenario);
    }
}
