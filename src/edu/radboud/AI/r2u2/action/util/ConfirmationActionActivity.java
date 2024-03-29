package edu.radboud.ai.r2u2.action.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.radboud.ai.r2u2.R;

/**
 * Created by Pieter Marsman on 9-6-2014.
 */
public class ConfirmationActionActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "ConfirmationActionActivity";
    public static final String RETURN_NAME = "result";
    public static final String EXTRAS_TEXT = "ExtraText";
    private TextView textView;
    private Button yesButton, noButton;

    private String question;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face_confirmation);

        textView = (TextView) findViewById(R.id.textView);
        yesButton = (Button) findViewById(R.id.confirmation_button_yes);
        noButton = (Button) findViewById(R.id.confirmation_button_no);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            question = extras.getString(EXTRAS_TEXT);
            textView.setText(question);
        } else {
            Log.e(TAG, "Activity was created without needed extra information. This should never happen.");
        }

        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean result = false;
        if (v == yesButton)
            result = true;
        else if (v == noButton)
            result = false;

        Intent returnIntent = new Intent();
        returnIntent.putExtra(RETURN_NAME, result);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
