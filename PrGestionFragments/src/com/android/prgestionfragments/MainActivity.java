package com.android.prgestionfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements OnClickListener {

	Fragment frag1;
	Fragment frag2;
	Button btCambiaFragment;
	private boolean fChanged = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_container);

		btCambiaFragment = (Button) findViewById(R.id.btCambiaFragment);
		btCambiaFragment.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		frag1 = new Fragment1();
		frag2 = new Fragment2();

		FragmentManager fManager = this.getSupportFragmentManager();
		FragmentTransaction fTransition = fManager.beginTransaction();
		fTransition.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		
		if (fChanged) 
			fTransition.add(R.id.FragmentContainer, frag1);
		else
			fTransition.add(R.id.FragmentContainer, frag2);

		/* añadir transaccion al backstack para que
		 * se pueda deshacer con el Atras
		 */
		fTransition.addToBackStack(null);
		/*
		 * confirmar la transaccion
		 */
		fTransition.commit();
		fChanged = (fChanged) ? false : true;
	}
}
