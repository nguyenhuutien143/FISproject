package org.example.repository;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadTransactionExcelRepository {
    public static void main(String[] args) throws IOException {
        final String excelFilePath = "transaction_history.xlsx";
        final List<Transaction> transactions = readTransactionExcel(excelFilePath);
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
    public static List<Transaction> readTransactionExcel(String excelFilePath) throws IOException {
        List<Transaction> transactionList = new ArrayList<>();
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Transaction transaction = new Transaction();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case Column.COLUMN_INDEX_TRANSACTION_TYPE:
                        transaction.setTransactionType(TransactionType.valueOf(((String)getCellValue(cell)).toUpperCase()));
                        break;
                    case Column.COLUMN_INDEX_BANK_ACCOUNT:
                        transaction.setBankAccount((String) getCellValue(cell));
                        break;
                    case Column.COLUMN_INDEX_AMOUNT:
                        transaction.setAmount(new BigDecimal((Double) cellValue).doubleValue());
                        break;
                    case Column.COLUMN_INDEX_MESSAGE:
                        transaction.setMessage( (String) getCellValue(cell));
                        break;
                    case Column.COLUMN_INDEX_DATE_TIME:
                        transaction.setDateTime(cell.getDateCellValue());
                        break;
                    default:
                        break;
                }

            }
            transactionList.add(transaction);
        }

        workbook.close();
        inputStream.close();

        return transactionList;
    }

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }

    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }
}
