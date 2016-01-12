package model;

import java.util.ArrayList;

public class Equipo
{
    private String hostname;
    private DireccionIP direccionIPPrincipal;
    private DireccionIP direccionIPBackup;

    public Equipo(String hostname)
    {
        this.hostname = hostname;
        this.direccionIPPrincipal = null;
        this.direccionIPBackup = null;
    }
    
    //<editor-fold desc="GYS:">

    public String getHostname()
    {
        return hostname;
    }

    public void setHostname(String hostname)
    {
        this.hostname = hostname;
    }

    public DireccionIP getDireccionIPPrincipal()
    {
        return direccionIPPrincipal;
    }

    public void setDireccionIPPrincipal(DireccionIP direccionIPPrincipal)
    {
        this.direccionIPPrincipal = direccionIPPrincipal;
    }

    public DireccionIP getDireccionIPBackup()
    {
        return direccionIPBackup;
    }

    public void setDireccionIPBackup(DireccionIP direccionIPBackup)
    {
        this.direccionIPBackup = direccionIPBackup;
    }




    
    //</editor-fold>


    /*private String imprimirArrDireccionesIP()
    {
        String salida = "\n     [\n";
        
        for(DireccionIP direccion : arrDireccionesIPBackup)
        {
            salida += "         " + direccion.toString() + "\n";
        }
        
        salida += "     ]";
        
        return salida;
    }
    */

    @Override
    public String toString()
    {
        return "Equipo{" + "hostname=" + hostname + ", principal=" + direccionIPPrincipal + ", backup=" + direccionIPBackup + '}';
    }
    
    
}
