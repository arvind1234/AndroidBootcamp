package com.arvindve.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText editText;
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = todoItems.get(position);

                Intent editActivityIntent = new Intent(MainActivity.this, EditItemActivity.class);
                editActivityIntent.putExtra(Constants.ItemText, text);
                editActivityIntent.putExtra(Constants.ItemPosition, position);
                startActivityForResult(editActivityIntent, REQUEST_CODE);
            }
        });

        editText = (EditText) findViewById(R.id.etNewItem);
    }

    private void populateArrayItems() {
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }

    public void onAddItemClick(View addButton) {
        String text = editText.getText().toString();
        if(text.trim().length() > 0) {
            aTodoAdapter.add(text);
            editText.setText("");
            writeItems();
        }
    }

    private void readItems() {
        File fileDir = getFilesDir();
        File file = new File(fileDir, "todo.txt");

        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch(FileNotFoundException ex) {
            todoItems = new ArrayList<String>();
        } catch(IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private void writeItems() {
        File fileDir = getFilesDir();
        File file = new File(fileDir, "todo.txt");

        try {
            FileUtils.writeLines(file, todoItems);
        } catch(IOException ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String text = data.getExtras().getString(Constants.ItemText);
            int position = data.getExtras().getInt(Constants.ItemPosition, 0);


            Log.i(TAG, String.format("setting %s at position %d", text, position));
            todoItems.set(position, text);
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }
}
