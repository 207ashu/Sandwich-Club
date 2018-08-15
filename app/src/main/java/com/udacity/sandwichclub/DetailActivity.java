package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView name,alsoKnownAs,placeOfOrigin,ingredients,description;
    ImageView img;

    ArrayList<Sandwich> sandwichList;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        sandwichList=MainActivity.mSandwichList;

        populateUI();

        Intent intent = getIntent();
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (intent == null||position==DEFAULT_POSITION) {
            closeOnError();
        }
        else{

            String desc=sandwichList.get(position).getDescription();
            String mainName=sandwichList.get(position).getMainName();
            String imgUrl=sandwichList.get(position).getImage();
            String origin=sandwichList.get(position).getPlaceOfOrigin();

            List<String> ing=sandwichList.get(position).getIngredients();
            List<String> knownAs=sandwichList.get(position).getAlsoKnownAs();


            setTitle(mainName);
            name.setText(mainName);

            if(origin.equals(""))
                placeOfOrigin.setText(R.string.data_not_available);
            else
                placeOfOrigin.setText(origin);

            if (desc.equals(""))
                description.setText(R.string.data_not_available);
            else
                description.setText(desc);

            Picasso.with(this).load(imgUrl).into(img);

            if(ing.size()==0){
              ingredients.setText(R.string.data_not_available);
            }
            else{
                for (int i=0;i<ing.size();i++){
                    ingredients.append("\u2022 "+ing.get(i)+"\n");
                }
            }

            if(knownAs.size()==0){
                alsoKnownAs.setText(R.string.data_not_available);
            }
            else{
                for (int i=0;i<knownAs.size();i++){
                    alsoKnownAs.append("\u2022 "+knownAs.get(i)+"\n");
                }
            }

        }
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        name=findViewById(R.id.name);
        img=findViewById(R.id.imageView);
        placeOfOrigin=findViewById(R.id.place_of_origin);
        alsoKnownAs=findViewById(R.id.also_known_as);
        ingredients=findViewById(R.id.ingredients);
        description=findViewById(R.id.description);

    }
}
