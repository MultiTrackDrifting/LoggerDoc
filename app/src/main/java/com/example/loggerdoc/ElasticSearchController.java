package com.example.loggerdoc;

import okhttp3.OkHttpClient;

public class ElasticSearchController {

    private static final String HOST = "http://104.237.6.208:9200";
    private static ElasticClient singletonclient;
    private static OkHttpClient client;

    public static ElasticClient getClient(){
        if(singletonclient == null){
            client = getHttpClient();
            singletonclient = new ElasticClient(HOST,client);
        }
        return singletonclient;
    }

    public static OkHttpClient getHttpClient(){
        //Just incase we need this somewhere else
        if(singletonclient == null){
            client = new OkHttpClient();
        }
        return client;
    }
}
