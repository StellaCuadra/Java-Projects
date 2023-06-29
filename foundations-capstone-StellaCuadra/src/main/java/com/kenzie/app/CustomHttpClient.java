package com.kenzie.app;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomHttpClient {

    //TODO: Write sendGET method that takes URL and returns response
    public static String sendGET(String URLString) {
        //** Start of GET request algorithm
        HttpClient client = HttpClient.newHttpClient(); // how we connect to the internet

        URI uri = URI.create(URLString);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            // We will return the response body shortly


            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                System.out.println(httpResponse.body());
                return httpResponse.body();
            } else {
                //String.format is fun! Worth googling
                return String.format("Get request failed:%d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();

        }
        // System.out.println(movieName);
        //
        //        String movieLowerCase = movieName.trim().replaceAll(" ",
        //                "%20").replaceAll("'", "%27");
        //
        //        System.out.println(movieLowerCase);


    }
    public static List<CluesDTO> getCluesList(String response){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CluesDTO>> dataType = new TypeReference<>() {
        };
        try {
            List<CluesDTO> cluesDTOList = mapper.readValue(response, dataType);
            System.out.println(cluesDTOList.size());
            return cluesDTOList;
        } catch (Exception e) {
            System.out.println("Something went wrong: "+e.getMessage());

        }
        return new ArrayList<>();
    }


}

