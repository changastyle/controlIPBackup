package model;

public class DireccionIP
{
    private String direccion;
    private boolean activo;

    public DireccionIP(String direccion)
    {
        this.direccion = direccion;
        this.activo = false;
    }
    
    //<editor-fold desc="GYS:">

    public String getDireccion()
    {
        return direccion;
    }

    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    public boolean isActivo()
    {
        return activo;
    }

    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }
    //</editor-fold>

    @Override
    public String toString()
    {
        return "{" + "direccion:" + direccion + ", activo:" + activo + '}';
    }
}
