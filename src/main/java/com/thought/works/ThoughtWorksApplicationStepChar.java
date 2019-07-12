package com.thought.works;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author binaymishrabca@gmail.com
 */
public class ThoughtWorksApplicationStepChar {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        Client client = Client.create();
        WebResource webResource = client.resource("https://http-hunt.thoughtworks-labs.net/challenge/input");
        ClientResponse response = webResource
                .header("userId", "mrryfcT51")
                .accept("application/json").get(ClientResponse.class);


        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        String json = response.getEntity(String.class);
        System.out.println("Output from Server .... \n");
        System.out.println(json);

        TwRequest twRequest = GSON.fromJson(json, TwRequest.class);

        int count = twRequest.text.toCharArray().length;

        TwResponseChar twResponseChar = new TwResponseChar();
        twResponseChar.count = Integer.valueOf(count);

        String postJson = GSON.toJson(twResponseChar);

        System.out.println("sampleOutPutJson = " + postJson);

        webResource = client.resource("https://http-hunt.thoughtworks-labs.net/challenge/output");
        ClientResponse postResponse = webResource
                .header("userId", "mrryfcT51")
                .type("application/json")
                .post(ClientResponse.class, postJson);

        System.out.println("Output from Server .... \n" + postResponse.getStatus());
        String responseJson = postResponse.getEntity(String.class);
        System.out.println(responseJson);

    }

}

