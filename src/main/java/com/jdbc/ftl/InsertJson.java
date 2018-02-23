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
        for (String url:this.fileList){
            this.connect.insertJsonData(url.substring(url.indexOf("%22") + 3,url.lastIndexOf("%22")),this.couch.getHttpResponse(url));
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
