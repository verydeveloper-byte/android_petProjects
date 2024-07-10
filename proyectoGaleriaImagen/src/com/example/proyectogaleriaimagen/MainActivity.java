package com.example.proyectogaleriaimagen;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private int seleccion=1;
	private int imagenActual=0;
	private Gallery galeria=null;
	private ImageView imagenView=null;
	private GaleriaAdaptador ga=null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagenView=(ImageView)findViewById(R.id.imagen);
        galeria=(Gallery)findViewById(R.id.galeria);
        
        ga=new GaleriaAdaptador(this);
        galeria.setAdapter(ga);
        galeria.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				imagenActual=arg2;
				Intent imagenIntent=new Intent();
				imagenIntent.setType("image/*");
				imagenIntent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(imagenIntent,"Seleccion Imagen"), seleccion);
				return true;
			}
		});
        galeria.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				imagenView.setImageBitmap(ga.getImagen(arg2));
			}
        	
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data)
    {
    	if (resultCode==RESULT_OK)
    	{
    		if(requestCode==seleccion)
    		{
    			Uri imagenUri=data.getData(); 
    			Bitmap img=null;
    			String imgPath="";
    			String [] medData={MediaStore.Images.Media.DATA};
    			Cursor imgCursor=managedQuery(imagenUri, medData,null,null,null);
    			if (imgCursor!=null)
    			{
    				int index=imgCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    				imgCursor.moveToFirst();
    				imgPath=imgCursor.getString(index);
    			}
    			else{
    				imgPath=imagenUri.getPath();
    			}
    			if (imagenUri!=null)
    			{
    				int ancho=200;
    				int alto=100;
    				int tamNuevo=1;
    				
    				BitmapFactory.Options bmOpciones=new BitmapFactory.Options();
    				bmOpciones.inJustDecodeBounds=true;
    				BitmapFactory.decodeFile(imgPath,bmOpciones);
    				int anchoActual=bmOpciones.outWidth;
    				int altoActual=bmOpciones.outHeight;
    				
    				if (altoActual>alto || anchoActual>ancho)
    				{
    					if (altoActual>anchoActual)
    					{
    						tamNuevo=Math.round((float)anchoActual/(float)altoActual);				
    					}
    					else{
    						tamNuevo=Math.round((float)altoActual/(float)anchoActual);
    					}
    				}
    				bmOpciones.inSampleSize=tamNuevo;
    				bmOpciones.inJustDecodeBounds=false;
    			    img=BitmapFactory.decodeFile(imgPath,bmOpciones);
    			    
    			    ga.addImagen(img, imagenActual);
    			    galeria.setAdapter(ga);
    			    imagenView.setImageBitmap(img);
    			    imagenView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    			}
    		}
    	}
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
}	
    
