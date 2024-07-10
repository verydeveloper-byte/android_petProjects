package libreriaDom;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import noticiasXml.Noticias;

public class RssParserDom {
 
	private URL rssUrl;
	
	public RssParserDom(String url)
	{
		try {
			rssUrl=new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public InputStream getInputStream()
	{
		try {
			return rssUrl.openConnection().getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	public List<Noticias> parse()
	{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		List<Noticias> noticias=new ArrayList<Noticias>();
		try{
			DocumentBuilder builder=factory.newDocumentBuilder();
			
			Document dom =builder.parse(getInputStream());
			
			Element root=(Element)dom.getDocumentElement();
			
			NodeList items=root.getElementsByTagName("item");
			 
			for (int i=0;i<items.getLength();i++)
			{
				Noticias noticia=new Noticias();
				Node item=items.item(i);
				NodeList datosNoticia=item.getChildNodes();
				
				for (int j=0;j<datosNoticia.getLength();j++)
				{
					Node dato=datosNoticia.item(j);
					String etiqueta=dato.getNodeName();
					if (etiqueta.equals("title"))
					{
						String texto=obtenerTexto(dato);
						noticia.setTitulo(texto);
					}
					else if(etiqueta.equals("description"))
					{
						String texto=obtenerTexto(dato);
						noticia.setDescripcion(texto);
					}
					else if(etiqueta.equals("pubDate"))
					{
						noticia.setFecha(dato.getFirstChild().getNodeValue());
					}
					else if(etiqueta.equals("category"))
					{
						noticia.setCategoria(dato.getFirstChild().getNodeValue());
					}
				}
				noticias.add(noticia);
			}
		}
		catch (Exception e)
		{
			
		}
		return noticias;
	}
	private String obtenerTexto(Node dato)
	{
		StringBuilder texto=new StringBuilder();
		NodeList fragmentos=dato.getChildNodes();
		for (int i=0;i<fragmentos.getLength();i++)
		{
			texto.append(fragmentos.item(i).getNodeValue());
		}
		return texto.toString();
	}
}
