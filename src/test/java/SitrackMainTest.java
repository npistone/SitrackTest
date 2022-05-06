import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class SitrackMainTest {

    @org.junit.jupiter.api.Test
    void main() {
    }

    @Test
    void creador() throws Exception {
        String url= "https://es.wikipedia.org/wiki/Rusia";
        URL ficheroUrl = new URL(url);
        BufferedReader espera = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));

        BufferedReader real = SitrackMain.creador(url);
        assertEquals(espera.equals(real), real.equals(espera) );
    }

    /*Creador devuelve excepción*/
    @Test
    void creadorException()  {

        boolean error = false;
        try{
        SitrackMain.creador(null);
        } catch (IOException e) {
            e.printStackTrace();
            error =true;
        }

        assertTrue(error);

    }

    @Test
    void find() throws Exception {
        String busca = "bloques de piedra";
        BufferedReader rd = SitrackMain.creador("https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto");
        int espera =2;
        int real = SitrackMain.buscaFrase(busca, rd);
        assertEquals(espera,real );
    }

    @org.junit.jupiter.api.Test
    void buscaPalabras() throws Exception {

        String busca = "piedra";
        BufferedReader rd = SitrackMain.creador("https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto");
        int espera =3;
        int real = SitrackMain.buscaPalabras(busca, rd);
        assertEquals(espera,real );


    }

    @org.junit.jupiter.api.Test
    void separador() {
        String frase = "Son tres palabras";
        String [] palabras = SitrackMain.separador(frase);
        String [] esperado = new String[]{"Son","tres","palabras"};
        assertEquals(esperado.length,palabras.length);
    }
}