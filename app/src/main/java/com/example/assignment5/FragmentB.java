package com.example.assignment5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class FragmentB extends Fragment {
    private FragmentBListener listener;
    private Button GET;
    private Button SHOW;
    private RequestQueue jono;
    private ListView mListView;



    public interface FragmentBListener{
        void onInputBSent(JSONObject input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentb, container,false);

        GET = v.findViewById(R.id.GET_btn);
        SHOW = v.findViewById(R.id.SHOW_btn);

        GET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jono = Volley.newRequestQueue(getContext());
                final Gson gson = new Gson();

                // test

                final Calendar calendar = Calendar.getInstance();
                String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format((calendar.getTime()));
                Person example = new Person("Test", currentDate);


                final ArrayList<Person> peopleList = new ArrayList<>();
                // then add the person
                //peopleList.add(example);

                final PersonListAdapter adapter = new PersonListAdapter(getContext(), R.layout.fragmenta, peopleList);
                mListView.setAdapter(adapter);

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

                                            listener.onInputBSent(jsonObject);


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
            }
        });

        SHOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }

    public void updateEditText(CharSequence newText){

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBListener){
            listener = (FragmentBListener) context;
        }
        else {
            throw new RuntimeException(context.toString()+" must implement FragmentBListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
