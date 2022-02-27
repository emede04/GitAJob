package com.example.gitajob.io;

import com.example.gitajob.steamdata.Constantes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSteam {

    public String cargarUsuarioBase(String clave){
        Constantes c = new Constantes(clave);
        System.out.println("entra");
        HttpURLConnection http = null;
                String content = null;
                try {
                    //formaremos la url juntando la url más el endpoint.
                    // Así como la cabecera, que permitira decidir la codificación de los datos que se están trasmitiendo.
                    URL url = new URL( c.getGetPlayerSummaries());
                    System.out.println(c.getIdpasada());
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
                        System.out.println(content);
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



    }
