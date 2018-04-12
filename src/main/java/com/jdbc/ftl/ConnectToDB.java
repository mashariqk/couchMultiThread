package com.jdbc.ftl;

import java.sql.*;
import java.util.Date;

public class ConnectToDB {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private  String jdbcDriverStr;
    private  String jdbcURL;

    public ConnectToDB(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    public void insertJsonData(String invStr,String json, String xml) throws Exception{
        {
            try {
                Class.forName(jdbcDriverStr);
                connection = DriverManager.getConnection(jdbcURL);
                preparedStatement = connection.prepareStatement("insert into inv_couch_load(invStr, jsonout, xmlavail) values (?, ?, ?)");
                preparedStatement.setString(1,invStr);
                preparedStatement.setString(2,json);
                preparedStatement.setString(3,xml);
                java.util.Date startDate = new Date();
                preparedStatement.executeUpdate();
                connection.commit();
            }finally {
                close();
            }
        }
    }

    public void readAndInsertTestData() throws Exception{
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from dbo.inv_couch_load;");
            getResultSet(resultSet);
            preparedStatement = connection.prepareStatement("insert into dbo.inv_couch_load values (?,?)");
            preparedStatement.setString(1,"test");
            preparedStatement.setString(2,"test");
            preparedStatement.executeUpdate();
            connection.commit();
        }finally {
close();
        }
    }

    private void getResultSet(ResultSet resultSet) throws Exception {
        while(resultSet.next()){
            String invStr = resultSet.getString("inv_Str");
            String jsonout = resultSet.getString("jsonout");
            System.out.println("invStr: "+invStr);
            System.out.println("jsonout: "+jsonout);
        }
    }

    private void close(){
        try {
            if(resultSet!=null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        } catch(Exception e){}
    }
}
