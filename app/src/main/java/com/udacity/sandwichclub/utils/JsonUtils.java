package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    private static final String TAG = "JsonUtilsActivity";


    public static Sandwich parseSandwichJson(String json) {
        //Lists to store the ingredients and alsoKnownAs
        List<String> ingredientsList = new ArrayList<>();
        List<String> alsoKnownAsList = new ArrayList<>();

        try {

            //full details
            JSONObject fullSandwichJSON = new JSONObject(json);
            //only mainName and alsoKnownAs
            JSONObject partSandwichJSON = fullSandwichJSON.getJSONObject("name");
            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(partSandwichJSON.getString("mainName"));
            //iterating through the JSONArray to get alsoKnownAs
            int alsoKnownAsArrLength = partSandwichJSON.getJSONArray("alsoKnownAs").length();
            for (int i = 0; i < alsoKnownAsArrLength; i++) {
                alsoKnownAsList.add(partSandwichJSON.getJSONArray("alsoKnownAs").get(i).toString());
            }

            sandwich.setAlsoKnownAs(alsoKnownAsList);
            sandwich.setPlaceOfOrigin(fullSandwichJSON.getString("placeOfOrigin"));
            //iterating through the JSONArray to get ingredients
            int ingredientsArrLength = fullSandwichJSON.getJSONArray("ingredients").length();
            for (int i = 0; i < ingredientsArrLength; i++) {
                ingredientsList.add(fullSandwichJSON.getJSONArray("ingredients").get(i).toString());
            }

            sandwich.setIngredients(ingredientsList);
            sandwich.setDescription(fullSandwichJSON.getString("description"));
            sandwich.setImage(fullSandwichJSON.getString("image"));

            return sandwich;
        } catch (JSONException e) {
            Log.d(TAG, "JSONException " + e);
        }

        //return null on unsuccessful attempt to parse JSON
        return null;
    }
}
