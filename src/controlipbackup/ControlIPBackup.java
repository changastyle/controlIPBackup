package controlipbackup;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import model.Equipo;
import org.apache.commons.net.telnet.TelnetClient;
import red.Telnet;

public class ControlIPBackup
{
    public static void main(String[] args)
    {

        
        /*ArrayList<String> interfaces = red.Network.procesarIFConfig(Telnet.fast("192.168.5.200", 23, "root", "tecacc", "ifconfig"));
        
        System.out.println("\ninterfaces:");
        for(String interf : interfaces)
        {
            System.out.println(interf);
        }*/
        
        for(model.Servidor servidor : controller.Controller.armarServers())
        {
            try
            {
                System.out.println("Cargando Errores en el Servidor "+ servidor.getNombre() + "..");
                servidor.start();
                servidor.join();
                //System.out.println(servidor);
                
                for(Equipo equipo :servidor.getArrEquipos())
                {
                    if(equipo.getDireccionIPPrincipal() != null && equipo.getDireccionIPBackup() != null)
                    {
                        if(equipo.getDireccionIPPrincipal().isActivo() && !(equipo.getDireccionIPBackup().isActivo()))
                        {
                            if(!esGPRS(equipo.getDireccionIPBackup().getDireccion()))
                            {
                                System.out.println(equipo + " no funca el bckup");
                            }
                            
                            
                        }
                    }
                }
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
        //red.Network.telnet("192.168.5.200");
    }

    private static boolean esGPRS(String direccion)
    {
        boolean esGPRS = false;
        
        if(direccion.trim().startsWith("172.23") || direccion.trim().startsWith("172.24") ||  direccion.trim().startsWith("172.26"))
        {
            esGPRS = true;
        }
        
        return esGPRS;
    }
}
