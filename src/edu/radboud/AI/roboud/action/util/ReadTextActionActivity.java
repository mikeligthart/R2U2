package edu.radboud.ai.roboud.action.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.radboud.ai.roboud.R;
import edu.radboud.ai.roboud.RoboudController;

/**
 * Created by Pieter Marsman on 26-6-2014.
 */
public class ReadTextActionActivity extends Activity implements View.OnClickListener {

    public static final String RETURN_NAME = "ReadText";
    private static final String TAG = "ReadTextActionActivity";

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.face_read_text);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textView.setText(extras.getString(RoboudController.EXTRAS_TEXT));
        } else {
            Log.e(TAG, "Activity was created without needed extra information. This should never happen.");
        }

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = editText.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(RETURN_NAME, text);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}