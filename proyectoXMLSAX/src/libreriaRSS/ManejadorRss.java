package libreriaRSS;

import java.util.ArrayList;
import java.util.List;
import noticiasXml.Noticias;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorRss extends DefaultHandler {
      private List<Noticias> noticias;
      private Noticias noticiaActual;
      private StringBuilder sbTexto;
      
      public List<Noticias> getNoticias()
      {
    	  return noticias;
      }
      @Override
      public void characters(char [] ch,int start,int length) throws SAXException
      {
    	  super.characters(ch, start, length);
    	  
    	  if (noticiaActual!=null)
    	  {
    		  sbTexto.append(ch,start,length);
    	  }
      }
      
      @Override
      public void startDocument() throws SAXException
      {
    	  super.startDocument();
    	  noticias=new ArrayList<Noticias>();
    	  sbTexto=new StringBuilder();
      }
      @Override
      public void startElement(String uri,String localName,String name, Attributes atributos) throws SAXException
      {
    	  super.startElement(uri, localName, name, atributos);
    	  
    	  if (localName.equals("item"))
    	  {
    		  noticiaActual=new Noticias();
    	  }
      }
      @Override
      public void endElement(String uri,String localName,String name) throws SAXException
      {
    	  super.endElement(uri, localName, name);
    	  if (noticiaActual!=null)
    	  {
    		  if (localName.equals("title"))
    		  {
    			  noticiaActual.setTitulo(sbTexto.toString());
    		  }
    		  else if (localName.equals("description"))
    		  {
    			  noticiaActual.setDescripcion(sbTexto.toString());
    		  }
    		  else if(localName.equals("pubDate"))
    		  {
    			  noticiaActual.setFecha(sbTexto.toString());
    		  }
    		  else if (localName.equals("category"))
    		  {
    			  noticiaActual.setCategoria(sbTexto.toString());
    		  }
    		  else if(localName.equals("item"))
    		  {
    			  noticias.add(noticiaActual);
    		  }
    		  sbTexto.setLength(0);
    	  }
      }
}
