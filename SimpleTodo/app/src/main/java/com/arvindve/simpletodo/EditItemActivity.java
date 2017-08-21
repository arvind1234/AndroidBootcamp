package com.arvindve.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText editItemTextField;
    String itemText;
    int itemPosition;
    private static final String TAG = "EditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        editItemTextField = (EditText) findViewById(R.id.etEditItem);

        Intent i = getIntent();
        itemText = i.getStringExtra("itemtext");
        itemPosition = i.getIntExtra("itemposition", 0);

        editItemTextField.setText(itemText);
    }

    public void onSaveClick(View view) {
        String newText = editItemTextField.getText().toString();
        if(newText.trim().length() > 0) {
            Log.i(TAG, "save clicked, calling main activity");

            Intent data = new Intent();

            data.putExtra(Constants.ItemText, newText);
            data.putExtra(Constants.ItemPosition, itemPosition);
            // Activity finished ok, return the data
            setResult(RESULT_OK, data); // set result code and bundle data for response
            finish(); // closes the activity, pass data to parent
        }
    }
}
