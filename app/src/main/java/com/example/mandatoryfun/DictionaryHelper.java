package com.example.mandatoryfun;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class DictionaryHelper extends AsyncTask<String, Integer, String> {

    final String app_id = "86eb06d6";
    final String app_key = "0fcb6d77f8e9e72fbc1c2df41cfdb00b";
    Context context;
    Boolean isSpell;
    String theWord;

    public DictionaryHelper(Context context, Boolean spell, String word) {
        this.context = context;
        isSpell = spell;
        theWord = word;
    }

    @Override
    protected String doInBackground(String... params) {

        //TODO: replace with your own app id and app key

        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();

        String date = sdf.format(c.getTime());
        DBHelper myDb = new DBHelper(context);

        String wordDef;
        try {
            JSONObject js = new JSONObject(result);
            JSONArray def = js.getJSONArray("results");

            JSONObject lEntries = def.getJSONObject(0);
            JSONArray laArray = lEntries.getJSONArray("lexicalEntries");

            JSONObject entries = laArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject d = sensesArray.getJSONObject(0);
            JSONArray de = d.getJSONArray("definitions");

            wordDef = de.getString(0);

            if (isSpell) {
                myDb.insertSpellingData(theWord, date, wordDef, 0, 0);
            }
            else{
                myDb.insertVocabData(theWord, date, wordDef, 0);
            }


        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}