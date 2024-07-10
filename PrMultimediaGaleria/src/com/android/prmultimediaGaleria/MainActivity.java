/*
 * URLs de interes
 * http://www.androidhive.info/2013/09/android-working-with-camera-api/
 * https://developer.android.com/training/sharing/send.html
 * https://developer.android.com/guide/components/intents-common.html
 * http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample/
 * https://developer.android.com/training/camera/photobasics.html
 */
package com.android.prmultimediaGaleria;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private final int GALERIA_CODE = 0;
	private final int CAMERA_CODE = 1;
	// variable global para no tener que buscar
	// la foto en el ActivityResult.
	private File takedPhoto;
	private Bitmap bitmap;
	private ImageView ivImg;
	private Button btImgGaleria;
	private Button btTomarFoto;
	private Button btInetPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ivImg = (ImageView) this.findViewById(R.id.ivImg);
		btImgGaleria = (Button) this.findViewById(R.id.btImgGaleria);
		btTomarFoto = (Button) this.findViewById(R.id.btTomarFoto);
		btInetPhoto = (Button) this.findViewById(R.id.btInetPhoto);
		//Log.d("AAAAAAAAAAAAA", "aaa " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));

		btImgGaleria.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent galeriaIntent = new Intent();
				/*
				 * La accion de este Intent no es una Activity definida explicitamente
				 * (new Intent(context, segundaActivity.class), sino que es una accion .setAction()
				 * La intent esta enviando datos de una actividad a otra (de otro proceso o app),
				 * para enviar datos a otra Activity hay que especificar el dato (ACTION_PICK)
				 * y el tipo (setType) (en otras palabras, estamos delegando una accion en otra aplicacion).
				 * El sistema identificara las Activitys compatibles para la accion ACTION_PICK
				 * que tengan un filtro para ACTION_PICK y las mostrara al usuario (dialogo chooser),
				 * o comenzara la Activity si solo encuentra una sola opcion.
				 * Normalmente la Accion y la URI se especifican en la declaracion:
				 * new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				 * ^-- tomar una imagen usando el directorio dado como URI EXTERNAL_CONTENT_URI.
				 * https://developer.android.com/training/sharing/send.html
				 */
				/*
				 * ACTION_PICK solicita al usuario que seleccione una imagen de la galeria.
				 * En el intent de startActivityForResult() se recibe una URI
				 * que apunta a la imagen; (pueder ser una URI de tipo http: file: o content: 
				 * en el caso de otras acciones como ACTION_GET_CONTENT).
				 * https://developer.android.com/guide/components/intents-common.html
				 */
				galeriaIntent.setAction(Intent.ACTION_PICK);
				/*
				 * android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI ->
				 * content://media/external/images/media
				 * una URI con la que enviamos la accion ACTION_PICK del intent a otra actividad.
				 */
				galeriaIntent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				/*
				 * con el MIME restringimos el tipo de ficheros que la galeria va a mostrar.
				 * Aunque con ACTION_PICK no es necesario (con ACTION_GET_CONTENT si).
				 */
				galeriaIntent.setType("image/*");
				/*
				 * Para restringir que el archivo elegido pueda ser accedido por un
				 * Content Provider (content: URI) o por un descriptor de fichero openFileDescriptor()
				 * restringimos la categoria a CATEGORY_OPENABLE al intent.
				 */
				//galeriaIntent.addCategory(Intent.CATEGORY_OPENABLE);
				/*
				 * Como se ha dicho arriba, si hay varias actividades que casan con la accion ACTION_PICK
				 * entonces el sistema muestra un dialogo para que el usuario elija una de ellas (chooser).
				 * Sin embargo si llamamos a Intent.createChooser pasandole el intent, nos devolvera un intent
				 * que siempre llamara al dialogo chooser aun cuando solo exista una opcion
				 */
				startActivityForResult(
						Intent.createChooser(galeriaIntent, "Select File"),
						GALERIA_CODE);
				/*
				 * Para incluir un selector (chooser) para dar a elegir tambien entre tomar una 
				 * foto de la camara o elegir una imagen de la galeria creamos un Intent chooser
				 * de galeria y le añadimos un extra con el resto de intents (intent tomar foto)
				 * en un EXTRA_INITIAL_INTENTS.
				 */
				//				Intent chooserIntent = Intent.createChooser(galeriaIntent, "galeria o foto ??");
				//				Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//				chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { takePhotoIntent });
				//				startActivityForResult(chooserIntent, GALERIA_CODE);
			}
		});

		btTomarFoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * Para tomar una foto creamos un Intent que delega la accion de
				 * capturar una foto de la camara a otra actividad externa.
				 * Cuando se haga la foto y el foco vuelva de nuevo a la Activity,
				 * tendremos la imagen en el onActivityResult()
				 * https://developer.android.com/training/camera/photobasics.html
				 */
				Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				/*
				 * Si queremos tomar la imagen completa y no solo la miniatura, tenemos
				 * que crear un obj File con la ruta donde vamos a guardar la foto 
				 * getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)->/storage/emulated/0/Pictures
				 * y decirle al Intent que guarde un extra con la ruta de la foto 
				 * mediante MediaStore.EXTRA_OUTPUT.
				 * Esto se podria haber hecho directamente con new Intent(ACTION, Uri)
				 */
				takedPhoto = new File(
						Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
						"tempPhoto.jpg");
				takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(takedPhoto));
				/*
				 * resolveActivity() devuelve la primera Activity que puede 
				 * manejar el Intent.
				 */
				if (takePhotoIntent.resolveActivity(getPackageManager()) != null)
					startActivityForResult(takePhotoIntent, CAMERA_CODE);
			}
		});

		/*
		 * Esto no funciona pq android no permite relizar
		 * operaciones de red en el thread principal...
		 * la solucion (creo) es asynctask
		 */
		//		btInetPhoto.setOnClickListener(new OnClickListener() {
		// TODO ver imagenes desde una URL de internet.
		//			@Override
		//			public void onClick(View v) {
		//				try {
		//					URL urlFoto = new URL("http://media.fukung.net/imgs/35b39e8395cb53a3f31ea8fe441a74ba.jpg");
		//					BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		//
		//					Bitmap bmUrl = BitmapFactory.decodeStream(urlFoto.openStream(), null, bmOptions);
		//					ivImg.setImageBitmap(bmUrl);
		//				} catch (MalformedURLException e) {
		//					e.printStackTrace();
		//				} catch (IOException e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// destruir la imagen temporal de la camara
		if (takedPhoto != null && takedPhoto.exists())
			takedPhoto.delete();
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

		if (requestCode == GALERIA_CODE && resultCode == Activity.RESULT_OK) {
			try {
				// reciclaje de bitmaps
				if (bitmap != null) {
					bitmap.recycle();
				}
				/* 
				 * getData() devuelve una URI -> content://com.android.providers.media.documents/document/image%3A11711
				 * esa URI la procesamos con getContentResolver() para obtener un obj InputStream mediante 
				 * el metodo openInputStream().
				 * Finalmente obtenemos el obj Bitmap haciendo un BitmapFactory.decodeStream() sobre el stream.
				 */
				/*
				 * Un Content Resolver proporciona un objeto ContentResolver que proporciona acceso a los Content Providers
				 * de otras aplicaciones.
				 */
				InputStream stream = getContentResolver().openInputStream(data.getData());
				bitmap = BitmapFactory.decodeStream(stream);
				stream.close();
				ivImg.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {
			/*
			 * La camara devuelve un thumbnail en un extra con la key "data"
			 * (esto funciona si no le ponemos un objeto File al crear el Intent)
			 */
			//Bundle extras = data.getExtras();
			//Bitmap bitmapMiniatura = (Bitmap) extras.get("data");


			BitmapFactory.Options bmOptions = new BitmapFactory.Options();

			bmOptions.inSampleSize = 3;

			/*
			 * Tomamos la foto del obj File que hemos asignado anteriormente 
			 * y la tratamos con Bitmap y/o Bitmapfactory.
			 */
			if (takedPhoto != null) {
				Bitmap bm = BitmapFactory.decodeFile(takedPhoto.getAbsolutePath(), bmOptions);
				// redimensiona la imagen tomada a la resolucion deseada
				bm = Bitmap.createScaledBitmap(bm, 500, 500, true);
				ivImg.setImageBitmap(bm);

				//añadir la imagen a la galeria
				galleryAddPic(takedPhoto);
			}
		}
	}

}
