package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.example.entity.TypecodeResponse;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        System.out.println("TypeCode Api");
        String Id = Integer.toString((int) (Math.random() *100));
        System.out.println("Generating ID: " + Id);

        //call Api
        TypecodeResponse typecodeResponse = callWeatherApi(Id);

        System.out.println("---Api response---");
        if (typecodeResponse != null) {
            System.out.println("Title: \n" + typecodeResponse.getTitle());
            System.out.println("Body: \n" + typecodeResponse.getBody());
        }


    }

    private static TypecodeResponse callWeatherApi(String id) {
        OkHttpClient client = new OkHttpClient();

        //create request object
        Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts/" + id)
                .get()
                .build();

        Response response;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //do call for api
            response = client.newCall(request).execute();

            //get response from api
            ResponseBody responseBody = response.body();

            if (responseBody != null) {
                //map response to local model class
                return objectMapper.readValue(responseBody.string(), TypecodeResponse.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}