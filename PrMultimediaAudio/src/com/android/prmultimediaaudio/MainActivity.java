/*
 * http://developer.samsung.com/android/technical-docs/Playing-Audio-and-Video-in-Android 
 * https://developer.android.com/guide/topics/media/audio-capture.html
 * 
 */
package com.android.prmultimediaaudio;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnCaptureControl;
	private Button btnReproControl;
	private MediaRecorder mRecorder;
	private MediaPlayer mPlayer;
	private boolean isPlaying = true;
	private boolean isCapturing = true;
	private final static int MEDIA_TYPE_IMAGE = 0;
	private final static int MEDIA_TYPE_VIDEO = 1;
	private final static int MEDIA_TYPE_AUDIO = 2;
	File fAudio;

	/** 
	 * Crea un objeto File de tipo MEDIA_TYPE_IMAGE o MEDIA_TYPE_VIDEO
	 * que va a servir para añadirlo a un Intent cuya accion puede ser
	 * abrir la imagen o video (con nombre univoco).
	 * multimediaIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
	 */
	private static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_MUSIC), "PrMultimediaAudio");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("PrMultimediaAudio", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator +
					"IMG_"+ timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator +
					"VID_"+ timeStamp + ".mp4");
		} else if (type == MEDIA_TYPE_AUDIO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator +
					"AUD_"+ timeStamp + ".3gp");
		} else {
			return null;
		}

		return mediaFile;
	}

	/*
	 * Devuelve un objeto Uri a partir de otro de tipo File.
	 */
	private static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		(btnReproControl = (Button) this.findViewById(R.id.btnReproControl)).setOnClickListener(this);
		(btnCaptureControl = (Button) this.findViewById(R.id.btnCaptureControl)).setOnClickListener(this);;

	}

	/*
	 * Comenzar/parar la captura
	 */
	private void onCapture(boolean bool) {
		if (bool) {
			//Log.d("CAPTURAAUDIO", "COMENZAR A CAPTURAR" + faudio.getPath());
			fAudio = getOutputMediaFile(MEDIA_TYPE_AUDIO);

			mRecorder = new MediaRecorder();
			mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mRecorder.setOutputFile(fAudio.getAbsolutePath());
			mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

			try {
				mRecorder.prepare();
				mRecorder.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//Log.d("CAPTURAAUDIO", "PARAR LA CAPTURA");
			mRecorder.stop();
			mRecorder.release();
			mRecorder = null;
		}
	}

	private void onPlay(boolean bool) {
		if (bool) {
			if (fAudio != null) {
				mPlayer = MediaPlayer.create(this.getApplicationContext(), Uri.parse(fAudio.getAbsolutePath()));

				// al terminar la reproduccion cambiar el boton 
				// de nuevo de "stop" a "play".
				mPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						mPlayer.release();
						mPlayer = null;
						btnReproControl.setText("Play");
					}
				});
				mPlayer.start();

				// Tambien podriamos reproducir mediante Intents de accion
				//				Intent playIntent = new Intent();
				//				playIntent.setAction(android.content.Intent.ACTION_VIEW);
				//				playIntent.setDataAndType(Uri.parse(fAudio.getAbsolutePath()), "audio/*");
				//				this.startActivity(playIntent);
			}
		} else {
			if (mPlayer != null) {
				mPlayer.release();
				mPlayer = null;
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCaptureControl:
			// comenzar/parar la captura
			onCapture(isCapturing);
			// cambiar nombre del boton al iniciar/parar la captura
			btnCaptureControl.setText((isCapturing ? "Parar Captura" : "Comenzar Captura"));
			isCapturing = !isCapturing;
			break;
		case R.id.btnReproControl:
			onPlay(isPlaying);
			btnReproControl.setText((isPlaying ? "Stop" : "Play"));
			isPlaying = !isPlaying;
			break;
		}
	}

}
