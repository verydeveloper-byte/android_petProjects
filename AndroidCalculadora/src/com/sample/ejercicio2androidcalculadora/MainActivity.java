package com.sample.ejercicio2androidcalculadora;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	// Inicializamos las variables que vamos a utilizar:
	private EditText etNum1, etNum2, etRes;
	private Button btSumar, btRestar, btMultiplicar, btDividir, btReset;
	private double num1, num2, res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Llamamos al método cargar:
		cargar();
	}

	private void cargar() {
		// TODO Auto-generated method stub
		// Relacionamos las variables que hemos creado en java con los del
		// archivo xml de la parte gráfica:
		etNum1 = (EditText) findViewById(R.id.etNum1);
		etNum2 = (EditText) findViewById(R.id.etNum2);
		etRes = (EditText) findViewById(R.id.etRes);

		btSumar = (Button) findViewById(R.id.btSumar);
		btRestar = (Button) findViewById(R.id.btRestar);
		btMultiplicar = (Button) findViewById(R.id.btMultiplicar);
		btDividir = (Button) findViewById(R.id.btDividir);
		btReset = (Button) findViewById(R.id.btReset);

		// Aplicamos los escuchadores a los botones:
		// Vamos a incluir los eventos en esta misma clase(this)
		// pero en un método a parte onClick.
		btSumar.setOnClickListener(this);
		btRestar.setOnClickListener(this);
		btMultiplicar.setOnClickListener(this);
		btDividir.setOnClickListener(this);
		btReset.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		try {
			// Para borrar los datos:
			if (arg0.getId() == btReset.getId()) {
				etNum1.setText("");
				etNum2.setText("");
				etRes.setText("");
			}
			/*
			 * Primero comprobamos que los campos de los números solicitados no
			 * están vacíos
			 */
			if (!(etNum1.getText().toString().equals("") && etNum2.getText()
					.toString().equals(""))) {
				// Metemos en las variables los valores de los campos editables:
				num1 = Double.parseDouble(etNum1.getText().toString());
				num2 = Double.parseDouble(etNum2.getText().toString());
				// Comparamos la id del elemento pulsado con la de los botones
				// que tenemos y luego realizamos la operación correspondiente y
				// mostramos el resultado

				// Para la suma:
				if (arg0.getId() == btSumar.getId()) {
					res = num1 + num2;
					etRes.setText(String.valueOf(res));
				}
				// Para la resta:
				else if (arg0.getId() == btRestar.getId()) {
					res = num1 - num2;
					etRes.setText(String.valueOf(res));
				}
				// Para la multiplicación:
				else if (arg0.getId() == btMultiplicar.getId()) {
					res = num1 * num2;
					etRes.setText(String.valueOf(res));
				}
				// Para la división:
				else if (arg0.getId() == btDividir.getId()) {
					res = num1 / num2;
					etRes.setText(String.valueOf(res));
				}
			}
		} catch (Exception ex) {
		}
	}
}
