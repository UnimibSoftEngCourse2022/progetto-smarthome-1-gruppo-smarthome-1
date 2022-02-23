package com.smarthome.smarthome.testPatriot;

import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

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
