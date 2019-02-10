package com.alphadevelopmentsolutions.frcscout.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiParser
{
    private Api api;

    public ApiParser(Api api)
    {
        this.api = api;
    }

    /**
     * Parses data from the server
     * Must be ran on a new thread
     * @return XML in string format
     * @throws IOException
     */
    public JSONArray parse() throws IOException, JSONException
    {
        String response = queryAPI();
        //parse and return the response
        try
        {
            return new JSONArray(new JSONObject(response));
        }
        catch(JSONException e)
        {
            return new JSONArray(response);
        }
    }

    private String queryAPI() throws IOException
    {
        //response from server
        StringBuilder response = new StringBuilder();

        //create the url based on the app URL and specified file
        URL url = new URL(api.getURL() + "?X-TBA-Auth-Key=gGDqr1h7gbcdKAumaFgnuzPJYDox7vz6gyX1a8r9nA0VPPLYBD8q1Uj8byvUR5Lp");

        //create a new connection to the server
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        //read response from the server
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        //for each line in the response, append to the response string
        for (String line; (line = bufferedReader.readLine()) != null; )
            response.append(line);


        httpURLConnection.disconnect();

        return response.toString();
    }

}

