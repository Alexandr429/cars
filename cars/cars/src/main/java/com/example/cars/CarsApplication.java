package com.example.cars;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarsApplication {
    static String url = "jdbc:mysql://localhost:3306/cars?serverTimezone=Europe/Moscow&useSSL=false";
    static String username = "root";
    static String password = "root12345";
    private static ResultSet rs;
    static void testdb(){
        try{
             Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
             try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                System.out.println("Connection to DB succesfull!");
            }
         }
         catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
         }
    }
    static JSONArray cardata(String... str) throws SQLException{
        JSONArray resultArray = new JSONArray();
        if(str.length == 0)
        {
            try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "Select * FROM car";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
                int counter = md.getColumnCount();
                String colName[] = new String[counter];
                for(int i = 1; i <= counter;i++)
                {
                    colName[i-1] = md.getColumnName(i);
                }
                    while (rs.next()) {
                        JSONObject result = new JSONObject();
                        for(int i = 0; i < counter;i++)
                        {
                            result.put(colName[i],rs.getString(colName[i]));
                        }
                        resultArray.add(result);
                    }
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
            }
        }
        if((str.length % 2 == 0) && (str.length > 0))
        {
            try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "Select * FROM car WHERE ";
                for(int i = 0; i < str.length;i++)
                {
                    if(i % 2 == 0)
                    {
                        query = query + str[i] + " = ";
                    }
                    if(i % 2 == 1)
                    {
                        query = query + "'" + str[i] + "'";
                        if(i < (str.length - 1))
                            query = query + " and ";
                    }
                }
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
                int counter = md.getColumnCount();
                String colName[] = new String[counter];
                for(int i = 1; i <= counter;i++)
                {
                    colName[i-1] = md.getColumnName(i);
                }
                    while (rs.next()) {
                        JSONObject result = new JSONObject();
                        for(int i = 0; i < counter;i++)
                        {
                            result.put(colName[i],rs.getString(colName[i]));
                        }
                        resultArray.add(result);
                    }
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
            }
            
        }
        if(str.length % 2 != 0)
        {
            System.out.println("Ошибка ввода");
        }
        return resultArray;
    }
    static JSONArray sortcardata(String sortpar, String... str) throws SQLException{
        JSONArray resultArray = new JSONArray();
        if(str.length == 0)
        {
            try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "Select * FROM car ORDER BY ";
                query = query + sortpar;
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
                int counter = md.getColumnCount();
                String colName[] = new String[counter];
                for(int i = 1; i <= counter;i++)
                {
                    colName[i-1] = md.getColumnName(i);
                }
                    while (rs.next()) {
                        JSONObject result = new JSONObject();
                        for(int i = 0; i < counter;i++)
                        {
                            result.put(colName[i],rs.getString(colName[i]));
                        }
                        resultArray.add(result);
                    }
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
            }
        }
        if((str.length % 2 == 0) && (str.length > 0))
        {
            try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "Select * FROM car WHERE ";
                for(int i = 0; i < str.length;i++)
                {
                    if(i % 2 == 0)
                    {
                        query = query + str[i] + " = ";
                    }
                    if(i % 2 == 1)
                    {
                        query = query + "'" + str[i] + "'";
                        if(i < (str.length - 1))
                            query = query + " and ";
                    }
                }
                query = query +" ORDER BY " + sortpar;
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
                int counter = md.getColumnCount();
                String colName[] = new String[counter];
                for(int i = 1; i <= counter;i++)
                {
                    colName[i-1] = md.getColumnName(i);
                }
                    while (rs.next()) {
                        JSONObject result = new JSONObject();
                        for(int i = 0; i < counter;i++)
                        {
                            result.put(colName[i],rs.getString(colName[i]));
                        }
                        resultArray.add(result);
                    }
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
              
             System.out.println(ex);
            }
            
        }
        if(str.length % 2 != 0)
        {
            System.out.println("Ошибка ввода");
        }
        return resultArray;
    }
    static int addcar(JSONObject jscar) {
        int result = -1;
        Iterator<String> keys = jscar.keySet().iterator();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String testquery = "";
                String querykey = "";
                String queryval = "";
                    while(keys.hasNext())
                    {
                        String key = (String) keys.next();
                        testquery = testquery + " " + key + " = '" + jscar.get(key) + "'";
                        querykey = querykey + key;
                        queryval = queryval + "'" + jscar.get(key) + "'";
                        if(keys.hasNext())
                        {
                            testquery = testquery + " AND";
                            querykey = querykey + ",";
                            queryval = queryval + ",";
                        }
                    }
                String test = "Select COUNT(*) FROM car WHERE" + testquery;
                Statement stmt = conn.createStatement();
                ResultSet count = stmt.executeQuery(test);
                count.next();
                if(count.getInt(1) == 0)
                {
                    String query = "INSERT INTO car (" + querykey + ") VALUES (" + queryval + ");";
                    result = stmt.executeUpdate(query);
                }
                else
                {
                    result = 0;
                }
                
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
            }
        return result;  
    }
    static int delcar(String str){
        int result = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "DELETE FROM car Where idcar = ";
                query =query + "'" + str + "'";
                Statement stmt = conn.createStatement();
                result = stmt.executeUpdate(query);
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
            }
        return result;
    }
    static JSONObject dbstat(int table){
        JSONObject result = new JSONObject();
        if(table == 0)
        {
            try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "SELECT * FROM   information_schema.tables WHERE  TABLE_SCHEMA = 'cars' AND TABLE_NAME = 'car'";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
                int counter = md.getColumnCount();
                String colName[] = new String[counter];
                for(int i = 1; i <= counter;i++)
                {
                    colName[i-1] = md.getColumnName(i);
                }
                rs.next();       
                for(int i = 0; i < counter;i++)
                {
                    result.put(colName[i],rs.getString(colName[i]));
                }
                        
                    
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
            }
        }
        if(table != 0)
        {
            try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String query = "Select * FROM car ORDER BY date";
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                rs.next();
                String first = rs.getString("date");
                String last = "";
                while(rs.next())
                last = rs.getString("date");
                String test = "Select COUNT(*) FROM car";
                Statement stmtcount = conn.createStatement();
                ResultSet count = stmtcount.executeQuery(test);
                count.next();
                result.put("First_added", first);
                result.put("Last_added", last);
                result.put("Num_of_rows", count.getInt(1));
                }
            }
            catch(Exception ex){
             System.out.println("Connection failed...");
             System.out.println(ex);
            }
        }
        
            return result;
        }
    


	public static void main(String[] args) throws SQLException, JsonProcessingException {
		SpringApplication.run(CarsApplication.class, args);
                JSONObject obj = new JSONObject();
                String par[] = new String[4];
                par[0] = "brand";
                par[1] = "Tesla";
                par[2] = "colour";
                par[3] = "red";
                obj.put("reg", "AA333A32");
                obj.put("brand", "Tesla");
                obj.put("colour", "black");
                obj.put("year", "2018");
                obj.put("ownerid", "5");
                obj.put("vin", "65656565656565656");
                //System.out.println(addcar(obj));
                //System.out.println(cardata("brand","Tesla"));
                //System.out.println(sortcardata("date",par));
                //System.out.println(dbstat(1));
	}

}

