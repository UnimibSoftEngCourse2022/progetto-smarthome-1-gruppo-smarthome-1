package com.smarthome.SmartHome.testPatriot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthome.SmartHome.user.User;
import com.smarthome.SmartHome.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

import java.io.DataInput;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/testPatriot")
public class TestPatriotController {

    @GetMapping
    public String index(){
        return "ciao";
    }

    @PostMapping()
    public void showData(@RequestBody JSONObject jasonData) {


        System.out.println(jasonData);
    }



}


