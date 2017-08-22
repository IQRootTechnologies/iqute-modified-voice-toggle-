package com.yschi.castscreen;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Dell on 07-08-2017.
 */

public class Books extends Ecommerce
{
    public ToggleButton toggleButtonBok;

    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        toggleButtonBok =(ToggleButton) findViewById(R.id.togglebutton4);

        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);


        toggleButtonBok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    buttonView.setBackgroundDrawable(getDrawable(R.drawable.mic));
                    speech.startListening(recognizerIntent);
                }
                else
                {
                    buttonView.setBackgroundDrawable(getDrawable(R.drawable.mic1));
                    speech.stopListening();
                }

            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBeginningOfSpeech()
    {
        Log.i("onBeginningOfSpeech", LOG_TAG);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i("onBufferReceived: " + buffer, LOG_TAG);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i("onEndOfSpeech", LOG_TAG);
        toggleButtonBok.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d("FAILED " + errorMessage, LOG_TAG);
        toggleButtonBok.setChecked(false);
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {Log.i("onEvent", LOG_TAG);
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i("onPartialResults", LOG_TAG);
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i("onReadyForSpeech", LOG_TAG);
    }

    @Override
    public void onResults(Bundle results) {
        Log.i("onResults", LOG_TAG);
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String text = "";
        for (String result : matches)
            text += result + "\n";

        if (matches.contains("zero") || matches.contains("back"))
        {
            recognizerIntent = new Intent(this, Ecommerce.class);
            startActivity(recognizerIntent);
        }
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i("onRmsChanged: " + rmsdB, LOG_TAG);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

}


