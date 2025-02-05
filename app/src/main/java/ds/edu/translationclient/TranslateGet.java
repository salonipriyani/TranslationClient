package ds.edu.translationclient;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//Name: Saloni Priyani
// Andrew ID: spriyani

/*
This class is responsible for running the background task
of translating the input text from the detected language
to the user-desired output language
 */
public class TranslateGet {

    MainTranslate mainTranslate = null;
    String textToTranslate;
    String fromLangCode;
    String toLangCode;


    public void translate(String textToTranslate, String fromLangCode, String toLangCode, MainTranslate me, Activity activity) {
        // Set the values of mainTranslate, textToTranslate, fromLangCode, and toLangCode
        // to the instance variables of the class.
        this.mainTranslate = me;
        this.textToTranslate = textToTranslate;
        this.fromLangCode = fromLangCode;
        this.toLangCode = toLangCode;

        // Create a new BackgroundTask2 object and execute it in the background.
        // Pass the activity to the BackgroundTask2 constructor so that it can update
        // the UI after the translation is complete.
        new BackgroundTask2(activity).execute();
    }

    // Private class to run the background task on another thread then pass it back to the
    // UI thread
    private class BackgroundTask2 {

        private Activity activity; // The UI thread
        Language language;
        String translatedString;
        public BackgroundTask2(Activity activity) {
            this.activity = activity;
        }

        private void startBackground() {
            new Thread(new Runnable() {
                public void run() {

                    doInBackground();
                    // This is magic: activity should be set to MainTranslate.this
                    //    then this method uses the UI thread
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            onPostExecute();
                        }
                    });
                }
            }).start();
        }

        // start background task
        private void execute() {
            startBackground();
        }

        // doInBackground( ) call the call API method
        // with the necessary parameters
        private void doInBackground() {
            translatedString = callApi(textToTranslate, fromLangCode, toLangCode);
        }

        // onPostExecute( ) will run on the UI thread after the background
        //    thread completes.
        // It passes the translated string back to the UI thread
        public void onPostExecute() {
            mainTranslate.textTranslated(translatedString);
        }

        /*
         * Calls the APi deployed in codespaces to convert the
         * required text into a language detected by the first API
         * to a language chosen by the user
         */
        private String callApi(String string, String fromLangCode, String toLangCode) {
            try {
                // Set up the URL with the text to translate and language codes
                String urlString = "https://salonipriyani-ideal-disco-4qr7j77qjw6fqw7j-8080.preview.app.github.dev/translate?textToTranslate=" + URLEncoder.encode(string, "UTF-8") + "&fromLang=" + URLEncoder.encode(fromLangCode, "UTF-8") + "&toLang=" + URLEncoder.encode(toLangCode, "UTF-8");
                URL url = new URL(urlString);

                // Open a connection to the URL and set the request method to GET
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Read the response from the API and store it as a string
                String jsonString = "";
                String translatedString = "";
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();
                    inputStream.close();
                    jsonString = stringBuilder.toString();

                    // Parse the JSON response and extract the translated text
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
                    translatedString = String.valueOf(jsonObject.get("translatedText"));
                } else {
                    System.out.println("HTTP Reply Code: " + connection.getResponseCode());
                }

                // Return the translated string
                System.out.println(translatedString);
                return translatedString;

            } catch (IOException e) {
                // Return null if there is an IOException
                return null;
            }
        }
    }
}
