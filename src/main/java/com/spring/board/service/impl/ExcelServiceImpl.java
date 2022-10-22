package com.spring.board.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.ExcelDao;
import com.spring.board.service.ExcelService;
import com.spring.board.vo.BoardVo;

@Service
public class ExcelServiceImpl implements ExcelService{
	
	@Autowired
	ExcelDao exceldao;

	@Override
	public void excelDown(BoardVo boardVo, HttpServletResponse response) throws Exception {
		
		
		List<BoardVo> voList = exceldao.selectExcelList(boardVo); 
		
		
		 try {

		      //Excel Down 시작

		      Workbook workbook = new HSSFWorkbook();

		      //시트생성
		      Sheet sheet = workbook.createSheet("test");

		      //행, 열, 열번호
		      Row row = null;
		      Cell cell = null;
		      int rowNo = 0;

		      // 테이블 헤더용 스타일
		      CellStyle headStyle = workbook.createCellStyle();

		      // 가는 경계선을 가집니다.
		      headStyle.setBorderTop(BorderStyle.THIN);
		      headStyle.setBorderBottom(BorderStyle.THIN);
		      headStyle.setBorderLeft(BorderStyle.THIN);
		      headStyle.setBorderRight(BorderStyle.THIN);

		      // 배경색은 노란색입니다.
		      headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		      headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		      // 데이터용 경계 스타일 테두리만 지정
		      CellStyle bodyStyle = workbook.createCellStyle();
		      bodyStyle.setBorderTop(BorderStyle.THIN);
		      bodyStyle.setBorderBottom(BorderStyle.THIN);
		      bodyStyle.setBorderLeft(BorderStyle.THIN);
		      bodyStyle.setBorderRight(BorderStyle.THIN);

		 

		      // 헤더명 설정
		      String[] headerArray = {"NO", "제목","내용","등록일","등록자","사용여부"};
		      row = sheet.createRow(rowNo++);
		      for(int i=0; i<headerArray.length; i++) {
		      cell = row.createCell(i);
		      cell.setCellStyle(headStyle);
		      cell.setCellValue(headerArray[i]);
		      }

		 

		      for(BoardVo excelData : voList ) {
		      row = sheet.createRow(rowNo++);
		      
		      cell = row.createCell(0);
		      cell.setCellStyle(bodyStyle);
		      //cell.setCellValue(excelData.getRowNum());
		      cell.setCellValue(excelData.getBoardNum());
		      
		      cell = row.createCell(1);
		      cell.setCellStyle(bodyStyle);
		     // cell.setCellValue(excelData.getTitle());
		      cell.setCellValue(excelData.getBoardTitle());
		      
		      cell = row.createCell(2);
		      cell.setCellStyle(bodyStyle);
		      //cell.setCellValue(excelData.getCnts());
		      cell.setCellValue(excelData.getBoardComment());

		      cell = row.createCell(3);
		      cell.setCellStyle(bodyStyle);
		      //cell.setCellValue(excelData.getRegDt());
		      cell.setCellValue(excelData.getBoardType());

		      cell = row.createCell(4);
		      cell.setCellStyle(bodyStyle);
		      //cell.setCellValue(excelData.getRegNm());
		      cell.setCellValue(excelData.getCodeName());

		      cell = row.createCell(5);
		      cell.setCellStyle(bodyStyle);
		      //cell.setCellValue(excelData.getUseYn());
		      cell.setCellValue(excelData.getCreator());
		      
		      cell = row.createCell(6);
		      cell.setCellStyle(bodyStyle);
		      //cell.setCellValue(excelData.getUseYn());
		      cell.setCellValue(excelData.getModifier());

		      }

		 
		      // 컨텐츠 타입과 파일명 지정
		      response.setContentType("ms-vnd/excel");
		      response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("test", "UTF8"));
		      //response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("***_관리.xls", "UTF8"));


		      // 엑셀 출력
		      workbook.write(response.getOutputStream());
		      workbook.close();
		      } catch (Exception e) {
		      e.printStackTrace();
		      }
	    }
	}
