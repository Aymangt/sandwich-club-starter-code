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

public class DetailActivity extends AppCompatActivity {
    //define textviews
    TextView alsoKnownAs;
    TextView description;
    TextView origin;
    TextView ingredients;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        //find the text views
        alsoKnownAs = findViewById(R.id.also_known_tv);
        description = findViewById(R.id.description_tv);
        origin =  findViewById(R.id.origin_tv);
        ingredients = findViewById(R.id.ingredients_tv);

        //assign sandwich variables to them
        String alsoKnownAsString = "";
        for (String aka: sandwich.getAlsoKnownAs()) {
            alsoKnownAsString += aka + ", ";
        }
        if(!alsoKnownAsString.equals("")){
            alsoKnownAsString = alsoKnownAsString.substring(0, alsoKnownAsString.length()-2)+".";
        }else
            alsoKnownAsString = "   ---";
        alsoKnownAs.setText(alsoKnownAsString+"\n");

        description.setText(sandwich.getDescription()+"\n");

        origin.setText(sandwich.getPlaceOfOrigin()+"\n");

        String ingredientsAsString = "";
        for (String ing: sandwich.getIngredients()) {
            ingredientsAsString += ing + ", ";
        }
        if(!ingredientsAsString.equals("")){
            ingredientsAsString = ingredientsAsString.substring(0, ingredientsAsString.length()-2)+".";
        }
        ingredients.setText(ingredientsAsString+"\n");


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
