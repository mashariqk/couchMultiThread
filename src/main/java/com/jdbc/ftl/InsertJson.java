package com.jdbc.ftl;

import java.io.IOException;
import java.util.List;

public class InsertJson implements Runnable {

    ConnectToCouch couch;
    ConnectToDB connect;
    List<String> fileList;
    private static int counter = 0;

    public InsertJson(ConnectToCouch couch, ConnectToDB connect, List<String> fileList) {
        this.couch = couch;
        this.connect = connect;
        this.fileList = fileList;
    }

    public void pushDataToCouch() throws Exception {
        for (String urls:this.fileList){
            String[] urlArray = urls.split(",");
            String json = urlArray[0];
            String xml = urlArray[1];
            this.connect.insertJsonData(json.substring(json.indexOf("%22") + 3,json.lastIndexOf("%22")),this.couch.getHttpResponse(json),this.couch.getHttpResponse(xml));
        }
        System.out.println(" Finished Thread "+ ++counter);
    }

    @Override
    public void run() {
        try {
            pushDataToCouch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
