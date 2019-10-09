package com.example.assignment5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class PersonListAdapter extends ArrayAdapter<Person> {
    private Context mContext;
    int mResource;

    public PersonListAdapter(@NonNull Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, (List<Person>) objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String PVM = getItem(position).getPVM();

        Person person = new Person(name, PVM);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.TextView1);
        TextView tvPVM = (TextView) convertView.findViewById(R.id.TextView2);

        tvName.setText(name);
        tvPVM.setText(PVM);

        return convertView;
    }
}
