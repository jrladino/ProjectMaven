package com.cucumber.utils;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtil {

    // Método que recibe  la ruta de un archivo Excel, busca fila disponible "Disponible"
    public static Map<String, String> obtenerRegistroDisponibleYMarcarUsado(String rutaArchivo){
        //Mapa para almacenar los datos de la fila encontrada (clave= nombre columna, valor= valor celda)
        Map<String, String> filaLeida = new HashMap<>();

        try{
            //Abre el archivo Excel indicado en la ruta
            FileInputStream file = new FileInputStream(new File(rutaArchivo));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Obtiene la primera hoja

            //Lee la primera fila para obtener los nombres de las columnas
            Row encabezado = sheet.getRow(0);

            //Busca el indice de la columna "Estado"
            int columnaEstado = obtenerIndiceColumna(encabezado, "Estado");   
            
            // Si no encuentra la columna "Estado", retorna mapa vacío
            if(columnaEstado == -1){
                workbook.close();
                file.close();
                return filaLeida;
            }
            // Recorre todas las filas empezando desde la segunda fila (índice 1), por que la primera es el encabezado
            for(int i=1; i<= sheet.getLastRowNum(); i++){
                Row fila = sheet.getRow(i);
                if(fila == null) continue; // Salta filas vacías
                //obtiene la celda correspondiente a la columna "Estado"
                Cell celdaEstado = fila.getCell(columnaEstado);
                if(celdaEstado == null) continue; // Salta si la celda está vacía

                


                
            }

            

        }
        catch (Exception e){
            System.out.println("Error al leer o escribir en el archivo Excel: " + e.getMessage());
        }

        return filaLeida;

    }

    private static int obtenerIndiceColumna(Row encabezado, String nombreColumna){
        for(Cell celda : encabezado){
            if(celda.getStringCellValue().equalsIgnoreCase(nombreColumna)){
                return celda.getColumnIndex();
            }
        }
        return -1; // Retorna -1 si no encuentra la columna
    }

}
