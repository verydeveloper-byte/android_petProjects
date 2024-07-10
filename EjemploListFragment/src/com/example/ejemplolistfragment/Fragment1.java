package com.example.ejemplolistfragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Fragment1 extends ListFragment {
	 
    // Array de String que contiene nuestros queridos Sistemas Operativos
    private String[] sistemas = { "Android", "Ubuntu", "Mac OSX", "Windows",
            "Solaris", "Windows 8", "Ubuntu 12.04", "Windows Phone",
            "Windows 7", "Kubuntu", "Ubuntu 12.10" };
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecemos el Adapter a la Lista del Fragment
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, sistemas));
    }
 
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Mostramos un mensaje con el elemento pulsado
        Toast.makeText(getActivity(), "Ha pulsado " + sistemas[position],
                Toast.LENGTH_SHORT).show();
    }
 
}
