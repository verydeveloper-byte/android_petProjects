package com.example.ejemplofragment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends Fragment {
	 
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.fragment2, container, false);
    }
 
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Button btnGetText = (Button) getActivity().findViewById(
                R.id.btnObtenerTexto);
        //Button btnGetText = (Button) getView().findViewById(R.id.btnObtenerTexto);
        btnGetText.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                TextView lbl = (TextView) getActivity().findViewById(
                        R.id.lblFragment1);
                Toast.makeText(getActivity(), lbl.getText(), Toast.LENGTH_SHORT)
                        .show();
            }
 
        });
    }
 
 
}
