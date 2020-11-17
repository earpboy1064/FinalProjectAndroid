package edu.aum.httpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ResultActivity extends AppCompatActivity {

    private TextView mResTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResTextView = (TextView) findViewById(R.id.result_textview);
        Intent intent = getIntent();
        String jsonResult = intent.getStringExtra("result");
        if(jsonResult != null) {
            try {
                JSONObject root = new JSONObject(jsonResult);
                JSONArray returnedMovies = root.getJSONArray("Search");
                int numOfMovies = returnedMovies.length();
                LinearLayout root_res_layout = (LinearLayout) findViewById(R.id.root_res_layout);
                for(int i=0;i<numOfMovies;i++) {
                    JSONObject movie = returnedMovies.getJSONObject(i);
                    LinearLayout movieLayout = new LinearLayout(ResultActivity.this);
                    movieLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    movieLayout.setOrientation(LinearLayout.VERTICAL);

                    TextView titleTextView = new TextView(ResultActivity.this);
                    titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    titleTextView.setText(movie.getString("Title"));

                    TextView releaseYearTextView = new TextView(ResultActivity.this);
                    releaseYearTextView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    releaseYearTextView.setText(movie.getString("Year"));

                    ImageView posterView = new ImageView(ResultActivity.this);
                    posterView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    if(movie.has("Poster")) {
                        Picasso.get().load(movie.getString("Poster")).into(posterView);
                    }
                    movieLayout.addView(titleTextView);
                    movieLayout.addView(releaseYearTextView);
                    movieLayout.addView(posterView);

                    root_res_layout.addView(movieLayout);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
