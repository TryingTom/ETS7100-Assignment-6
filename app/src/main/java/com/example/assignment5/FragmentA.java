package com.example.assignment5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment5.R;

public class FragmentA extends Fragment {
    private FragmentAListener listener;
    private EditText textview1;
    private EditText textview2;

    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmenta, container, false);

        textview1 = v.findViewById(R.id.TextView1);
        textview2 = v.findViewById(R.id.TextView2);

        //listener.onInputASent(input);
        // this is how you get input or something

        return v;
    }

    public void updateEditText(CharSequence newText){
        textview1.setText(newText);
        textview2.setText(newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
            + " must implement FragmentAListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
