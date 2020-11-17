package edu.aum.httpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private Button nButton;
    private TextView mTextView;
    private EditText mEdit;
    private EditText nEdit;

    private TextView mText;

    String title = "";
    String year = "%20";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nButton = (Button) findViewById(R.id.buttonSearch); // this is the button to read the search
        mButton = (Button) findViewById(R.id.button);   // this button just runs the default script for debugging NEEDS TO BE REMOVED BEFORE SUBMISSION
       // mTextView = (TextView) findViewById(R.id.res_text_view);

        /*
        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEdit = (EditText)findViewById(R.id.editText1);
                mText = (TextView)findViewById(R.id.textView1);
                mText.setText("Welcome "+mEdit.getText().toString()+"!");
                title = mEdit.getText().toString() + "&";
            }
        });
*/

        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEdit = (EditText)findViewById(R.id.title_input);
                nEdit = (EditText)findViewById(R.id.year_input);
                //mText = (TextView)findViewById(R.id.textView1);
                //mText.setText("Welcome "+mEdit.getText().toString()+"!");
                title = mEdit.getText().toString() + "&";
                year = "y=" + nEdit.getText().toString();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

               // String title = "Pirates&of&the&caribbean";
                //String Year = "%20";


                //Notes about string URL
                //************************************************
                 // the & means space
                // the s=  means the title we are searching for
                //************************************************


                String url ="https://www.omdbapi.com/?apikey=28772f51&s=" + title + year;
             //   String url ="https://www.omdbapi.com/?apikey=28772f51&s=iron&man&y=2010";



                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                intent.putExtra("result", response);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("#######",error.getMessage());
                        mTextView.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);


            }
        });
    }
}
