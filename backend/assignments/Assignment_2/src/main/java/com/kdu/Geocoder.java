package com.kdu;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import java.text.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

public class Geocoder {

    public LocDetail getLocationFromGeocoder(String url) throws IOException, InterruptedException, ParseException {
        LocDetail result;
        result = new LocDetail();
        var client =  HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var parser = new JSONParser();
        Object obj = parser.parse(response.body());
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonResults = (JSONArray) jsonObject.get("results");
        for ( Object element: jsonResults){
            JSONObject object = (JSONObject) element;
            String cmpleteAddress = (String) object.get("formatted");
            JSONObject annotations = (JSONObject) object.get("annotations");
            JSONObject dms = (JSONObject) annotations.get("DMS");
            String lat = (String) dms.get("lat");
            String lng = (String) dms.get("lng");
            result.findLatLong(lat,lng,cmpleteAddress,true);
            break;
        }
        return result;
    }
}


