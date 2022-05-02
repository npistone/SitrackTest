import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Array;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {

    @org.junit.jupiter.api.Test
    void main() {
    }

    @org.junit.jupiter.api.Test
    void creador() {
    }

    @org.junit.jupiter.api.Test
    void find() throws IOException {
        String busca = "bloques de piedra";
        BufferedReader rd = main.creador("https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto");
        int espera =2;
        int real = main.find(busca, rd);
        assertEquals(espera,real );
    }

    @org.junit.jupiter.api.Test
    void buscaPalabras() throws IOException {

        String busca = "piedra";
        BufferedReader rd = main.creador("https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto");
        int espera =3;
        int real = main.buscaPalabras(busca, rd);
        assertEquals(espera,real );


    }

    @org.junit.jupiter.api.Test
    void separador() {
        String frase = "Son tres palabras";
        String [] palabras = main.separador(frase);
        String [] esperado = new String[]{"Son","tres","palabras"};
        assertEquals(esperado.length,palabras.length);
    }
}