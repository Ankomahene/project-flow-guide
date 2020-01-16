package io.turntabl.orc.jsonToORC.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readingJSONFile {
    public static void  readFile(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("sampledata1.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            System.out.println(obj);

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.get("users"));

            JSONArray jsonArray = (JSONArray) jsonObject.get("users");

            System.out.println("===============Getting All Users=============");

            jsonArray.forEach(user -> {
                JSONObject userObject = (JSONObject) user;
                System.out.println(userObject.get("firstName"));
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile();
    }

}
