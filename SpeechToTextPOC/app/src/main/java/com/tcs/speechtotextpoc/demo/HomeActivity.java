package com.tcs.speechtotextpoc.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tcs.speechtotextpoc.R;
import com.tcs.speechtotextpoc.translation_engine.TranslatorFactory;
import com.tcs.speechtotextpoc.translation_engine.utils.ConversionCallaback;
import com.tcs.speechtotextpoc.translation_engine.utils.TranslatorUtil;

public class HomeActivity extends AppCompatActivity implements ConversionCallaback {


    private static final int TTS = 0;
    private static final int STT = 1;
    private static int CURRENT_MODE = -1;

    private EditText ttsInput;
    private TextView sttOutput;
    private TextView erroConsole;
    private FloatingActionButton speechToText;
    private FloatingActionButton textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ttsInput = (EditText) findViewById(R.id.tts_input);
        sttOutput = (TextView) findViewById(R.id.stt_output);
        erroConsole = (TextView) findViewById(R.id.error_output);
        speechToText = (FloatingActionButton) findViewById(R.id.listen);
        textToSpeech = (FloatingActionButton) findViewById(R.id.talk);


        speechToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Listening", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                TranslatorFactory.getInstance().getTranslator(TranslatorFactory.TRANSLATOR_TYPE.SPEECH_TO_TEXT, HomeActivity.this).initialize("Hello There", HomeActivity.this);

                CURRENT_MODE = STT;
            }
        });


        textToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Talking", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                TranslatorFactory.getInstance().getTranslator(TranslatorFactory.TRANSLATOR_TYPE.TEXT_TO_SPEECH, HomeActivity.this).initialize((null != ttsInput.getText().toString() && !ttsInput.getText().toString().isEmpty() ? ttsInput.getText().toString() : "Invalid Input"), HomeActivity.this);

                CURRENT_MODE = TTS;
            }
        });


        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sharing", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if (null == sttOutput.getText().toString() && sttOutput.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Error empty spech to text output ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                TranslatorUtil.share(sttOutput.getText().toString(), HomeActivity.this);

            }
        });
    }


    @Override
    public void onSuccess(String result) {
        Toast.makeText(this, "Result " + result, Toast.LENGTH_SHORT).show();

        switch (CURRENT_MODE) {
            case STT:
                sttOutput.setText(result);
        }
    }

    @Override
    public void onCompletion() {
        Toast.makeText(this, "Done ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onErrorOccured(String errorMessage) {
        erroConsole.setText(errorMessage);
    }
}
