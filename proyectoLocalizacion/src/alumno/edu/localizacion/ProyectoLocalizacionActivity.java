package alumno.edu.localizacion;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProyectoLocalizacionActivity extends Activity {
    /** Called when the activity is first created. */
	private Button btActivar,btDesactivar;
	private TextView lbLatitud,lbLongitud,lbPrecision,lbEstado;
	
	private LocationManager locManager;
	private LocationListener locListener;
	
	private void cargarDatos()
	{
		btActivar=(Button)findViewById(R.id.btActivar);
		btDesactivar=(Button)findViewById(R.id.btDesactivar);
		lbLatitud=(TextView)findViewById(R.id.lbPosLatitud);
		lbLongitud=(TextView)findViewById(R.id.lbPosLongitud);
		lbPrecision=(TextView)findViewById(R.id.lbPosPrecision);
		lbEstado=(TextView)findViewById(R.id.lbEstado);
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        cargarDatos();
        btActivar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				comenzar();
				
			}
		});
        btDesactivar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				locManager.removeUpdates(locListener);
				
			}
		});
    }
    private void comenzar()
    {
    	locManager=(LocationManager)getSystemService(LOCATION_SERVICE);
    	
    	Location local=locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	
    	mostrar(local);
    	
    	locListener=new LocationListener() {
			
			public void onStatusChanged(String provider, int status, Bundle extras) {
				
				lbEstado.setText("Estado: "+ status);
			}
			
			public void onProviderEnabled(String provider) {
				lbEstado.setText("Dispositivo ON");
				
			}
			
			public void onProviderDisabled(String provider) {
				lbEstado.setText("Dispositivo OFF");
				
			}
			
			public void onLocationChanged(Location location) {
				mostrar(location);
				
			}
		};
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,30000, 0, locListener);
    	
    }
    private void mostrar(Location local)
    {
    	if (local!=null)
    	{
    		lbLatitud.setText("Latitud: "+String.valueOf(local.getLatitude()));
    		lbLongitud.setText("Longitud: "+String.valueOf(local.getLongitude()));
    		lbPrecision.setText("Precision: "+ String.valueOf(local.getAccuracy()));
    	}
    	else{
    		lbLatitud.setText("Latitud: vacio");
    		lbLongitud.setText("Longitud: vacio");
    		lbPrecision.setText("Precision: vacio");
    	}
    }
}




