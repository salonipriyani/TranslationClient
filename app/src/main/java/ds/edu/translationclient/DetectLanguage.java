package ds.edu.translationclient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.google.gson.Gson;
//Name: Saloni Priyani
// Andrew ID: spriyani

/*
This class is responsible for running the background task
of detecting the input language
 */
public class DetectLanguage {

    MainTranslate mainTranslate = null;
    String textToDetect;
    public void detectLang(String textToDetect, MainTranslate me, Activity activity) {
        // Set the instance variables to the provided values.
        this.mainTranslate = me;
        this.textToDetect = textToDetect;

        // Create a new instance of the BackgroundTask class and execute it.
        // The task will run asynchronously in the background and call back to the
        // MainTranslate instance when it's finished.
        new BackgroundTask(activity).execute();
    }

    // Private class to run the background task on another thread then pass it back to the
    // UI thread
    private class BackgroundTask {

        private Activity activity = null; // The UI thread
        Language language = null;

        public BackgroundTask(Activity activity) {
            this.activity = activity;
        }

        private void startBackground() {
            new Thread(new Runnable() {
                public void run() {

                    try {
                        doInBackground();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
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

        private void execute(){
            // Starts background task
            startBackground();
        }

        // doInBackground() calls API with the text to be detected
        private void doInBackground() throws IOException {
            language = callApi(textToDetect);
        }

        // onPostExecute( ) will run on the UI thread after the background
        //    thread completes.
        // Calls the MainTranslate.textDetected method after receiving response from the API
        public void onPostExecute() {
            mainTranslate.textDetected(language);
        }

        /*
         * Calls an API to detect the language of a given text string
         */
        private Language callApi(String string) {
            try {
                // Create a URL string for the API endpoint with the given text string
                String urlString = "https://salonipriyani-ideal-disco-4qr7j77qjw6fqw7j-8080.preview.app.github.dev/detectLanguage?textToDetect=" + URLEncoder.encode(string, "UTF-8");
                System.out.println(urlString);

                // Create a URL object from the URL string
                URL url = new URL(urlString);

                // Open a connection to the URL and set the request method to GET
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Initialize variables to hold the response data
                String jsonString = "";
                Language language = null;

                // Get the response code from the connection
                int responseCode = connection.getResponseCode();

                // Set up JSON data if the response code is good
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    // Read the response data into a StringBuilder
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    reader.close();
                    inputStream.close();

                    // Convert the response data to a JSON string
                    jsonString = stringBuilder.toString();
                    System.out.println("json string" + jsonString);

                    // Deserialize the JSON string to a Language object using Gson
                    Gson gson = new Gson();
                    language = gson.fromJson(jsonString, Language.class);
                }
                else{
                    System.out.println("HTTP Reply Code: " + connection.getResponseCode());
                }
                System.out.println(jsonString);

                // Return the Language object
                return language;
            }
            catch (IOException e) {
                return null;
            }
        }

    }
}
