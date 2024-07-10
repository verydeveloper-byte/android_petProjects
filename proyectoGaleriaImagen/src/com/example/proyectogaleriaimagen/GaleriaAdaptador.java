package com.example.proyectogaleriaimagen;

import android.R.color;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GaleriaAdaptador extends BaseAdapter {
    private int fondo;
    private Context galeriaContext;
    private Bitmap [] imagenBitmap;
    private Bitmap manejador;
    
    public GaleriaAdaptador(Context c)
    {
    	galeriaContext=c;
    	imagenBitmap=new Bitmap[10];
    	manejador=BitmapFactory.decodeResource(c.getResources(),R.drawable.ic_launcher);
    	for (int i=0;i<imagenBitmap.length;i++)
    	{
    		imagenBitmap[i]=manejador;
    	}
    	TypedArray atributos=galeriaContext.obtainStyledAttributes(R.styleable.imagenGa);
    	fondo=atributos.getResourceId(R.styleable.imagenGa_android_galleryItemBackground,0);
    	atributos.recycle();
    }
	
	public int getCount() {
		// TODO Auto-generated method stub
		return imagenBitmap.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ImageView imagen=new ImageView(galeriaContext);
		imagen.setImageBitmap(imagenBitmap[arg0]);
		imagen.setLayoutParams(new Gallery.LayoutParams(200,100));
		imagen.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imagen.setBackgroundResource(fondo);
		return imagen;
	}
	public void addImagen(Bitmap imagenNueva,int posicion)
	{
		imagenBitmap[posicion]=imagenNueva;
	}
	public Bitmap getImagen(int pos)
	{
		return imagenBitmap[pos];
	}
     
}
