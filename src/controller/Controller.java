package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import model.DireccionIP;
import model.Equipo;
import model.Servidor;

public class Controller
{
    private static String ruta = "C:\\control ip de backup\\archivo.txt";
    private static ArrayList<model.Servidor> arrServers = armarServers();
    
    
    public static ArrayList<model.Servidor> armarServers()
    {
        ArrayList<model.Servidor> arrRespuesta = new ArrayList<model.Servidor>();
        
        
        File archivo = new File (ruta);
        FileReader fr;
        try
        {
            fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            
            String linea =  "";
            model.Servidor ultimoServer = null;
            while((linea = br.readLine())!=null)
            {
                if(!linea.trim().equalsIgnoreCase("") && ! linea.trim().startsWith("El control de los ip de backup"))
                {
                    if(linea.startsWith("1"))
                    {
                        System.out.println("IP:" + linea);
                        Equipo equipoAux = new Equipo("x");
                        
                        String acumulador = "";
                        boolean pasoLaComa = false;
                        for(int i = 0 ; i < linea.length() ; i++)
                        {
                            char caracterActual = linea.charAt(i);
                            
                            if(caracterActual == ',')
                            {
                                equipoAux.setDireccionIPPrincipal(new DireccionIP(acumulador));
                                acumulador = "";
                                pasoLaComa = true;
                            }
                            else if(caracterActual == ' ' || caracterActual == '\t' || caracterActual == '\n' || caracterActual == '\r')
                            {
                                if(pasoLaComa)
                                {
                                    equipoAux.setDireccionIPBackup(new DireccionIP(acumulador));
                                    acumulador = "";
                                }
                                else
                                {
                                    equipoAux.setDireccionIPPrincipal(new DireccionIP(acumulador));
                                    break;
                                }
                            }
                            else
                            {
                                acumulador += caracterActual;
                            }
                        }
                        
                        ultimoServer.addEquipo(equipoAux);
                    }
                    else
                    {
                        if(ultimoServer != null)
                        {
                            arrRespuesta.add(ultimoServer);
                        }
                        
                        System.out.println("SV:" + linea);
                        
                        ultimoServer = new Servidor(linea);
                        
                    }
                    
                }
            }
            arrRespuesta.add(ultimoServer);
            
        } 
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        return arrRespuesta;
    }

    public static ArrayList<Servidor> getArrServers()
    {
        return arrServers;
    }
    
    
}
