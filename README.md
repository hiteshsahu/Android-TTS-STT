# Text to Speech & Speech to Text

##One line solution for android Text to speech and text to Speech conversion for Android.

**Project demonstarting how to :-**

- Convert Text to speech ins Android
- Convert Speech to text without Prompting dialog
- Handling errror and Updating UI
- Sharing text content over social media
- Voice search using Speech to text 

## Usage 

Translator used factory pattern to create translators and does job in one line

**Speech to Text** :- One line solution

                TranslatorFactory.getInstance().getTranslator(TranslatorFactory.TRANSLATOR_TYPE.SPEECH_TO_TEXT, HomeActivity.this)
                                              .initialize("Hello There", HomeActivity.this);
                                              
 **Text to Speech** :- One line solution
 
                 TranslatorFactory.getInstance().getTranslator(TranslatorFactory.TRANSLATOR_TYPE.TEXT_TO_SPEECH, HomeActivity.this)
                                                 .initialize((null != message && !message ? message : "Invalid Input"), HomeActivity.this);

 **Error Handling** :- make your view/Activity implement ConversionCallaback
 
    public class HomeActivity extends AppCompatActivity implements ConversionCallaback {
 
 
 override this callabacks
 
 
    @Override
    public void onSuccess(String result) {
        Toast.makeText(this, "Result " + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletion() {
        Toast.makeText(this, "Done ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onErrorOccured(String errorMessage) {
       Toast.makeText(this, "Error " + errorMessage, Toast.LENGTH_SHORT).show();
    }


##Screenshots of Demo screen

For demo import and app in Android Studio

**Text to speech **

![Alt text](https://github.com/hiteshsahu/Text-to-Speech---Speech-to-Text/blob/master/Art/text_to_speech.png "tts")

**Speech to Text **

![Alt text](https://github.com/hiteshsahu/Text-to-Speech---Speech-to-Text/blob/master/Art/speech_to_text.png "stt")

**Process converted output eg. Share converted text on social media

![Alt text](https://github.com/hiteshsahu/Text-to-Speech---Speech-to-Text/blob/master/Art/share.png "share")

**Real time error Handling

![Alt text](https://github.com/hiteshsahu/Text-to-Speech---Speech-to-Text/blob/master/Art/error.png "error")


##Use in your project

Simply drop translation_engine package in your project and start using one liners.


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






