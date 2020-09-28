package org.application;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static final int COLUMN = 1; // Column B = 1

    public static void main(String[] args) {
        String pathToFile;
        if (args.length != 1) {
            throw new IllegalArgumentException("Expected one argument with path to xslx file. Got " + args.length);
        } else {
            pathToFile = args[0];
        }
        LOGGER.log(Level.INFO, pathToFile);

        try {
            File excelFile = new File(pathToFile);
            LOGGER.log(Level.INFO, excelFile.getName());
            try (FileInputStream fis = new FileInputStream(excelFile)) {
                try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
                    XSSFSheet sheet = wb.getSheetAt(0);
                    Iterator<Row> itr = sheet.iterator();
                    itr.next(); //skip "Data" header
                    while (itr.hasNext()) {
                        Row row = itr.next();
                        Cell cell = row.getCell(COLUMN);
                        long cellValue;
                        try {
                            cellValue = Long.parseLong(cell.getStringCellValue());
                        } catch (NumberFormatException e) {
                            continue;
                        }
                        if (isPrime(cellValue)) {
                            System.out.println(cellValue);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.warning(String.format("File not found on a path: %s", pathToFile));
            throw new IllegalArgumentException("File not found", e);
        } catch (IOException e) {
            LOGGER.warning("Error during file reading");
            throw new IllegalArgumentException("Error during file reading", e);
        }

    }

    public static boolean isPrime(Long n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
