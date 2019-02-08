/*
 * Creator: Hitesh Sahu on 2/8/19 1:56 PM
 * Last modified: 2/8/19 1:56 PM
 * Copyright: All rights reserved Ⓒ 2019 http://hiteshsahu.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file    except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.hiteshsahu.stt_tts.demo

import android.app.Activity
import android.content.Intent
import android.support.design.widget.Snackbar
import com.hiteshsahu.stt_tts.R
import com.hiteshsahu.stt_tts.translation_engine.ConversionCallback
import com.hiteshsahu.stt_tts.translation_engine.TranslatorFactory
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : BasePermissionActivity() {

    override fun getActivityLayout(): Int {
        return R.layout.activity_home
    }


    override fun setUpView() {

        setSupportActionBar(toolBar)



        //SPEECH TO TEXT DEMO
        speechToText.setOnClickListener({ view ->

            Snackbar.make(view, "Speak now, App is listening", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            TranslatorFactory
                    .instance
                    .with(TranslatorFactory.TRANSLATORS.SPEECH_TO_TEXT,
                            object : ConversionCallback {
                                override fun onSuccess(result: String) {
                                    sttOutput.text = result
                                }

                                override fun onCompletion() {
                                }

                                override fun onErrorOccurred(errorMessage: String) {
                                    erroConsole.text = "Speech2Text Error: $errorMessage"
                                }

                            }).initialize("Speak Now !!", this@HomeActivity)

        })


        //TEXT TO SPEECH DEMO
        textToSpeech.setOnClickListener({ view ->

            val stringToSpeak :String = ttsInput.text.toString()

            if (null!=stringToSpeak &&  stringToSpeak.isNotEmpty()) {

                TranslatorFactory
                        .instance
                        .with(TranslatorFactory.TRANSLATORS.TEXT_TO_SPEECH,
                                object : ConversionCallback {
                                    override fun onSuccess(result: String) {
                                    }

                                    override fun onCompletion() {
                                    }

                                    override fun onErrorOccurred(errorMessage: String) {
                                        erroConsole.text = "Text2Speech Error: $errorMessage"
                                    }

                                })
                        .initialize(stringToSpeak, this)

            } else {
                ttsInput.setText("Invalid input")
                Snackbar.make(view, "Please enter some text to speak", Snackbar.LENGTH_LONG).show()
            }

        })

    }

    fun findString(listOfPossibleMatches: ArrayList<String>?, stringToMatch: String): Boolean {

        if (null != listOfPossibleMatches) {

            for (transaltion in listOfPossibleMatches) {

                if (transaltion.contains(stringToMatch)) {

                    return true
                }
            }
        }
        return false
    }

    /**
     * Share on social media
     *
     * @param messageToShare message To Share
     * @param activity       context
     */
    fun share(messageToShare: String, activity: Activity) {

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, messageToShare)
        activity.startActivity(Intent.createChooser(shareIntent, "Share using"))
    }
}


