package com.app.util;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleMapsApi extends HttpConnection {
    private static final String BASE_URI_STRING = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String GET_REQUEST = "GET";

    public String getLocation (Double latitud, Double longitud) throws Exception {
  
        Map<String, String> parameters = new HashMap<>();

        parameters.put("latlng", latitud.toString() + "," + longitud.toString());
        parameters.put("skolary-pro", "AIzaSyC6FpePYbx3VG1G42lS8apv5jqkfa3Xn58");
        String json = getRequest(GET_REQUEST, BASE_URI_STRING, parameters);

        return json;
     
    }

   
}
