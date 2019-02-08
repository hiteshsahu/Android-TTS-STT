/*
 * Creator: Hitesh Sahu on 2/8/19 1:56 PM
 * Last modified: 2/8/19 1:56 PM
 * Copyright: All rights reserved Ⓒ 2019 http://hiteshsahu.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file    except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.hiteshsahu.stt_tts.translation_engine.translators

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.hiteshsahu.stt_tts.translation_engine.ConversionCallback
import com.hiteshsahu.stt_tts.translation_engine.TranslatorFactory
import java.util.*


class SpeechToTextConverter(private val conversionCallback: ConversionCallback)  : TranslatorFactory.IConverter {

    private   val  TAG = SpeechToTextConverter::class.java.name

    override fun initialize(message: String, appContext: Activity): SpeechToTextConverter {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                message)
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                appContext.getPackageName())

        //Add listeners
        val listener = CustomRecognitionListener()
        val sr = SpeechRecognizer.createSpeechRecognizer(appContext)
        sr.setRecognitionListener(listener)
        sr.startListening(intent)
        return this
    }

    internal inner class CustomRecognitionListener : RecognitionListener {

        override fun onReadyForSpeech(params: Bundle) {
            Log.d(TAG, "onReadyForSpeech")
        }

        override fun onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech")
        }

        override fun onRmsChanged(rmsdB: Float) {
            Log.d(TAG, "onRmsChanged")
        }

        override fun onBufferReceived(buffer: ByteArray) {
            Log.d(TAG, "onBufferReceived")
        }

        override fun onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech")
        }

        override fun onError(error: Int) {
            Log.e(TAG, "error $error")
            conversionCallback.onErrorOccurred(getErrorText(error))
        }

        override fun onResults(results: Bundle) {
            var translateResults = String()
            val data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            for ( result in  data) {
                translateResults += result + "\n"
            }
            conversionCallback.onSuccess(translateResults)
        }

        override fun onPartialResults(partialResults: Bundle) {
            Log.d(TAG, "onPartialResults")
        }

        override  fun onEvent(eventType: Int, params: Bundle) {
            Log.d(TAG, "onEvent $eventType")
        }
    }

    override fun  getErrorText(errorCode: Int): String {
        val message: String
        when (errorCode) {
            SpeechRecognizer.ERROR_AUDIO -> message = "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> message = "Client side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> message = "Insufficient permissions"
            SpeechRecognizer.ERROR_NETWORK -> message = "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> message = "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> message = "No match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> message = "RecognitionService busy"
            SpeechRecognizer.ERROR_SERVER -> message = "error from server"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> message = "No speech input"
            else -> message = "Didn't understand, please try again."
        }
        return message
    }



} 