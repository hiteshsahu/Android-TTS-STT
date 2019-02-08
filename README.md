# Android Easy Text to Speech & Speech to Text without annoying dialog(TTS & STT)

## Android TTS and STT is one line solution to convert text to speech(TTS) & speech to text(STT) in your Android App.

- Convert Speech to text without annoying dialog.
- Convert Text to Speech with error handling and callbacks

## Usage 

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



##Screenshots of Demo screen

![Alt text](https://github.com/hiteshsahu/Android-TTS-STT/blob/master/Art/demo.png "demo")

##Use in your project

Simply drop translation_engine package in your project and start using it.


Copyright 2015 Hitesh Kumar Sahu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.






