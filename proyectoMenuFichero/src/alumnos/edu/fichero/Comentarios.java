package alumnos.edu.fichero;

public class Comentarios {
    private String usuario;
    private String comentario;
    
    public Comentarios(String vusuario,String vcomentario)
    {
    	usuario=vusuario;
    	comentario=vcomentario;
    }
    public String getUsuario()
    {
    	return usuario;
    }
    public String getComentario()
    {
    	return comentario;
    }
}
