package ds.edu.translationclient;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Name: Saloni Priyani
// Andrew ID: spriyani
// Define the class MainTranslate and inherit from AppCompatActivity
public class MainTranslate extends AppCompatActivity  {
    // Create an instance of MainTranslate
    MainTranslate me = this;
    // A string containing a list of language codes and names in JSON format
    String languagesList = "[{\"code\":\"aa\",\"name\":\"AFAR\"},{\"code\":\"ab\",\"name\":\"ABKHAZIAN\"},{\"code\":\"af\",\"name\":\"AFRIKAANS\"},{\"code\":\"ak\",\"name\":\"AKAN\"},{\"code\":\"am\",\"name\":\"AMHARIC\"},{\"code\":\"ar\",\"name\":\"ARABIC\"},{\"code\":\"as\",\"name\":\"ASSAMESE\"},{\"code\":\"ay\",\"name\":\"AYMARA\"},{\"code\":\"az\",\"name\":\"AZERBAIJANI\"},{\"code\":\"ba\",\"name\":\"BASHKIR\"},{\"code\":\"be\",\"name\":\"BELARUSIAN\"},{\"code\":\"bg\",\"name\":\"BULGARIAN\"},{\"code\":\"bh\",\"name\":\"BIHARI\"},{\"code\":\"bi\",\"name\":\"BISLAMA\"},{\"code\":\"bn\",\"name\":\"BENGALI\"},{\"code\":\"bo\",\"name\":\"TIBETAN\"},{\"code\":\"br\",\"name\":\"BRETON\"},{\"code\":\"bs\",\"name\":\"BOSNIAN\"},{\"code\":\"bug\",\"name\":\"BUGINESE\"},{\"code\":\"ca\",\"name\":\"CATALAN\"},{\"code\":\"ceb\",\"name\":\"CEBUANO\"},{\"code\":\"chr\",\"name\":\"CHEROKEE\"},{\"code\":\"co\",\"name\":\"CORSICAN\"},{\"code\":\"crs\",\"name\":\"SESELWA\"},{\"code\":\"cs\",\"name\":\"CZECH\"},{\"code\":\"cy\",\"name\":\"WELSH\"},{\"code\":\"da\",\"name\":\"DANISH\"},{\"code\":\"de\",\"name\":\"GERMAN\"},{\"code\":\"dv\",\"name\":\"DHIVEHI\"},{\"code\":\"dz\",\"name\":\"DZONGKHA\"},{\"code\":\"egy\",\"name\":\"EGYPTIAN\"},{\"code\":\"el\",\"name\":\"GREEK\"},{\"code\":\"en\",\"name\":\"ENGLISH\"},{\"code\":\"eo\",\"name\":\"ESPERANTO\"},{\"code\":\"es\",\"name\":\"SPANISH\"},{\"code\":\"et\",\"name\":\"ESTONIAN\"},{\"code\":\"eu\",\"name\":\"BASQUE\"},{\"code\":\"fa\",\"name\":\"PERSIAN\"},{\"code\":\"fi\",\"name\":\"FINNISH\"},{\"code\":\"fj\",\"name\":\"FIJIAN\"},{\"code\":\"fo\",\"name\":\"FAROESE\"},{\"code\":\"fr\",\"name\":\"FRENCH\"},{\"code\":\"fy\",\"name\":\"FRISIAN\"},{\"code\":\"ga\",\"name\":\"IRISH\"},{\"code\":\"gd\",\"name\":\"SCOTS_GAELIC\"},{\"code\":\"gl\",\"name\":\"GALICIAN\"},{\"code\":\"gn\",\"name\":\"GUARANI\"},{\"code\":\"got\",\"name\":\"GOTHIC\"},{\"code\":\"gu\",\"name\":\"GUJARATI\"},{\"code\":\"gv\",\"name\":\"MANX\"},{\"code\":\"ha\",\"name\":\"HAUSA\"},{\"code\":\"haw\",\"name\":\"HAWAIIAN\"},{\"code\":\"hi\",\"name\":\"HINDI\"},{\"code\":\"hmn\",\"name\":\"HMONG\"},{\"code\":\"hr\",\"name\":\"CROATIAN\"},{\"code\":\"ht\",\"name\":\"HAITIAN_CREOLE\"},{\"code\":\"hu\",\"name\":\"HUNGARIAN\"},{\"code\":\"hy\",\"name\":\"ARMENIAN\"},{\"code\":\"ia\",\"name\":\"INTERLINGUA\"},{\"code\":\"id\",\"name\":\"INDONESIAN\"},{\"code\":\"ie\",\"name\":\"INTERLINGUE\"},{\"code\":\"ig\",\"name\":\"IGBO\"},{\"code\":\"ik\",\"name\":\"INUPIAK\"},{\"code\":\"is\",\"name\":\"ICELANDIC\"},{\"code\":\"it\",\"name\":\"ITALIAN\"},{\"code\":\"iu\",\"name\":\"INUKTITUT\"},{\"code\":\"iw\",\"name\":\"HEBREW\"},{\"code\":\"ja\",\"name\":\"JAPANESE\"},{\"code\":\"jw\",\"name\":\"JAVANESE\"},{\"code\":\"ka\",\"name\":\"GEORGIAN\"},{\"code\":\"kha\",\"name\":\"KHASI\"},{\"code\":\"kk\",\"name\":\"KAZAKH\"},{\"code\":\"kl\",\"name\":\"GREENLANDIC\"},{\"code\":\"km\",\"name\":\"KHMER\"},{\"code\":\"kn\",\"name\":\"KANNADA\"},{\"code\":\"ko\",\"name\":\"KOREAN\"},{\"code\":\"ks\",\"name\":\"KASHMIRI\"},{\"code\":\"ku\",\"name\":\"KURDISH\"},{\"code\":\"ky\",\"name\":\"KYRGYZ\"},{\"code\":\"la\",\"name\":\"LATIN\"},{\"code\":\"lb\",\"name\":\"LUXEMBOURGISH\"},{\"code\":\"lg\",\"name\":\"GANDA\"},{\"code\":\"lif\",\"name\":\"LIMBU\"},{\"code\":\"ln\",\"name\":\"LINGALA\"},{\"code\":\"lo\",\"name\":\"LAOTHIAN\"},{\"code\":\"lt\",\"name\":\"LITHUANIAN\"},{\"code\":\"lv\",\"name\":\"LATVIAN\"},{\"code\":\"mfe\",\"name\":\"MAURITIAN_CREOLE\"},{\"code\":\"mg\",\"name\":\"MALAGASY\"},{\"code\":\"mi\",\"name\":\"MAORI\"},{\"code\":\"mk\",\"name\":\"MACEDONIAN\"},{\"code\":\"ml\",\"name\":\"MALAYALAM\"},{\"code\":\"mn\",\"name\":\"MONGOLIAN\"},{\"code\":\"mr\",\"name\":\"MARATHI\"},{\"code\":\"ms\",\"name\":\"MALAY\"},{\"code\":\"mt\",\"name\":\"MALTESE\"},{\"code\":\"my\",\"name\":\"BURMESE\"},{\"code\":\"na\",\"name\":\"NAURU\"},{\"code\":\"ne\",\"name\":\"NEPALI\"},{\"code\":\"nl\",\"name\":\"DUTCH\"},{\"code\":\"no\",\"name\":\"NORWEGIAN\"},{\"code\":\"nr\",\"name\":\"NDEBELE\"},{\"code\":\"nso\",\"name\":\"PEDI\"},{\"code\":\"ny\",\"name\":\"NYANJA\"},{\"code\":\"oc\",\"name\":\"OCCITAN\"},{\"code\":\"om\",\"name\":\"OROMO\"},{\"code\":\"or\",\"name\":\"ORIYA\"},{\"code\":\"pa\",\"name\":\"PUNJABI\"},{\"code\":\"pl\",\"name\":\"POLISH\"},{\"code\":\"ps\",\"name\":\"PASHTO\"},{\"code\":\"pt\",\"name\":\"PORTUGUESE\"},{\"code\":\"qu\",\"name\":\"QUECHUA\"},{\"code\":\"rm\",\"name\":\"RHAETO_ROMANCE\"},{\"code\":\"rn\",\"name\":\"RUNDI\"},{\"code\":\"ro\",\"name\":\"ROMANIAN\"},{\"code\":\"ru\",\"name\":\"RUSSIAN\"},{\"code\":\"rw\",\"name\":\"KINYARWANDA\"},{\"code\":\"sa\",\"name\":\"SANSKRIT\"},{\"code\":\"sco\",\"name\":\"SCOTS\"},{\"code\":\"sd\",\"name\":\"SINDHI\"},{\"code\":\"sg\",\"name\":\"SANGO\"},{\"code\":\"si\",\"name\":\"SINHALESE\"},{\"code\":\"sk\",\"name\":\"SLOVAK\"},{\"code\":\"sl\",\"name\":\"SLOVENIAN\"},{\"code\":\"sm\",\"name\":\"SAMOAN\"},{\"code\":\"sn\",\"name\":\"SHONA\"},{\"code\":\"so\",\"name\":\"SOMALI\"},{\"code\":\"sq\",\"name\":\"ALBANIAN\"},{\"code\":\"sr\",\"name\":\"SERBIAN\"},{\"code\":\"ss\",\"name\":\"SISWANT\"},{\"code\":\"st\",\"name\":\"SESOTHO\"},{\"code\":\"su\",\"name\":\"SUNDANESE\"},{\"code\":\"sv\",\"name\":\"SWEDISH\"},{\"code\":\"sw\",\"name\":\"SWAHILI\"},{\"code\":\"syr\",\"name\":\"SYRIAC\"},{\"code\":\"ta\",\"name\":\"TAMIL\"},{\"code\":\"te\",\"name\":\"TELUGU\"},{\"code\":\"tg\",\"name\":\"TAJIK\"},{\"code\":\"th\",\"name\":\"THAI\"},{\"code\":\"ti\",\"name\":\"TIGRINYA\"},{\"code\":\"tk\",\"name\":\"TURKMEN\"},{\"code\":\"tl\",\"name\":\"TAGALOG\"},{\"code\":\"tlh\",\"name\":\"KLINGON\"},{\"code\":\"tn\",\"name\":\"TSWANA\"},{\"code\":\"to\",\"name\":\"TONGA\"},{\"code\":\"tr\",\"name\":\"TURKISH\"},{\"code\":\"ts\",\"name\":\"TSONGA\"},{\"code\":\"tt\",\"name\":\"TATAR\"},{\"code\":\"ug\",\"name\":\"UIGHUR\"},{\"code\":\"uk\",\"name\":\"UKRAINIAN\"},{\"code\":\"ur\",\"name\":\"URDU\"},{\"code\":\"uz\",\"name\":\"UZBEK\"},{\"code\":\"ve\",\"name\":\"VENDA\"},{\"code\":\"vi\",\"name\":\"VIETNAMESE\"},{\"code\":\"vo\",\"name\":\"VOLAPUK\"},{\"code\":\"war\",\"name\":\"WARAY_PHILIPPINES\"},{\"code\":\"wo\",\"name\":\"WOLOF\"},{\"code\":\"xh\",\"name\":\"XHOSA\"},{\"code\":\"yi\",\"name\":\"YIDDISH\"},{\"code\":\"yo\",\"name\":\"YORUBA\"},{\"code\":\"za\",\"name\":\"ZHUANG\"},{\"code\":\"zh\",\"name\":\"CHINESE_SIMPLIFIED\"},{\"code\":\"zh-Hant\",\"name\":\"CHINESE_TRADITIONAL\"},{\"code\":\"zu\",\"name\":\"ZULU\"}]";
    Type languageListType = new TypeToken<List<Language>>() {}.getType();
    List<Language> languages = new Gson().fromJson(languagesList, languageListType);

    // Override the onCreate method of AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity using the XML file
        setContentView(R.layout.activity_main);

        //Define object pointing to current class for callback
        final MainTranslate ma = this;

        Button submitButton = (Button)findViewById(R.id.submit);


        // Add a listener to the submit button
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View viewParam) {
                // Get text to be detected
                String textToDetect = ((EditText)findViewById(R.id.searchTerm)).getText().toString();
                System.out.println("textToDetect = " + textToDetect);
                try {
                    //validate if user input is valid
                    validateUserInput(textToDetect);
                    DetectLanguage dl = new DetectLanguage();
                    //call DetectLanguage's detectLang method with reference to current class
                    dl.detectLang(textToDetect, me, ma);

                } catch (InvalidInputException e) {
                    // make error text visible
                    TextView errorInput = (TextView) findViewById(R.id.errorInput);
                    errorInput.setText(e.getMessage());
                    errorInput.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    /*
    This method validates user input by checking if the input contains only alphabets.
    If the input contains non-alphabetic characters, it throws an InvalidInputException.
     */
    private void validateUserInput(String textToDetect) throws InvalidInputException {
        // Define a regex pattern that matches only alphabets.
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        // Create a Matcher object to match the pattern against the input string.
        Matcher matcher = pattern.matcher(textToDetect);
        // Check if the input string matches the alphabetic pattern.
        if (!matcher.matches()) {
            // Throw an exception if the input string contains non-alphabetic characters.
            throw new InvalidInputException("Invalid input: input should contain only alphabets");
        }
    }


    //This function is called once the language has been detected on the input string
    @SuppressLint("WrongViewCast")
    public void textDetected(Language language) {
        try {
            // Check if the language is null, throw an IOException if true
            if (language == null){
                throw new IOException();
            }
            // Save a reference to the current instance of MainTranslate
            final MainTranslate ma = this;
            // Get the TextView for displaying the detected language
            TextView detectedLangView = (TextView) findViewById(R.id.detectedLangView);
            // Set the text to display the name of the detected language
            detectedLangView.setText("The language of the text is " + language.getName());
            // Create an empty ArrayList for storing the names of the available languages
            List<String> languageList = new ArrayList<>();
            // Loop through the available languages and add their names to the ArrayList
            for (Language l : languages) {
                languageList.add(l.getName());
            }
            // Create an ArrayAdapter for populating the Spinner with the available languages
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languageList);
            // Get the Spinner view and set its adapter to the ArrayAdapter
            Spinner spinner = findViewById(R.id.lang_spinner);
            spinner.setAdapter(adapter);
            // Get the Button view for the translate action
            Button translateButton = (Button)findViewById(R.id.translate);
            // Set a click listener for the translate action
            translateButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View viewParam) {
                    // Get the currently selected language from the Spinner
                    Spinner spinner = findViewById(R.id.lang_spinner);
                    String toLang = spinner.getSelectedItem().toString();
                    // Get the code for the detected language
                    String fromLangCode = language.getCode();
                    // Get the text to be translated from the EditText view
                    String textToTranslate = ((EditText)findViewById(R.id.searchTerm)).getText().toString();
                    String toLangCode = "en";
                    // Loop through the available languages to get the code for the target language
                    for (Language l: languages){
                        if (l.getName().equalsIgnoreCase(toLang))
                            toLangCode = l.getCode();
                    }
                    // Create a new instance of TranslateGet for performing the translation asynchronously
                    TranslateGet tg = new TranslateGet();
                    tg.translate(textToTranslate, fromLangCode, toLangCode, me, ma); // Done asynchronously in another thread.  It calls ip.pictureReady() in this thread when complete.
                }
            });
        }
        // Catch any IOException thrown earlier and display an error message in the UI
        catch (IOException e){
            TextView errorNetwork = (TextView) findViewById(R.id.errorNetwork);
            errorNetwork.setVisibility(View.VISIBLE);
        }

    }


    /*
    The function is called after the input text has been translated to the desired language
     */
    public void textTranslated(String translatedString) {
        try {
            // Check if the translated string is null
            if (translatedString == null)
                throw new IOException();

            // Update the text view with the translated string
            TextView translatedTextView = (TextView) findViewById(R.id.translatedTextView);
            translatedTextView.setText("The translated text is " + translatedString);
        } catch (IOException e){
            // If there is an exception, display an error message
            TextView errorNetwork = (TextView) findViewById(R.id.errorNetwork);
            errorNetwork.setVisibility(View.VISIBLE);
        }
    }
}
