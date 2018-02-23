package com.jdbc.ftl;


import com.google.common.base.Charsets;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppMain {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        Properties jdbc = new Properties();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "core.properties";
        String jdbcConfigPath = rootPath + "jdbc.properties";
        properties.load(new FileInputStream(appConfigPath));
        jdbc.load(new FileInputStream(jdbcConfigPath));

        String jdbcDriverStr = jdbc.getProperty("jdbcDriverStr");
        String jdbcURL = jdbc.getProperty("jdbcURL");

        ConnectToCouch couch = new ConnectToCouch();
        int noOfThreads = Integer.parseInt(properties.getProperty("threads"));
        int totalLineCount = Math.toIntExact(Files.lines(Paths.get(properties.getProperty("data"))).count());
        int fullRange = totalLineCount/noOfThreads;
        ExecutorService executor = Executors.newFixedThreadPool(noOfThreads);
        Date startDate = new Date();
        for (int i=0;i<noOfThreads;i++){
            if (i != (noOfThreads -1)){
                List<String> fileList = getLinesFromFile(new File(properties.getProperty("data")),fullRange*i,fullRange*(i+1)-1);
                ConnectToDB connectLoop = new ConnectToDB(jdbcDriverStr,jdbcURL);
                Runnable worker = new InsertJson(new ConnectToCouch(),connectLoop,fileList);
                executor.execute(worker);
            }else {
                List<String> fileList = getLinesFromFile(new File(properties.getProperty("data")),fullRange*i,totalLineCount);
                ConnectToDB connectLoop = new ConnectToDB(jdbcDriverStr,jdbcURL);
                Runnable worker = new InsertJson(new ConnectToCouch(),connectLoop,fileList);
                executor.execute(worker);
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads in "+((new Date()).getTime() - startDate.getTime())/1000 +" second");
    }

    static List<String> getLinesFromFile(File file, int startLine, int endLine) {
        try {
            return com.google.common.io.Files.readLines(file, Charsets.UTF_8,
                    new LineProcessor<List<String>>() {

                        List<String> processedLines = new ArrayList<>();
                        int counter = 0;

                        @Override
                        public boolean processLine(String s) throws IOException {
                            if (counter >= startLine && counter <= endLine) {
                                processedLines.add(s.replaceAll("\"","%22"));
                                counter++;
                                return true;
                            } else if (counter < startLine){
                                counter++;
                                return true;
                            } else {
                                return false;
                            }
                        }

                        @Override
                        public List<String> getResult() {
                            return processedLines;
                        }
                    });

        } catch (IOException e) {

        } catch (Exception e) {

        }

        return Collections.emptyList();
    }


}
