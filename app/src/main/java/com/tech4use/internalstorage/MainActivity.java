package com.tech4use.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // this is simple example to create and save textFiles in Internal Storage

    EditText edtxt_text;
    TextView textView;
    Button btn_save, btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // finding all the ids
        edtxt_text = findViewById(R.id.edtxt_text);
        textView =findViewById(R.id.textView);
        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);

    }

    // saving the text on Click of save button
    public void saveClicked(View view) {
        /** creating a object of FileOutputStream to save openFileOutput data
         *  openFileOutput is used to write data in a textView
         *  also adding read mode
         * @param Private - only visible to our app
         * @param Append - can be seen by other apps and gets modified on adding new data
         */
        // getting the text added by user
        String text = edtxt_text.getText().toString();
        // this is for getting the saved file directory
        File file = null;
        // checking if text is empty
        if (text.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter the Text", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                FileOutputStream fileOutputStream = openFileOutput("myText.txt",
                        MODE_PRIVATE);
                fileOutputStream.write(text.getBytes());
                // getting the file directory
                file = getFilesDir();
                fileOutputStream.close();
                edtxt_text.setText("");
                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void loadClicked(View view) {
        /** creating a object of FileIutputStream to save openFileInput data
         *  openFileInput is used to read data from a textView
         *  must have same of text file as saved text file
         *  we have to read from textFile byte by byte
         *  so if alphabets ends in textFile it returns -1
         *  so reading till we get -1
         *  finally appending all the alphabets in a StringBuffer
         */
        File file = null;
        try {
            FileInputStream fileInputStream = openFileInput("myText.txt");
            int read = -1;
            file = getFilesDir();
            StringBuilder builder = new StringBuilder();
            while ((read = fileInputStream.read())!=-1) {
                builder.append((char)read);
            }
            textView.setText(builder.toString());
            Toast.makeText(this, "Loaded successfully", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
