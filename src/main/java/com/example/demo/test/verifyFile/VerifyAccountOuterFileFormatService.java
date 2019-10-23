package com.example.demo.test.verifyFile;


import com.example.demo.common.CodeMsg;
import com.example.demo.common.ResultDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.util.DateUtil;
import com.example.demo.util.ResultUtils;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 *  校验对账外部文件格式
 */
@Slf4j
@Service
public class VerifyAccountOuterFileFormatService {

    public static final String FILE_SUFFIX_CSV = ".csv";
    public static final String FILE_SUFFIX_XLS = ".xls";
    public static final String FILE_SUFFIX_XLSX = ".xlsx";


    public ResultDTO getOuterAccountFileInfo(MultipartFile file) throws IOException {
        log.info("开始解析外部对账文件");
        // 1、获取外部对账文件名
        String fileName = file.getOriginalFilename();
        // /2、获取外部对账后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        VerifyOuterFileInfoDTO verifyOuterFileInfoDTO = new VerifyOuterFileInfoDTO();
        verifyOuterFileInfoDTO.setSuffixName(suffixName);
        if (FILE_SUFFIX_CSV.equals(suffixName)){
            FileCSVDTO fileCSVDTO = new FileCSVDTO();
            File toFile = null;
            String milliTime = DateUtil.getMilliTime();
            String title = milliTime + ".csv";
            if (file.equals("") || file.getSize() <= 0) {
                file = null;
            } else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamTurnToFile(ins, toFile);
                ins.close();
            }
            fileCSVDTO.setFile(toFile);
            fileCSVDTO.setTitle(title);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileCSVDTO.getFile()), "GB2312"));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> csvList = csvReader.readAll();
            if (csvList.size() > 0){
                verifyOuterFileInfoDTO.setRows(csvList.size());
                verifyOuterFileInfoDTO.setColumns(csvList.get(0).length);
                Date date = DateUtil.strToDate(csvList.get(1)[0], DateUtil.FORMAT_PATTERN_DAY_TOGETHER);
                String dateYM = DateUtil.dateToStr(date, "yyyy-MM");
                verifyOuterFileInfoDTO.setYearMonth(dateYM);
            }else{
                log.error("CSV对账外部文件返回行数为零");
                throw new ServiceException(CodeMsg.ACCOUNT_OUTER_FILE_RETURN_ROWS_NULL);
            }
            csvReader.close();
            reader.close();
        }else if(FILE_SUFFIX_XLS.equals(suffixName)){
            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String sheetName = sheet.getSheetName();
            verifyOuterFileInfoDTO.setSheetName(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            if (rows > 0){
                Row row = sheet.getRow(1);
                int columns = row.getPhysicalNumberOfCells();
                verifyOuterFileInfoDTO.setRows(rows);
                verifyOuterFileInfoDTO.setColumns(columns);

                // 获取数据行首列数据的日期
                Cell cell = row.getCell(0);
                Date date = cell.getDateCellValue();
                String dateYM = DateUtil.dateToStr(date, "yyyy-MM");
                verifyOuterFileInfoDTO.setYearMonth(dateYM);
            }else{
                log.error("XLS对账外部文件返回行数为零");
                throw new ServiceException(CodeMsg.ACCOUNT_OUTER_FILE_RETURN_ROWS_NULL);
            }

        }else if(FILE_SUFFIX_XLSX.equals(suffixName)){
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = wb.getSheetAt(0);
            String sheetName = sheet.getSheetName();
            verifyOuterFileInfoDTO.setSheetName(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            if (rows > 0){
                Row row = sheet.getRow(1);
                int columns = row.getPhysicalNumberOfCells();
                verifyOuterFileInfoDTO.setRows(rows);
                verifyOuterFileInfoDTO.setColumns(columns);

                // 获取数据行首列数据的日期
                Cell cell = row.getCell(0);
                Date date = cell.getDateCellValue();
                String dateYM = DateUtil.dateToStr(date, "yyyy-MM");
                verifyOuterFileInfoDTO.setYearMonth(dateYM);
            }else{
                log.error("XLSX对账外部文件返回行数为零");
                throw new ServiceException(CodeMsg.ACCOUNT_OUTER_FILE_RETURN_ROWS_NULL);
            }
        }else{
            log.error("对账外部文件未知的文件后缀名");
            throw new ServiceException(CodeMsg.ACCOUNT_OUTER_FILE_SUFFIXNAME_UNKOWN);
        }
        return ResultUtils.success(verifyOuterFileInfoDTO);
    }

    /**
     * 把数据流转换成file
     *
     * @param inputStream
     * @param file
     */
    public void inputStreamTurnToFile(InputStream inputStream, File file) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
