/*
 * https://developer.android.com/guide/topics/media/mediaplayer.html
 * https://developer.android.com/guide/topics/media/audio-capture.html
 * https://developer.android.com/reference/android/media/MediaPlayer.html
 * http://developer.samsung.com/android/technical-docs/Playing-Audio-and-Video-in-Android
 */
package com.android.prmultimediavideo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private Button btCapturaVideo;
	private VideoView vvVideo;
	private File videoFile;
	private final static int MEDIA_TYPE_IMAGE = 0;
	private final static int MEDIA_TYPE_VIDEO = 1;
	private final int VIDEO_REQUEST = 0;

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
				Environment.DIRECTORY_MOVIES), "PrMultimediaVideo");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("PrMultimediaVideo", "failed to create directory");
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

	/*
	 * Borrar video al terminar la app
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (videoFile != null && videoFile.exists()) 
			videoFile.delete();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btCapturaVideo = (Button) this.findViewById(R.id.btCapturaVideo);
		vvVideo = (VideoView) this.findViewById(R.id.vvVideo);

		btCapturaVideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

				// Obtenemos la Uri del objeto File del video
				videoFile = getOutputMediaFile(MEDIA_TYPE_VIDEO);
				takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));

				// hay una actividad que puede manejar el intent ?
				if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
					startActivityForResult(takeVideoIntent, VIDEO_REQUEST);
				}
			}
		});
	}

	/*
	 * Añade una imagen a la galeria usando el media scanner
	 * del Media Provider del sistema (añade la foto recien
	 * creada a la BD del Media Provider haciendola visible a 
	 * otras apps como la galeria, etc)
	 */
	private void galleryAddPic(File f) {
		if (f != null) {
			Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			Uri contentUri = Uri.fromFile(f);
			mediaScanIntent.setData(contentUri);
			this.sendBroadcast(mediaScanIntent);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == VIDEO_REQUEST && resultCode == Activity.RESULT_OK) {
			Uri videoUri = data.getData();
			vvVideo.setVideoURI(videoUri);
			vvVideo.start();

			/*
			 * Abre el video capturado con una de las actividades que sean
			 * capaces de manejar la accion ACTION_VIEW para el mime 'video/*'
			 * (o dar a elegir mediante un chooser)
			 */
			//			Intent intent = new Intent();
			//			intent.setAction(Intent.ACTION_VIEW);
			//			intent.setDataAndType(videoUri, "video/*");
			//			startActivity(intent);

			// añadir a la galeria
			//galleryAddPic(new File(videoUri.getPath()));
		}
	}

}
