package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.ArrayList;



public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich item=new Sandwich();


        try {
            if(json!=null) {


                JSONObject root = new JSONObject(json);
                String imgUrl = root.getString("image");
                String placeOfOrigin = root.getString("placeOfOrigin");
                String desc = root.getString("description");
                JSONObject name = root.getJSONObject("name");
                JSONArray ing = root.getJSONArray("ingredients");
                String mainName = name.getString("mainName");
                JSONArray alsoKnownAs = root.getJSONArray("alsoKnownAs");

                item=new Sandwich(mainName,getListFromArray(alsoKnownAs),placeOfOrigin,desc,imgUrl,getListFromArray(ing));
/*
                item.setMainName(mainName);
                item.setAlsoKnownAs(getListFromArray(alsoKnownAs));
                item.setPlaceOfOrigin(placeOfOrigin);
                item.setDescription(desc);
                item.setImage(imgUrl);
                item.setIngredients(getListFromArray(ing));*/

              //  MainActivity.mSandwichList.add(item);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static ArrayList<String> getListFromArray(JSONArray array){

        ArrayList<String> list=new ArrayList();

            for(int i=0;i<array.length();i++){
                try {
                    list.add(i,array.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        return list;

    }
}
