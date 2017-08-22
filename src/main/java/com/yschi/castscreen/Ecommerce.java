package com.yschi.castscreen;

/**
 * Created by Dell on 07-08-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class Ecommerce extends HomePage implements RecognitionListener
{
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;

    public TextView tView;

    ToggleButton toggleButton1;

    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "VoiceRecognitionActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecommerce);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);

        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);





      /*  if(getResources().getDisplayMetrics().widthPixels>getResources().getDisplayMetrics().
                heightPixels)
        {
            Toast.makeText(this,"Screen switched to Landscape mode",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Screen switched to Portrait mode",Toast.LENGTH_SHORT).show();
        } */






        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
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


    public  void startElectronics(View v)
    {
        Toast.makeText(Ecommerce.this, "Electronics", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Electronics.class));

    }
    public  void startGrocery(View v)
    {
        Toast.makeText(Ecommerce.this, "Grocery", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Grocery.class));
    }
    public  void startBook(View v)
    {
        Toast.makeText(Ecommerce.this, "Books", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Books.class));
    }
    public  void startFashion(View v)
    {
        Toast.makeText(Ecommerce.this, "Fashion", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Fashion.class));
    }
    public  void startLaptop(View v)
    {
        Toast.makeText(Ecommerce.this, "Laptop", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Laptop.class));
    }

    public void startWatch(View v)
    {
        Toast.makeText(Ecommerce.this, "Watches", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this, Watches.class));
    }

    public  void startNext(View v)
    {
        Toast.makeText(Ecommerce.this, "Next", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Next.class));
    }
    public  void startOthers(View v)
    {
        Toast.makeText(Ecommerce.this, "Others", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Others.class));
    }
    public  void startCamera(View v)
    {
        Toast.makeText(Ecommerce.this, "Camera Started", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Ecommerce.this,Camera.class));
    }
    public  void clickCast(View v)
    {
        Toast.makeText(Ecommerce.this, "Welcome to Easy2Buy", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(ScreenMirroring.this,Camera.class));
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
        toggleButton1.setChecked(false);
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d("FAILED " + errorMessage, LOG_TAG);
        toggleButton1.setChecked(false);
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
        String text = "           ";
        for (String result : matches)
            text += result + "\n";

        if (matches.contains("one") || matches.contains("Elect"))
        {
            recognizerIntent = new Intent(this, Electronics.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);
        }

       else if (matches.contains("two") || matches.contains("Gro") || matches.contains("Groceries"))
        {
            recognizerIntent = new Intent(this, Grocery.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);
        }

        else if (matches.contains("three") || matches.contains("Book") || matches.contains(" note"))
        {
            recognizerIntent = new Intent(this, Books.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);
        }
        else if (matches.contains("four") || matches.contains("Fash") || matches.contains("Fashions"))
        {
            recognizerIntent = new Intent(this, Fashion.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);

        }
        else if (matches.contains("five") || matches.contains("Lap") || matches.contains("top"))
        {
            recognizerIntent = new Intent(this, Laptop.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);
        }
        else if (matches.contains("six") || matches.contains("watch") || matches.contains("Watches"))
        {
            recognizerIntent = new Intent(this, Watches.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);

        }
        else if(matches.contains("back") || matches.contains("zero"))
        {
            recognizerIntent = new Intent(this, HomePage.class);
            startActivity(recognizerIntent);
            speech.startListening(recognizerIntent);
        }
        else
        {
            Toast.makeText(Ecommerce.this, "Didn't understand, please try again.", Toast.LENGTH_SHORT).show();
            speech.startListening(recognizerIntent);
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

