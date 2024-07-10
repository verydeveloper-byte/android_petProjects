package com.android.printent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginFormActivity extends Activity implements OnClickListener {

	TextView tvLogin;
	EditText etLogin;
	Button btBackToMenu, btAceptar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		cargar();
	}

	private void cargar() {
		tvLogin = (TextView) findViewById(R.id.tvLogin);
		etLogin = (EditText) findViewById(R.id.etLogin);
		btBackToMenu = (Button) findViewById(R.id.btBackToMenuFromLogin);
		btAceptar = (Button) findViewById(R.id.btAceptar);

		btBackToMenu.setOnClickListener(this);
		btAceptar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// devolver el valor de edittext del login
		case R.id.btAceptar:
			setResult(Activity.RESULT_OK, this.getIntent().putExtra("usuario", etLogin.getText().toString()));
			finish();
			break;
			// volver atras
		case R.id.btBackToMenuFromLogin:
			finish();
			break;
		}
	}

}
