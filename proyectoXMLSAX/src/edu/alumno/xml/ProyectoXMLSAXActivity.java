package edu.alumno.xml;

import java.util.List;

import noticiasXml.Noticias;

import libreriaDom.RssParserDom;
import libreriaRSS.RssParseSax;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ProyectoXMLSAXActivity extends Activity {
    /** Called when the activity is first created. */
	private AdaptadorLista adapLista;
	private ListView listado;
	private Noticias[] arrayNoticias;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //RssParseSax saxparser=new RssParseSax("http://www.europapress.es/rss/rss.aspx");
        RssParserDom domparser=new RssParserDom("http://www.europapress.es/rss/rss.aspx");
        
        //List<Noticias> noticias=saxparser.parse();
        List<Noticias>noticias=domparser.parse();
        
        arrayNoticias=new Noticias[noticias.size()];
        arrayNoticias=noticias.toArray(arrayNoticias);
        
        adapLista=new AdaptadorLista(this,arrayNoticias);
        
        listado=(ListView)findViewById(R.id.lvNoticias);
        listado.setAdapter(adapLista);
        
    }
}