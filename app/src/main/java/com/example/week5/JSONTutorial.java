package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONTutorial extends AppCompatActivity {
    private Button btnGetName , btnGetCourse , btnGetAge , btnGetBook , btnGetLibrary , btnGetFriend;
    private TextView tvDisplay;
    private String myJsonObjectString = "{\n" +
            "  \"studentName\": \"Lee Boon Keng\",\n" +
            "  \"courseName\": \"Computer\",\n" +
            "  \"Age\": \"18\",\n" +
            "  \"borrowedbooks\": [\n" +
            "    \"Wealth of the Nations\",\n" +
            "    \"The prince\",\n" +
            "    \"Computer Science\"\n" +
            "  ],\n" +
            "  \"libraryProfile\": {},\n" +
            "  \"libraryId\": \"0003\",\n" +
            "  \"numberOfBorrowed\": \"3\",\n" +
            "  \"allowedToEnter\": \"true\",\n" +
            "  \"friends\": [\n" +
            "    {\n" +
            "      \"name\": \"Avicili\",\n" +
            "      \"status\": \"best Friend\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Justin\",\n" +
            "      \"status\": \"unfriended\",\n" +
            "      \"\": {}\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Jason\",\n" +
            "      \"status\": \"normal friend\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Kelly\",\n" +
            "      \"status\": \"friend\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private JSONObject myJsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsontutorial);
        findViews();
        setListeners();
        prepareJSON();

    }

    private void findViews(){
        btnGetName = findViewById(R.id.btnGetName);
        btnGetCourse = findViewById(R.id.btnGetCourse);
        btnGetAge = findViewById(R.id.btnGetAge);
        tvDisplay = findViewById(R.id.tvDisplay);
        btnGetBook = findViewById(R.id.btnGetBook);
        btnGetLibrary = findViewById(R.id.btnGetLibrary);
        btnGetFriend = findViewById(R.id.btnGetFriend);

    }
    private void setListeners(){
        btnGetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = myJsonObject.getString("studentName");
                    tvDisplay.setText(name);
                }catch(JSONException e) {
                    e.printStackTrace();

                }
            }
        });

        btnGetCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String course = myJsonObject.getString("courseName");
                    tvDisplay.setText(course);
                }catch(JSONException e) {
                    e.printStackTrace();

                }


            }
        });

        btnGetAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String age = myJsonObject.getString("Age");
                    tvDisplay.setText(age);
                }catch(JSONException e) {
                    e.printStackTrace();

                }


            }
        });

        btnGetBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                JSONArray booksArray = new JSONArray(myJsonObject.getString("borrowedbooks"));
                String result = "";
                for(int i=0; i < booksArray.length(); i++){
                    String bookName = booksArray.getString(i);
                    result += bookName + "\n";
                }
                tvDisplay.setText(result);
                }catch(JSONException e) {
                    e.printStackTrace();

                }
            }
        });
        btnGetLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject libraryJsonObject = new JSONObject(myJsonObject.getString("libraryProfile"));
                    String libraryId = myJsonObject.getString("libraryId");
                    tvDisplay.setText(libraryId);
                }catch(JSONException e) {
                    e.printStackTrace();

                }
            }
        });
        btnGetFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray friendsArray = myJsonObject.getJSONArray("friends");
                    for(int i=0; i<friendsArray.length();i++){
                        JSONObject friend = friendsArray.getJSONObject(i);
                        String name = friend.getString("name");
                        if(name.equalsIgnoreCase("Avicili")){
                            tvDisplay.setText(friend.getString("status"));
                        }
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void prepareJSON(){
        try {
            myJsonObject = new JSONObject(myJsonObjectString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
