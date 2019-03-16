package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        //define the returned object
        Sandwich sandwich = new Sandwich();

        //define the variables of Json
        String mainName = null;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();

        //fetch all data from json object to variables

        try {
            JSONObject sandwichAsJson = new JSONObject(json);
            //mainName
            JSONObject sandwichNameAsJson = sandwichAsJson.getJSONObject("name");
            mainName = sandwichNameAsJson.getString("mainName");//or get()
            //alsoKnownAs
            JSONArray akaAsJsonArray = sandwichNameAsJson.getJSONArray("alsoKnownAs");
            for (int i=0; i<akaAsJsonArray.length(); i++){
                alsoKnownAs.add(akaAsJsonArray.getString(i));
            }
            //placeOfOrigin
            placeOfOrigin = sandwichAsJson.getString("placeOfOrigin");//or get()
            //description
            description = sandwichAsJson.getString("description");//or get()
            //image
            image = sandwichAsJson.getString("image");//or get()
            //ingredients
            JSONArray ingredientsAsJsonArray = sandwichAsJson.getJSONArray("ingredients");
            for(int i=0; i<ingredientsAsJsonArray.length(); i++){
                ingredients.add(ingredientsAsJsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //assign variables into sandwich object
        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoKnownAs);
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setDescription(description);
        sandwich.setImage(image);
        sandwich.setIngredients(ingredients);

        //return sandwich
        return sandwich;
    }
}
