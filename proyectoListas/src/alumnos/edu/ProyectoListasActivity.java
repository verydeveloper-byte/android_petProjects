package alumnos.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProyectoListasActivity extends Activity {
    /** Called when the activity is first created. */
	//private String [] elementos=new String[]{"opcion 1","opcion 2","opcion 3", "opcion 4"};
	private Menu[] datos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     /*   ListView lista=(ListView)findViewById(R.id.lvMenu);
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,elementos);
        lista.setAdapter(adaptador);*/
        datos=new Menu[]{new Menu("Calendario","creacion de un calendario"), new Menu("Emergente","mostrar ventana emergentes"),new Menu("Texto Personal","enviar mensajes")};
        ListView lvMenu =(ListView)findViewById(R.id.lvMenu);
        AdaptadorMenu adaptadorM=new AdaptadorMenu(this,datos);
        lvMenu.setAdapter(adaptadorM);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2==0)
				{
					enviarMensaje();
				}
				
			}
		});
    }
    private void enviarMensaje()
    {
       Intent ventanaCal=new Intent(this, ActivityCalendario.class);
       startActivity(ventanaCal);
    }
}