package edu.radboud.ai.r2u2.action.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import edu.radboud.ai.r2u2.R;

/**
 * Created by Pieter Marsman on 4-6-2014.
 */
public class ChoiceActionActivity extends Activity implements AdapterView.OnItemClickListener {

    public static final String RETURN_NAME = "Selected Item";
    private static final String TAG = "ChoiceActionActivity";
    public static final String EXTRAS_TEXT = "ExtraText";
    public static final String EXTRAS_OPTIONS = "options";

    private TextView textView;
    private ListView list;
    private String[] options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.face_choice);

        list = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            options = extras.getStringArray(EXTRAS_OPTIONS);
            textView.setText(extras.getString(EXTRAS_TEXT));
        } else {
            Log.e(TAG, "Activity was created without needed extra information. This should never happen.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, options);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = options[position];
        Intent returnIntent = new Intent();
        returnIntent.putExtra(RETURN_NAME, selectedItem);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}