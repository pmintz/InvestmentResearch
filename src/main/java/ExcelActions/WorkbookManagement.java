package ExcelActions;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

import java.io.FileInputStream;
import java.io.IOException;

import static ExcelActions.CopyExcelRanges.*;

public class WorkbookManagement {
    static String home = System.getProperty("user.home");
    static String sourceBaseFilePath = home + "\\Downloads\\";
    static String[] statementTypes = {"Income", "Balance", "Cash"};
    StatementType statementTypeObj = new StatementType();
    static String destinationFilePath = "./src/main/resources/MASTER COPY 2025.xls";

    public void copyData() throws IOException {
        Workbook destinationWorkbook = getDestinationWorkbook();
        for (String statementType : statementTypes) {
            copyRows(getSourceWorkbook(statementTypeObj.getFilePaths().get(statementType)),
                    destinationWorkbook,
                    statementTypeObj.getCellRanges().get(statementType)
            );

            writeToDestinationWorkbook(destinationWorkbook, statementType);
        }

    }

    public static HSSFWorkbook getSourceWorkbook(String path) {
        String sourceFilePath = sourceBaseFilePath + path;
        try (FileInputStream sourceFile = new FileInputStream(sourceFilePath)) {
            Thread.sleep(2000);
            return new HSSFWorkbook(sourceFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to Retrieve Source Workbook");

        }

        return null;
    }

    public static Workbook getDestinationWorkbook() {
        try (FileInputStream sourceFile = new FileInputStream(destinationFilePath)) {
            Thread.sleep(2000);
            return new HSSFWorkbook(sourceFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to Retrieve Destination Workbook");

        }

        return null;
    }

    public static void writeToDestinationWorkbook(Workbook destinationWorkbook, String statementType) {

        try (FileOutputStream outputStream = new FileOutputStream(destinationFilePath)) {
            Thread.sleep(2000);
            destinationWorkbook.write(outputStream);
        } catch (Exception e) {
            System.out.println(statementType + " statement not copied!");
            System.out.println(e.getMessage());
        }

        System.out.println(statementType + " statement copied successfully!");
    }

    public void removeFinancialStatementsFromDownloadFolder() {
        try {
            for (String statementType : statementTypes) {
                String path = statementTypeObj.getFilePaths().get(statementType);
                String sourceFilePath = sourceBaseFilePath + path;

                File file = new File(sourceFilePath);
                if (file.delete()) {
                    System.out.println(statementType + " file deleted successfully");
                } else {
                    System.out.println("Failed to delete " + statementType + " file");
                }
            }
        } catch (Exception e) {
            System.out.println("Error removing files from download folder");
            System.out.println(e.getMessage());
        }

    }

}
