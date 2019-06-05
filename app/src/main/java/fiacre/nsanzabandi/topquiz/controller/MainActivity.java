package fiacre.nsanzabandi.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences sharedPreferences;
    public static final String PREFERENCE_KEY_USER_SCORE = "PREFERENCE_KEY_USER_SCORE";
    public static final String PREFERENCE_KEY_FIRSTNAME = "PREFERENCE_KEY_FIRSTNAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            sharedPreferences.edit().putInt(PREFERENCE_KEY_USER_SCORE, score).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        sharedPreferences = getPreferences(MODE_PRIVATE);
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
                String userFirstName = nameInput.getText().toString();
                user.setFirstName(userFirstName);
                sharedPreferences.edit().putString(PREFERENCE_KEY_FIRSTNAME, user.getFirstName()).apply();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    /**
     * TODO Update this part to wellcome the user and tell him his last score stored in sharedPreferences.
     */
    private void startGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome" + sharedPreferences.getString(PREFERENCE_KEY_FIRSTNAME, PREFERENCE_KEY_FIRSTNAME));
        builder.setMessage("Your score was: " + sharedPreferences.getString(PREFERENCE_KEY_USER_SCORE, PREFERENCE_KEY_USER_SCORE));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}
