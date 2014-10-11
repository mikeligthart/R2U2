package edu.radboud.ai.r2u2.behaviour.behaviors;

import android.util.Log;
import edu.radboud.ai.r2u2.action.ActionFactory;
import edu.radboud.ai.r2u2.action.actions.AbstractAction;
import edu.radboud.ai.r2u2.action.actions.ReadTextAction;
import edu.radboud.ai.r2u2.behaviour.util.PostTweet;
import edu.radboud.ai.r2u2.behaviour.util.SpeechRepertoire;
import edu.radboud.ai.r2u2.util.Scenario;
import twitter4j.TwitterException;

import java.io.IOException;

/**
 * Created by Guido on 02-07-14.
 */
public class CountNrPeopleEvaluateBehavior extends AbstractBehavior {

    public static final String TAG = "CountNrPeopleBehavior";
    private String askUserReady;
    private String askToCount;
    private String understand;
    private String ending;
    private String AskUserSucceeded;
    private String AskNrOfPeople;
    private String ConfirmNrOfPeople;
    private String ConfirmPostTweet;
    private String myTweet;
    private String myTweet1;
    private String myTweet2;
    private ReadTextAction nrOfPeople;
    private String textGreetingEnd;

    public CountNrPeopleEvaluateBehavior(ActionFactory actionFactory, Scenario scenario) {
        super(actionFactory, scenario);
        // SpeechRepertoire.randomChoice(SpeechRepertoire.textGreetingStart);
        Log.v(TAG, "Initializing CountNrPeopleBehavior");
        askUserReady = SpeechRepertoire.randomChoice(SpeechRepertoire.questionAskUserReady);
        askToCount = SpeechRepertoire.randomChoice(SpeechRepertoire.textAskToCount);
        understand = SpeechRepertoire.randomChoice(SpeechRepertoire.questionUnderstand);
        ending = SpeechRepertoire.randomChoice(SpeechRepertoire.textEnding);

        AskUserSucceeded = SpeechRepertoire.randomChoice(SpeechRepertoire.AskUserSucceeded);
        AskNrOfPeople = SpeechRepertoire.randomChoice(SpeechRepertoire.AskNrOfPeople);
        ConfirmNrOfPeople = SpeechRepertoire.randomChoice(SpeechRepertoire.ConfirmNrOfPeople);
        ConfirmPostTweet = SpeechRepertoire.randomChoice(SpeechRepertoire.ConfirmPostTweet);
        textGreetingEnd = SpeechRepertoire.randomChoice(SpeechRepertoire.textGreetingEnd);
        myTweet1 = SpeechRepertoire.randomChoice(SpeechRepertoire.myTweet1);
        myTweet2 = SpeechRepertoire.randomChoice(SpeechRepertoire.myTweet2);

        // ask if user succeeded with the assignment
        if (scenario.isCanTalk()) {
            actions.add(actionFactory.getSpeakAction(AskUserSucceeded));
        }
        // not used now
        actions.add(actionFactory.getConfirmationAction(AskUserSucceeded));

        // ask nr of people
        if (scenario.isCanTalk()) {
            actions.add(actionFactory.getSpeakAction(AskNrOfPeople));
        }
        nrOfPeople = actionFactory.getReadTextAction(AskNrOfPeople);
        actions.add(nrOfPeople);

        // confirm nr of people
        if (scenario.isCanTalk()) {
            Log.i(TAG, ConfirmNrOfPeople);
            actions.add(actionFactory.getShowTextAction(ConfirmNrOfPeople));
            actions.add(actionFactory.getSpeakAction(ConfirmNrOfPeople));
        } else {
            actions.add(actionFactory.getShowTextAction(ConfirmNrOfPeople));
            actions.add(actionFactory.getSleepAction(2500)); //this should be in ShowTextAction
        }

        if (scenario.isCanTalk()) {
//            ConfirmPostTweet += myTweet; //just for now, since it is not known
            actions.add(actionFactory.getShowTextAction(ConfirmPostTweet));
            actions.add(actionFactory.getSpeakAction(ConfirmPostTweet));
        } else {
            actions.add(actionFactory.getShowTextAction(ConfirmPostTweet));
            actions.add(actionFactory.getSleepAction(2500)); //this should be in ShowTextAction
        }

        if (scenario.isCanTalk()) {
            actions.add(actionFactory.getShowTextAction(ending));
            actions.add(actionFactory.getSpeakAction(ending));
        } else {
            actions.add(actionFactory.getShowTextAction(ending));
            actions.add(actionFactory.getSleepAction(2500)); //this should be in ShowTextAction
        }
    }



    public void evaluateAssignment() {

    }

    @Override
    protected Object processInformation(AbstractAction currentAction) {
        if(currentAction == nrOfPeople) {
            String people = nrOfPeople.getInformation().toString();
            myTweet = myTweet1 + people + myTweet2;
            postATweet();
        }
        return null;
    }

    public String getMyTweet(){ return myTweet;}

    private void postATweet() {
        PostTweet postMyTweet;
        try {
            postMyTweet = new PostTweet();
            postMyTweet.postTweet(myTweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object getInformation() {
        return null;
    }
}
