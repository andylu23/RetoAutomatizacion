package web.com.bdd.util;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import org.eclipse.jetty.util.URIUtil;
import org.jetbrains.annotations.NotNull;
import web.com.bdd.generic.Parameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UtilWeb {

    private static final String CLASS_NAME = "UtilWeb";

    private UtilWeb(){
        //do nothing
    }

    /**
     * Crear la respuesta de la ejucion dentro de un archivo plano
     *
     * @param serviceName Nombre del servicio
     * @param formatFile  Formato del archivo
     * @param response    otenido en la ejecucion
     */
    public static boolean createResponseFile(String serviceName, String formatFile, String response) {

        //Crea path del file
        File filePath = new File("response/" + serviceName + URIUtil.SLASH + formatFile);
        if(filePath.mkdirs()){
            UtilWeb.logger(UtilWeb.class).info(Parameters.FILE_CREATED);
        }else{
            UtilWeb.logger(UtilWeb.class).info(Parameters.FILE_EXIST);
        }
        //Nombre del file con formato fecha al final
        String finalFileName = serviceName + "." + formatFile;
        //Crea el file dentro del path creado
        File file = new File(filePath + URIUtil.SLASH + finalFileName);
        UtilWeb.logger(UtilWeb.class).log(Level.INFO, "finalFileName: {0}", finalFileName);
        FileWriter fileWriter = null;
        try {
            if (file.createNewFile()) {

                UtilWeb.logger(UtilWeb.class).info(Parameters.FILE_CREATED);
            } else {

                UtilWeb.logger(UtilWeb.class).info(Parameters.FILE_EXIST);

            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write(response + System.lineSeparator());
        } catch (IOException e) {
            UtilWeb.logger(UtilWeb.class).throwing(CLASS_NAME, "createResponseFile", e);
            return false;

        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (IOException e) {

                UtilWeb.logger(UtilWeb.class).throwing(CLASS_NAME, "createResponseFile", e);

            }
        }
        return true;
    }

    public static boolean createResponseFileDataGenerate(String[] dataGenerate) {

        String serviceName = dataGenerate[0];
        String formatFile = dataGenerate[1];
        String numeroTarjeta = dataGenerate[2];
        String numeroOperacion = dataGenerate[3];
        String numeroDestino = dataGenerate[4];
        String cuentaabono = dataGenerate[5];
        String monto = dataGenerate[6];
        String monedaSigno = dataGenerate[7];

        //Crea path del file
        File filePath = new File("response/" + serviceName + URIUtil.SLASH + formatFile);
        boolean mkdirs = filePath.mkdirs();
        if (mkdirs) UtilWeb.logger(UtilWeb.class).log(Level.INFO, "Se creo el archivo");
        else UtilWeb.logger(UtilWeb.class).log(Level.INFO, "No Se creo el archivo");

        //Nombre del file con formato fecha al final
        String finalFileName = serviceName + "." + formatFile;

        //Crea el file dentro del path creado
        File file = new File(filePath + URIUtil.SLASH + finalFileName);

        UtilWeb.logger(UtilWeb.class).log(Level.INFO, " finalFileName: {0}", finalFileName);
        FileWriter fileWriter = null;
        try {
            if (file.createNewFile()) {
                UtilWeb.logger(UtilWeb.class).log(Level.INFO, Parameters.FILE_CREATED);
            } else {
                UtilWeb.logger(UtilWeb.class).log(Level.INFO, Parameters.FILE_EXIST);
            }
            String response = "";
            response += "Tarjeta: " + numeroTarjeta + System.lineSeparator();
            response += "Numero de Operación: " + numeroOperacion + System.lineSeparator();
            response += "Cuenta Abono: " + cuentaabono + System.lineSeparator();
            response += "Cuenta Destino: " + numeroDestino + System.lineSeparator();
            response += "Monto: " + monedaSigno + " " + monto + System.lineSeparator();
            response += "------------------------------";
            //Escribe la respueta dentro del file creado
            fileWriter = new FileWriter(file, true);
            fileWriter.write(response + System.lineSeparator());
        } catch (IOException e) {
            UtilWeb.logger(UtilWeb.class).throwing(CLASS_NAME, "createResponseFileDataGenerate", e);
            return false;
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
            } catch (IOException e) {
                UtilWeb.logger(UtilWeb.class).throwing(CLASS_NAME, "createResponseFileDataGenerate", e);
            }
        }
        return true;
    }


    /**
     * Toma una captura del scenario y lo guarda en formato png
     *
     * @param folder   Ubicacion de donde se van a ubicar las evidencias
     * @param scenario Scenario otenido en la ejecucion
     * @throws IOException Exception
     */
    public static void embedImageCucumber(File[] folder, Scenario scenario) throws IOException {
        for (File file : folder) {
            if (file.getName().contains(".png")) {
                File imgPath = new File(file.getAbsolutePath());
                BufferedImage bufferedImage = ImageIO.read(imgPath);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);

                byte[] screenShotByte = outputStream.toByteArray();
                scenario.embed(screenShotByte, "image/png");

            }
        }
    }


    /**
     * Obtiene el valor de Tabla de Datos de gherkin en un feature
     *
     * @param dataTable Tabla de datos declarada en el feature
     * @param title     Titulo de una columna en la tabla de datos
     * @return Retorna el valor de la columa buscada por titulo
     */
    public static String getValueFromDataTable(DataTable dataTable, String title) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        return list.get(0).get(title);
    }


    /**
     * Filtra los numeros y el signo de puntuacion, de una cadena de caracteres, respetando su orden de lectura
     *
     * @param cadena texto enviado
     * @return numeros enteros o decimales, tipo String
     */
    public static String getNumDecimal(String cadena) {
        if (cadena == null) {
            return null;
        }
        StringBuilder concat = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            String sub = cadena.substring(i, i + 1);
            if (isNumeric(sub)) {
                concat.append(sub);
            }
            if (sub.equals(".")) {
                concat.append(".");
            }
        }
        return concat.toString();
    }


    /**
     * Indica si es verdad o no, que el texto enviado es un numero
     *
     * @param str texto enviado
     * @return verdadero, si el texto es convertido en numero
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            UtilWeb.logger(UtilWeb.class).throwing("Utilweb", "isNumeric", nfe);
            return false;
        }
    }


    /**
     * Crea un archivo en caso no existierá e inserta texto en un archivo formato *.json, ubicado en la raiz del proyecto, nombre de carpeta JSON
     *
     * @param nameFile    nombre del archivo
     * @param typeFile    el tipo de formato del archivo
     * @param setDataText el texto que enviaremos a dentro del archivo creado
     */
    public static boolean createFileWithData(String nameFile, String typeFile, String[] setDataText) {
        String fileName = nameFile + "." + typeFile;
        File filePath = (new File("json")).getAbsoluteFile();
        File file = new File(filePath, fileName);

        try {
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                UtilWeb.logger(UtilWeb.class).log(Level.INFO, "Archivo creado {0}", newFile);
            }
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(Arrays.toString(setDataText).replace("[", "[\"").replace("]", "\"]").replace(", ", "\",\""));
            writer.close();
        } catch (IOException ex) {
            UtilWeb.logger(UtilWeb.class).throwing("UtilWeb.class", "createFileWithData", ex);
            return false;
        }
        return true;
    }

    /**
     * Obtiene el texto de un archivo de la carpeta JSON, ubicado en la raiz del proyecto
     *
     * @param nameFile nombre del archivo, del cual queremos extraer su texto
     * @param typeFile el tipo de formato del archivo
     * @return el texto del archivo, tipo String
     */
    public static String getDataFile(String nameFile, String typeFile) {
        String filePath = (new File("web-continuous-testing\\json")).getAbsoluteFile() + URIUtil.SLASH + nameFile + "." + typeFile;
        String cadena;
        StringBuilder texto = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while ((cadena = br.readLine()) != null) {
                texto.append(cadena).append("\n");
            }
        }catch (IOException e) {
            UtilWeb.logger(UtilWeb.class).throwing(CLASS_NAME, "getDataFile", e);
        }

        return texto.toString();
    }

    /***
     * Copia en el portapapeles el texto enviado
     * @param texto texto a copiar
     */
    public static void guardarTextoEnPortapapeles(String texto) {
        StringSelection selection = new StringSelection(texto);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    /**
     * Metodo que consume la clase Logger para el loggin
     *
     * @param clase Clase donde se esta utilizando el loggin
     * @return retorna la clase Logger con la clase ya definida
     */
    public static Logger logger(@NotNull Class clase) {
        return Logger.getLogger(clase.getName());
    }

}