/*
 * Creator: Hitesh Sahu on 2/8/19 1:56 PM
 * Last modified: 2/8/19 1:56 PM
 * Copyright: All rights reserved Ⓒ 2019 http://hiteshsahu.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file    except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.hiteshsahu.stt_tts.translation_engine

import android.app.Activity
import com.hiteshsahu.stt_tts.translation_engine.translators.SpeechToTextConverter
import com.hiteshsahu.stt_tts.translation_engine.translators.TextToSpeckConverter

/**
 * Created by Hitesh on 12-07-2016.
 *
 *
 * This Factory class return object of TTS or STT depending on input enum TRANSLATORS
 */
class TranslatorFactory private constructor() {

    enum class TRANSLATORS {
        TEXT_TO_SPEECH,
        SPEECH_TO_TEXT
    }

    interface IConverter {
        fun initialize(message: String, appContext: Activity): IConverter

        fun getErrorText(errorCode: Int): String
    }


    fun with(TRANSLATORS: TRANSLATORS, conversionCallback: ConversionCallback): IConverter {
        return when (TRANSLATORS) {
            TranslatorFactory.TRANSLATORS.TEXT_TO_SPEECH ->
                //Get Text to speech translator
                TextToSpeckConverter(conversionCallback)

            TranslatorFactory.TRANSLATORS.SPEECH_TO_TEXT ->

                //Get speech to text translator
                SpeechToTextConverter(conversionCallback)
        }
    }

    companion object {
        val instance = TranslatorFactory()
    }
}
