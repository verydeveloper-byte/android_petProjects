package libreriaRSS;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import noticiasXml.Noticias;

public class RssParseSax {
	private URL rssUrl;
	
	public RssParseSax(String url) 
	{
		try {
			this.rssUrl=new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private InputStream getInputStream()
	{
		try {
			return rssUrl.openConnection().getInputStream();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	public List<Noticias> parse()
	{
		SAXParserFactory factory=SAXParserFactory.newInstance();
		try{
			ManejadorRss manejador=new ManejadorRss();
			SAXParser parser=factory.newSAXParser();
			parser.parse(getInputStream(),manejador);
			return manejador.getNoticias();
		}
		catch (Exception e)
		{
			throw new RuntimeException();
		}
	}
	
}
