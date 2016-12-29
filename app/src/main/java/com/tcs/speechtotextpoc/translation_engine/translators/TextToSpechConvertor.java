package com.tcs.speechtotextpoc.translation_engine.translators; 
 
import android.annotation.TargetApi; 
import android.app.Activity;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
 
import com.tcs.speechtotextpoc.translation_engine.utils.ConversionCallaback; 
import com.tcs.speechtotextpoc.translation_engine.utils.TranslatorUtil; 
 
import java.util.HashMap;
import java.util.Locale;
 
/** 
 * Created by Hitesh on 12-07-2016. 
 */ 
public class TextToSpechConvertor implements IConvertor { 
 
    public TextToSpechConvertor(ConversionCallaback conversioCallaBack) {
        this.conversionCallaBack = conversioCallaBack;
    } 
 
    private ConversionCallaback conversionCallaBack;
 
    private TextToSpeech textToSpeech;
 
    /** 
     * This method will initialize and translate message provided 
     * 
     * @param message    message to speak 
     * @param appContext Note:- Donnot forgot to call finish when 
     */ 
    @Override 
    public TextToSpechConvertor initialize(final String message, Activity appContext) {
        textToSpeech = new TextToSpeech(appContext, new TextToSpeech.OnInitListener() {
            @Override 
            public void onInit(int status) {
 
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.getDefault());
                    textToSpeech.setPitch(1.3f);
                    textToSpeech.setSpeechRate(1f);
                    textToSpeech.setLanguage(Locale.UK);
 
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    } else { 
                        ttsUnder20(message);
                    } 
                } else { 
                    conversionCallaBack.onErrorOccured(TranslatorUtil.FAILED_TO_INITILIZE_TTS_ENGINE);
                } 
            } 
        }); 
 
        return this;
    } 
 
 
    public void finish() { 
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        } 
    } 
 
    @SuppressWarnings("deprecation") 
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
 
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
 
            @Override 
            public void onStart(String utteranceId) {
                // TODO Auto-generated method stub 
 
            } 
 
            @Override 
            public void onError(String utteranceId) {
                // TODO Auto-generated method stub 
 
            } 
 
            @Override 
            public void onDone(String utteranceId) {
                //do some work here 
 
                conversionCallaBack.onCompletion();
            } 
        }); 
 
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    } 
 
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId = this.hashCode() + "";
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    } 
 
} 