package com.example.cars;

import static com.example.cars.CarsApplication.cardata;
import static com.example.cars.CarsApplication.sortcardata;
import static com.example.cars.CarsApplication.delcar;
import static com.example.cars.CarsApplication.addcar;
import java.sql.SQLException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CarsApplicationTests {
        @Test
	void testcardataexist() throws SQLException {
            int expected = cardata("brand","test").size();
            int actual = 2;
            Assert.isTrue(expected == actual,"cardata 2 Exist Error");
	}
        @Test
	void testsortcardataexist() throws SQLException {
            int expected = sortcardata("date","brand","test").size();
            int actual = 2;
            Assert.isTrue(expected == actual,"sortcardata 2 Exist Error");
	}
        @Test
	void testcardatanotexist() throws SQLException {
            int expected = cardata("brand","testNotExist").size();
            int actual = 0;
            Assert.isTrue(expected == actual,"cardata Not Exist Error");
	}
        @Test
	void testsortcardatanotexist() throws SQLException {
            int expected = sortcardata("date","brand","testNotExist").size();
            int actual = 0;
            Assert.isTrue(expected == actual,"sortcardata Not Exist Error");
	}
        @Test
	void testdelnotexist() throws SQLException {
            int expected = delcar("0");
            int actual = 0;
            Assert.isTrue(expected == actual,"delcar Not Exist Error");
	}
        @Test
	void testaddnotexist() throws SQLException {
            JSONObject obj = new JSONObject();
            obj.put("reg", "AA333A32");
            obj.put("brand", "testadd");
            obj.put("colour", "black");
            obj.put("year", "2018");
            obj.put("ownerid", "5");
            obj.put("vin", "65656565656565656");
            int expected = addcar(obj);
            int actual = 1;
            Assert.isTrue(expected == actual,"addcar Not Exist Error");
	}
        @Test
	void testdelexist() throws SQLException {
            JSONArray array = cardata("reg", "AA333A32","brand", "testadd","colour", "black","year", "2018","ownerid", "5","vin", "65656565656565656");
            JSONObject obj = (JSONObject) array.get(0);
            String str = (String) obj.get("idcar");
            int expected = delcar(str);
            int actual = 1;
            Assert.isTrue(expected == actual,"delcar Exist Error");
	}
        @Test
	void testaddexist() throws SQLException {
            JSONObject obj = new JSONObject();
            obj.put("reg", "AA333A32");
            obj.put("brand", "testadd2");
            obj.put("colour", "black");
            obj.put("year", "2018");
            obj.put("ownerid", "5");
            obj.put("vin", "65656565656565656");
            int expected = addcar(obj);
            int actual = 0;
            Assert.isTrue(expected == actual,"addcar Exist Error");
	}
    

}
