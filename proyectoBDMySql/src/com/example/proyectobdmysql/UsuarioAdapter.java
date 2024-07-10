package com.example.proyectobdmysql;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UsuarioAdapter extends BaseAdapter {
	protected Activity actividad;
	protected ArrayList<Usuarios> elementos;
	
	
	public UsuarioAdapter(Activity actividad,ArrayList<Usuarios> elementos)
	{
		this.actividad=actividad;
		this.elementos=elementos;
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return elementos.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return elementos.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View aux=arg1;
		if (aux==null)
		{
			LayoutInflater inflado=(LayoutInflater)actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			aux=inflado.inflate(R.layout.list_item_layout, null);
		}
		Usuarios usu=elementos.get(arg0);
		TextView login=(TextView)aux.findViewById(R.id.tvlogin);
		login.setText(usu.getLogin());
		TextView pass=(TextView)aux.findViewById(R.id.tvPass);
		pass.setText(usu.getPass());
		return aux;
	}

}
