package com.tcs.speechtotextpoc.translation_engine;

import com.tcs.speechtotextpoc.translation_engine.translators.IConvertor;
import com.tcs.speechtotextpoc.translation_engine.translators.SpeechToTextConvertor;
import com.tcs.speechtotextpoc.translation_engine.translators.TextToSpechConvertor;
import com.tcs.speechtotextpoc.translation_engine.utils.ConversionCallaback;

/**
 * Created by Hitesh on 12-07-2016.
 */
public class TranslatorFactory {

    private static TranslatorFactory ourInstance = new TranslatorFactory();

    private TranslatorFactory() {
    }

    public static TranslatorFactory getInstance() {
        return ourInstance;
    }

    public IConvertor getTranslator(TRANSLATOR_TYPE translator_type, ConversionCallaback conversionCallaback) {
        switch (translator_type) {
            case TEXT_TO_SPEECH:

                return new TextToSpechConvertor(conversionCallaback);

            case SPEECH_TO_TEXT:
                return new SpeechToTextConvertor(conversionCallaback);
        }

        return null;
    }


    public enum TRANSLATOR_TYPE {TEXT_TO_SPEECH, SPEECH_TO_TEXT}


}
