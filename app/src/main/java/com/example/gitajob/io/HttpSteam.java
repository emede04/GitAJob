package com.example.gitajob.io;

import android.util.Log;

import com.example.gitajob.steamdata.Constantes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSteam {

    public String cargarUsuarioBase(String clave){
        Constantes c = new Constantes(clave);
        Log.d("mio"," Clase HTTP STEAM comenzamos a cargar los usuarios");
            HttpURLConnection http = null;
                String content = null;
                try {
                    //formaremos la url juntando la url más el endpoint.
                    // Así como la cabecera, que permitira decidir la codificación de los datos que se están trasmitiendo.
                    URL url = new URL( c.getGetPlayerSummaries());
                    http = (HttpURLConnection)url.openConnection();
                    http.setRequestProperty("Content-Type", "application/json");
                    http.setRequestProperty("Accept", "application/json");

                    //Si el servidor devuelve un codigo 200 (HTTP_OK == 200),la informacion que devuelva sabremos que es correcta.
                    if( http.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                        //Se codifica el texto de la respuesta como String.
                        StringBuilder sb = new StringBuilder();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader( http.getInputStream() ));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        content = sb.toString();
                        reader.close();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                finally {

                    //Se desconecta la conexión.
                    if( http != null ) http.disconnect();
                }
                return content;
            }

    public static int post(String clave, String datos){
        Constantes c = new Constantes(clave);
        HttpURLConnection http = null;
        int respuestaCodigo = -1;
        try{
            // Creamos la URL con el valor pasado por parametro (champ)
            URL url = new URL(c.getGetPlayerSummaries());
            // Abrimos la conexion con la API
            http = (HttpURLConnection)url.openConnection();
            // Establecemos el modo de conexion, en este caso, POST
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type","application/json");
            http.setRequestProperty("Accept","application/json");
            http.setDoOutput(true);

            // Creamos el escritor, para mostrar los datos por pantalla
            PrintWriter escritor = new PrintWriter(http.getOutputStream());
            // Escribo los datos leidos en el layout
            escritor.print(datos);
            escritor.flush();
            // Comprobamos si los datos han sido leidos correctamente
            respuestaCodigo = http.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (http != null){
                http.disconnect();
            }
        }
        return respuestaCodigo;
    }





    //TODO -Pues eso veras las risas mañana

    public String cargarNoticias(String clave) {

        return clave;
    }


    public String cargarJuegos(String clave) {
        Constantes c = new Constantes(clave);
        Log.d("mio"," Clase HTTP STEAM comenzamos a cargar los usuarios");
        HttpURLConnection http = null;
        String content = null;
        try {
            //formaremos la url juntando la url más el endpoint.
            // Así como la cabecera, que permitira decidir la codificación de los datos que se están trasmitiendo.
            URL url = new URL( c.getGetOwnedGames());
            http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");

            //Si el servidor devuelve un codigo 200 (HTTP_OK == 200),la informacion que devuelva sabremos que es correcta.
            if( http.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                //Se codifica el texto de la respuesta como String.
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader( http.getInputStream() ));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
                reader.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {

            //Se desconecta la conexión.
            if( http != null ) http.disconnect();
        }
        return content;
    }
    public static int postGamesOwned(String clave, String datos){
        Constantes c = new Constantes(clave);
        HttpURLConnection http = null;
        int respuestaCodigo = -1;
        try{
            // Creamos la URL con el valor pasado por parametro (champ)
            URL url = new URL(c.getGetOwnedGames());
            // Abrimos la conexion con la API
            http = (HttpURLConnection)url.openConnection();
            // Establecemos el modo de conexion, en este caso, POST
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type","application/json");
            http.setRequestProperty("Accept","application/json");
            http.setDoOutput(true);

            // Creamos el escritor, para mostrar los datos por pantalla
            PrintWriter escritor = new PrintWriter(http.getOutputStream());
            // Escribo los datos leidos en el layout
            escritor.print(datos);
            escritor.flush();
            // Comprobamos si los datos han sido leidos correctamente
            respuestaCodigo = http.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (http != null){
                http.disconnect();
            }
        }
        return respuestaCodigo;
    }

}