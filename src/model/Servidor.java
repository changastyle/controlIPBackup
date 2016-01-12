package model;

import java.util.ArrayList;

public class Servidor extends Thread
{
    private String nombre;
    private ArrayList<Equipo> arrEquipos;

    public Servidor(String nombre)
    {
        this.nombre = nombre;
        this.arrEquipos = new ArrayList<Equipo> ();
    }
    
    //<editor-fold desc="GYS:">

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public ArrayList<Equipo> getArrEquipos()
    {
        return arrEquipos;
    }

    public void setArrEquipos(ArrayList<Equipo> arrEquipos)
    {
        this.arrEquipos = arrEquipos;
    }
    //</editor-fold>

    public void addEquipo (Equipo e )
    {
        this.arrEquipos.add(e);
    }
    public void removeEquipo(Equipo e )
    {
        this.arrEquipos.remove(e);
    }
    
    public String imprimirArrEquipos()
    {
        String salida = "\n[\n";
        
        for(Equipo e : arrEquipos)
        {
            salida += "     " + e.toString() + "\n";
        }
        
        salida+= "]";
        
        return salida;
    }

    @Override
    public void run()
    {
        super.run(); 
        
        String salida = "";
        for(Equipo equipo : this.getArrEquipos())
        {
            //salida = this.getNombre() +" | ";
                
            if(equipo.getDireccionIPPrincipal() != null)
            {
                //PRINCIPAL:
                equipo.getDireccionIPPrincipal().setActivo(red.Network.pingear( equipo.getDireccionIPPrincipal().getDireccion()) );
                //salida += equipo.getDireccionIPPrincipal();
                equipo.setDireccionIPPrincipal(equipo.getDireccionIPPrincipal());

                //BACKUP:
                if(equipo.getDireccionIPBackup() != null)
                {
                    equipo.getDireccionIPBackup().setActivo( red.Network.pingear( equipo.getDireccionIPBackup().getDireccion()) );
                    //salida += " | " + equipo.getDireccionIPBackup();
                    equipo.setDireccionIPBackup(equipo.getDireccionIPBackup());
                }
                
            }
        }
        //System.out.println(salida);
            
        
    }
    
    
    @Override
    public String toString()
    {
        return "Servidor{" + "nombre=" + nombre + ", arrEquipos=" + imprimirArrEquipos() + '}';
    }
    
    
    
}
