import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SitrackMain {
    public static void main(String[] args) throws IOException {

        String resp = " ";
        do {
            try {
                String url;

               do {
                        System.out.println("Ingrese la URL en la que desea buscar");
                        Scanner lect = new Scanner(System.in).useDelimiter("\n");
                        url = lect.next();
               } while (url.isEmpty());
                 BufferedReader content = null ;

                 content = creador(url);





                String busca = null;
                if (!(content == null)){
                do {
                    System.out.println("Ingrese la frase a buscar");
                    Scanner lect1 = new Scanner(System.in).useDelimiter("\n");
                     busca = lect1.nextLine();
                     busca= busca.toLowerCase();
                     String reg = busca.replaceAll("\\s+","");
                     if (reg.isEmpty() || reg == null){
                         System.out.println("Debe ingresar por lo menos una palabra");
                     }
                }while (busca.isEmpty());  }

                String [] palabras = separador(busca);

                if(palabras.length>1){
                    int encontrado = buscaFrase(busca, content);

                    if (encontrado == 0) {
                        System.out.println("No se encontro la palabra/frase  buscada");
                    } else {
                        System.out.println( busca + " se encontro " + encontrado + " veces.");
                    }

                    for (int i = 0; i < palabras.length ; i++) {

                        BufferedReader contenido = creador(url);
                        int cont = buscaPalabras(palabras[i], contenido);

                        if (cont == 0) {
                            System.out.println("No se encontro la palabra buscada");
                        } else {
                            System.out.println(palabras[i] + " se encontro " + cont + " veces.");
                        }

                    }

                }else{
                    for (int i = 0; i < palabras.length ; i++) {

                        BufferedReader contenido = creador(url);
                        int cont = buscaPalabras(palabras[i], contenido);

                        if (cont == 0) {
                            System.out.println("No se encontro la palabra buscada");
                        } else {
                            System.out.println("'"+palabras[i]+"'" + " se encontro " + cont + " vez/veces");
                        }

                    }

                }

                content.close();

            }catch (Exception e){
                System.out.println("Ha ocurrido un error al realizar la b??squeda:  " + e.getMessage());
            }
            System.out.println("Desea realizar otra busqueda?");


            do {
                System.out.println("Ingrese S para continuar, N para salir");
                Scanner lect2 = new Scanner(System.in);
                resp = lect2.nextLine();

            } while (!resp.equalsIgnoreCase("s") && !resp.equalsIgnoreCase("n"));

            }while (resp.equalsIgnoreCase("s"));
        

    }


    /*
    * M??todo que genera un fichero para realizar la lectura del HTML origen
    * @ return BufferedReader
    */
    public static BufferedReader creador (String url) throws IOException {
        BufferedReader in = null;
        try {
            URL ficheroUrl = new URL(url);
            in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));

        } catch (IOException e) {
            e.getMessage();
            throw new IOException("Error URL no v??lida");
            
        }
        return in;
    }

    /*
     *M??todo para realizar la b??squeda del valor ingresado
     * @param  String: frase o palabra a buscar.
     * @param  BufferedReader: file a donde realizar la b??squeda
     * @ return N??mero de apariciones de la palabra buscada
     */
    public static int buscaFrase (String frase, BufferedReader rd) throws IOException {

        String[] separa = separador(frase);

        int cont =0;
        String linea;
        while ((linea = rd.readLine()) != null) {
            linea = linea.toLowerCase().replaceAll("[-+.^:,]","");/*.replaceAll("[-+.^:,]","");*/
            String[] palabras = separador(linea);
            String fraseDevuelta = new String();
            for (int i = 0; i < palabras.length; i++) {
                int f = i;
                if (palabras[i].equals(separa[0])) {
                    for (int j = 0; j < separa.length; j++) {

                        if (j < separa.length-1) {
                            fraseDevuelta = fraseDevuelta + palabras[f] + " ";
                        } else {
                            fraseDevuelta = fraseDevuelta +palabras[f];
                        }

                        f++;
                    }

                    if (fraseDevuelta.equals(frase)) {
                        cont = cont + 1;

                    }

                }

            }

        }
        return cont;
    }


    /*
    *M??todo que busca palabra por palabra en cada una de las lineas.
    * @param String que contiene la palabra a buscar,
    * @param BufferedReader archivo donde se realiza la b??squeda.
    * @return int devuelve la cantidad de apariciones de la palabra.
     */
    public static int buscaPalabras(String palabra ,BufferedReader rd ) throws IOException {
        int cont =0;
       String linea;
        while((linea = rd.readLine()) != null) {
            linea = linea.replaceAll("[-+.^:,]","");
            String[] palabras = separador(linea);
            for(int i = 0 ; i < palabras.length ; i++) {
                if(palabras[i].equals(palabra)) {
                    cont = cont+1;

            }
            }
        }
        return cont;
    }
        /*
         * M??todo que separa las palabras de la frase recibida.
         * @param String frase ingresada por el usuario.
         * @return Array de String, con las palabras ingresadas.
         */
        public static String[] separador (String frase){
            return frase.split(" ");
        }
    }
