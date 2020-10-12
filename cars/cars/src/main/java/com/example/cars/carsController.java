/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cars;

import static com.example.cars.CarsApplication.addcar;
import static com.example.cars.CarsApplication.dbstat;
import static com.example.cars.CarsApplication.cardata;
import static com.example.cars.CarsApplication.delcar;
import java.sql.SQLException;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kozar
 */
@RestController
@RequestMapping("carsdb")
public class carsController {
    
    @GetMapping()
    public JSONObject list() throws SQLException
    {
        JSONObject obj = new JSONObject();
        obj.put("cars", cardata());
        return obj;
    }
    @GetMapping("{stat}")
    public JSONObject getOne(@PathVariable int stat) throws SQLException
    {
        return dbstat(stat);
    }
    @PostMapping()
    public String create(@RequestBody JSONObject message) throws SQLException
    {
        String result = "Error";
        int i = addcar(message);
        if(i == 1)
        {
            result = "Added";
        }
        if(i == 0)
        {
            result = "Already exists";
        }
        return result;
        
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable String id)
    {
        String result = "Error";
        int i = delcar(id);
        if(i == 1)
        {
            result = "Deleted";
        }
        if(i == 0)
        {
            result = "Doesn't exists";
        }
        return result;
    }
}
