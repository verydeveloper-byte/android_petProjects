package alumnos.edu;

public class Menu {
    private String titulo;
    private String descripcion;
    
    public Menu(String vtitulo,String vdescripcion)
    {
    	titulo=vtitulo;
    	descripcion=vdescripcion;
    }
    public String getTitulo()
    {
    	return titulo;
    }
    public String getDescripcion()
    {
    	return descripcion;
    }
}
