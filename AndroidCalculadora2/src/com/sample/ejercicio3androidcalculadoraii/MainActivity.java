package com.sample.ejercicio3androidcalculadoraii;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	//Declaramos las variables que vamos a utilizar.
	private EditText etNumero1;
	private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btC,
			btmas, btmenos, btpor, btentre, btigual, btpunto;
	private String operacion, num1 = "", num2 = "";
	private int indicador, decimal, fin, oper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Llamamos al método cargar.
		cargar();
	}

	private void cargar() {
		//Relacionamos el elemento java y xml del editText relativo al número1 y lo dejamos inactivo.
		etNumero1 = (EditText) findViewById(R.id.etNumero1);
		etNumero1.setEnabled(false);
		//Relacionamos el elemento del botón en java y el xml y le aplicamos un escuchador.
		bt1 = (Button) findViewById(R.id.bt1);
		bt1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Enviamos al método numeros lo que debe mostrarse en el editText 
				String num = "1";
				numeros(num);
			}
		});

		bt2 = (Button) findViewById(R.id.bt2);
		bt2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "2";
				numeros(num);
			}
		});

		bt3 = (Button) findViewById(R.id.bt3);
		bt3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "3";
				numeros(num);
			}
		});

		bt4 = (Button) findViewById(R.id.bt4);
		bt4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "4";
				numeros(num);
			}
		});

		bt5 = (Button) findViewById(R.id.bt5);
		bt5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "5";
				numeros(num);
			}
		});

		bt6 = (Button) findViewById(R.id.bt6);
		bt6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "6";
				numeros(num);
			}
		});

		bt7 = (Button) findViewById(R.id.bt7);
		bt7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "7";
				numeros(num);
			}
		});

		bt8 = (Button) findViewById(R.id.bt8);
		bt8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "8";
				numeros(num);
			}
		});

		bt9 = (Button) findViewById(R.id.bt9);
		bt9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "9";
				numeros(num);
			}
		});

		bt0 = (Button) findViewById(R.id.bt0);
		bt0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num = "0";
				numeros(num);
			}
		});

		btmas = (Button) findViewById(R.id.btmas);
		btmas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Enviamos el signo de la operación para establecer 
				//el tipo de operación que vamos a realizar
				setoperacion("+");
			}
		});
		btmenos = (Button) findViewById(R.id.btmenos);
		btmenos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				setoperacion("-");

			}
		});

		btpor = (Button) findViewById(R.id.btpor);
		btpor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				setoperacion("*");

			}
		});

		btentre = (Button) findViewById(R.id.btentre);
		btentre.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				setoperacion("/");

			}
		});

		btigual = (Button) findViewById(R.id.btigual);
		btigual.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Comprobamos que existan num1 y num2 como operandos
				//y que hayamos elegido un tipo de operación:
				if (!(num1.equals("")) && !(num2.equals(""))
						&& !(operacion.equals(""))) {
					double numero1, numero2, total;
					numero1 = Double.parseDouble(num1);
					numero2 = Double.parseDouble(num2);
					//Realizamos la operación
					total = operar(numero1, numero2);
					//Mostramos en el editText el resultado de la operación.
					etNumero1.setText(String.valueOf(total));
					
					//Dejamos a cero las variables después de terminar la operción
					//tras haber pulsado el botón del signo igual.
					decimal = 0;
					indicador = 0;
					oper = 0;
					//Guardamos el total en num1 por si decidimos realizar alguna operación 
					//seguidamente.
					num1 = String.valueOf(total);
					//Dejamos libre la variable num2.
					num2 = "";

				}

			}
		});

		btpunto = (Button) findViewById(R.id.btpunto);
		btpunto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String num = ".";
				if (decimal == 0
						&& !(etNumero1.getText().toString().equals(""))) {
					numeros(num);
					decimal = 1;
				}

			}
		});

		btC = (Button) findViewById(R.id.btC);
		btC.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				etNumero1.setText("");
				decimal = 0;
				indicador = 0;
				oper = 0;
				num1 = "";
				num2 = "";
				fin = 0;
			}
		});

	}
	//Creamos un método numeros que puestra en el editText los 
	//números de las operaciones que vamos pulsando con los botones.
	//La variable fin hace referncia al final de la operación.
	private void numeros(String num) {

		if (fin == 1) {
			etNumero1.setText(num);
			num1 = num;
			fin = 0;
			decimal = 0;
			indicador = 0;
			oper = 0;
			num2 = "";
		} else if (fin == 0) {
			//Indicador indica cuántos números existen en la operación
			//que estamos realizando
			if (indicador == 0) {
				if (num1.equals("")) {
					num1 = num;
					etNumero1.setText(num1);
				} else {
					num1 = etNumero1.getText().toString() + num;
					etNumero1.setText(num1);
				}

			} else if (indicador == 1) {
				if (num2.equals("")) {
					num2 = num;
					etNumero1.setText(num2);
				} else {
					num2 = num2 + num;
					etNumero1.setText(num2);
				}
			}
		}

	}
	//Función que devuelve el resultado:
	private double operar(double numero1, double numero2) {
		double res = 0;
		if (operacion.equals("+")) {
			res = numero1 + numero2;
		} else if (operacion.equals("-")) {
			res = numero1 - numero2;
		} else if (operacion.equals("*")) {
			res = numero1 * numero2;
		} else if (operacion.equals("/")) {
			res = numero1 / numero2;
		}
		return res;
	}
	//procedimiento que establece el tipo de operación: suma, resta, división o multiplicación.
	private void setoperacion(String op) {
		if (oper == 0) {
			if (op.equals("+")) {
				operacion = "+";
			} else if (op.equals("-")) {
				operacion = "-";
			} else if (op.equals("*")) {
				operacion = "*";
			} else if (op.equals("/")) {
				operacion = "/";
			}
			//En esta aprte se van acumulando las operaciones en num1, por si hacemos varias seguidas:
			//Dejamos libre la variable num2 para poder seguir realizando operaciones.
		} else if (oper == 1) {
			double numero1, numero2, total;
			if (op.equals("+")) {
				numero1 = Double.parseDouble(num1);
				numero2 = Double.parseDouble(num2);
				total = operar(numero1, numero2);
				num1 = String.valueOf(total);
				num2 = "";
				operacion = "+";
			} else if (op.equals("-")) {
				numero1 = Double.parseDouble(num1);
				numero2 = Double.parseDouble(num2);
				total = operar(numero1, numero2);
				num1 = String.valueOf(total);
				num2 = "";
				operacion = "-";
			} else if (op.equals("*")) {
				numero1 = Double.parseDouble(num1);
				numero2 = Double.parseDouble(num2);
				total = operar(numero1, numero2);
				num1 = String.valueOf(total);
				num2 = "";
				operacion = "*";
			} else if (op.equals("/")) {
				numero1 = Double.parseDouble(num1);
				numero2 = Double.parseDouble(num2);
				total = operar(numero1, numero2);
				num1 = String.valueOf(total);
				num2 = "";
				operacion = "/";
			}

		}
		//oper: indica si hay o no operación realizada
		//indicador: indica cuántos opernados llevamos
		//decimal: indica si el número es decimal o no
		oper = 1;
		indicador = 1;
		decimal = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
