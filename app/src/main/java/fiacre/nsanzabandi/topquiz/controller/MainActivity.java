package fiacre.nsanzabandi.topquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fiacre.nsanzabandi.topquiz.R;
import fiacre.nsanzabandi.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView greetingText;
    private EditText nameInput;
    private Button playButton;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        greetingText = findViewById(R.id.activity_main_greeting_text);
        nameInput = findViewById(R.id.activity_main_name_input);
        playButton = findViewById(R.id.activity_main_play_btn);

        playButton.setEnabled(false);

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                playButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirstName(nameInput.getText().toString());

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });
    }
}
