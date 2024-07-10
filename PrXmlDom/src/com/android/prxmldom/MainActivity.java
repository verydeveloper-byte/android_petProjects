package com.android.prxmldom;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.prxmldom.model.Noticia;
import com.android.prxmldom.rss.RssParserDom;

public class MainActivity extends Activity {

	private Button btnCargar;
	private TextView txtResultado;

	private List<Noticia> noticias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnCargar = (Button)findViewById(R.id.btnCargar);
		txtResultado = (TextView)findViewById(R.id.txtResultado);

		btnCargar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				CargarXmlTask tarea = new CargarXmlTask();
				tarea.execute("http://www.europapress.es/rss/rss.aspx");
			}
		});
	}

	//Tarea Asíncrona para cargar un XML en segundo plano
	private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

		protected Boolean doInBackground(String... params) {

			RssParserDom domparser =
					new RssParserDom(params[0]);

			noticias = domparser.parse();

			return true;
		}

		protected void onPostExecute(Boolean result) {

			//Tratamos la lista de noticias
			//Por ejemplo: escribimos los títulos en pantalla
			txtResultado.setText("");
			for(int i=0; i<noticias.size(); i++)
			{
				txtResultado.setText(
						txtResultado.getText().toString() +
						System.getProperty("line.separator") +
						noticias.get(i).getTitulo());
			}
		}
	}
}
