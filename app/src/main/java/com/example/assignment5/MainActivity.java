package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    ListView mListView;
    Button GET;
    RequestQueue jono;

    /*
    ->"Fragmentit on kyllä vaikeita, mutta joskus nekin pitää käydä..."
    ->Antaa tehtävän jossa miljoona muuttujaa
    <-Ei tajua edes kontekstia

    Muuten ihan hyvä tehtävä, mutta oman työmuistin kapasiteetti ei riitä tajuamaan kaikkea mitä pitäisi tehdä.
    Olisi hyvä opetella perusteet ennenkuin lähetään soveltamaan näin laajoilla tehtävillä.
    Fragmentit ovat ehkä vähän liian suuri harppaus tässä vaiheessa, se vielä että pitää käyttää SQLiteä...
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentA, fragmentA)
                .replace(R.id.fragmentB, fragmentB)
                .commit();

        ListView mListView = (ListView) findViewById(R.id.fragmentA);
        /*jono = Volley.newRequestQueue(this);
        final Gson gson = new Gson();

        // test

        final Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format((calendar.getTime()));
        Person example = new Person("Test", currentDate);


        final ArrayList<Person> peopleList = new ArrayList<>();
        // then add the person
        //peopleList.add(example);

        final PersonListAdapter adapter = new PersonListAdapter(this, R.layout.FragmentA, peopleList);
        mListView.setAdapter(adapter);*/


        /*
        GET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(HaveNetwork()){
                    // Pressing GET-button again overwrites the text with the new response
                    adapter.clear();
                    JsonArrayRequest request = new JsonArrayRequest(
                            "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1",
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    for (int i = 0; i < response.length(); i++) {
                                        try {
                                            JSONObject jsonObject = response.getJSONObject(i);


                                            // ilman deserializointia:
                                            /*
                                            String PVM = jsonObject.getString("pvm");
                                            String name = jsonObject.getString("nimi");
                                            String onlyPVM = null;


                                            // insinööriratkaisu formatointiin:
                                            Person person = new Person(name, PVM.substring(0,10));


                                            peopleList.add(person);
                                            adapter.notifyDataSetChanged();


                                            // deserialize?
                                            Person person1 = gson.fromJson(String.valueOf(jsonObject), Person.class);
                                            person1.setPVM(person1.getPVM().substring(0,10));
                                            peopleList.add(person1);
                                            adapter.notifyDataSetChanged();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
                    );
                    jono.add(request);


                }   // HaveNetwork (true) end
                else{
                    Toast.makeText(MainActivity.this, "Not connected to the internet :(", Toast.LENGTH_SHORT)
                            .show();
                }   // HaveNetwork (false) end
            }
        });*/


    }

    @Override
    public void onInputASent(CharSequence input) {
        //FragmentB.updateEditText(input);
    }

    @Override
    public void onInputBSent(JSONObject input) {
        //FragmentA.updateEditText(input);
    }

    private boolean HaveNetwork()
    {
        boolean have_WIFI = false;
        boolean have_MobileData = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI")){
                if (info.isConnected()){
                    have_WIFI = true;
                }
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")){
                if (info.isConnected()){
                    have_MobileData = true;
                }
            }
        }
        return have_MobileData || have_WIFI;
    }
}
