package com.spring.board.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.dao.ExcelDao;
import com.spring.board.service.ExcelService;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.common.CommonUtil;

@Controller
public class ExcelController {
	
	@Autowired 
	ExcelService excelService;
	
	@Autowired
	ExcelDao excelDao;
	/*
	@RequestMapping(value = "/excel/download.do", method = RequestMethod.POST , produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String ExcelDown(@ModelAttribute BoardVo boardVo 
							,HttpServletRequest request
							,HttpServletResponse response) throws Exception {
		
		System.out.println("====================================엑셀다운로드============================================");
		
		excelService.excelDown(boardVo, response);
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		result.put("success", (0 == 0)?"Y" : "N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;

	}*/
	@RequestMapping(value = "/excel/download1.do", method = RequestMethod.POST , produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String ExcelDown1(@ModelAttribute BoardVo boardVo 
			,HttpServletRequest request
			,HttpServletResponse response) throws Exception {
		
		System.out.println("====================================엑셀다운로드============================================");
		
		List<BoardVo> voList = excelDao.selectExcelList(boardVo); 
		
		
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
		      String[] headerArray = {"NO", "제목","내용","작성자","타입","분류"};
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
		      cell.setCellValue(excelData.getBoardNum());
		      
		      cell = row.createCell(1);
		      cell.setCellStyle(bodyStyle);
		      cell.setCellValue(excelData.getBoardTitle());
		      
		      cell = row.createCell(2);
		      cell.setCellStyle(bodyStyle);
		      cell.setCellValue(excelData.getBoardComment());
		      
		      cell = row.createCell(3);
		      cell.setCellStyle(bodyStyle);
		      cell.setCellValue(excelData.getCreator());
		
		      cell = row.createCell(4);
		      cell.setCellStyle(bodyStyle);
		      cell.setCellValue(excelData.getBoardType());
		      
		      cell = row.createCell(5);
		      cell.setCellStyle(bodyStyle);
		      cell.setCellValue(excelData.getCodeName());
		

		      }
		   

		 
		      // 컨텐츠 타입과 파일명 지정
		      response.setContentType("ms-vnd/excel");
		      response.setHeader("Content-Disposition", "attachment;filename=test.xls");
		      //response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("***_관리.xls", "UTF8"));


		      // 엑셀 출력
		      workbook.write(response.getOutputStream());
		      workbook.close();
		      } catch (Exception e) {
		      e.printStackTrace();
		      }
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		result.put("success", (0 == 0)?"Y" : "N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
		
	}
	
	@RequestMapping(value ="/excel/upload.do" ,method = RequestMethod.GET)
	public String excelUpload() {
		
		return "excel/ExcelUpload";
	}
	
	@RequestMapping(value = "/excel/uploadAction.do" , method = RequestMethod.POST  )
	public String excelUploadAction( @RequestParam("file") MultipartFile file , Model model)throws IOException  {
		System.out.println("====================================엑셀업로드============================================");
		List<BoardVo> dataList = new ArrayList<>();
		
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		
		if (!extension.equals("xlsx") && !extension.equals("xls")) {
		      throw new IOException("엑셀파일만 업로드 해주세요.");
		    }
		
		Workbook workbook = null;
		
		if (extension.equals("xlsx")) {
		      workbook = new XSSFWorkbook(file.getInputStream());
		    } else if (extension.equals("xls")) {
		      workbook = new HSSFWorkbook(file.getInputStream());
		    }
		
		Sheet worksheet = workbook.getSheetAt(0);
		
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4

		      Row row = worksheet.getRow(i);

		      BoardVo boardVo = new BoardVo();

		      boardVo.setBoardNum((int) row.getCell(0).getNumericCellValue());
		      boardVo.setBoardTitle(row.getCell(1).getStringCellValue());
		      boardVo.setBoardComment(row.getCell(2).getStringCellValue());
		      boardVo.setCreator(row.getCell(3).getStringCellValue());
		      boardVo.setBoardType(row.getCell(4).getStringCellValue());
		      boardVo.setCodeName(row.getCell(5).getStringCellValue());

		      dataList.add(boardVo);
		    }
		
		System.out.println("확인 : "+dataList);
		 model.addAttribute("datas", dataList);

		
		return "excel/ExcelUploadList";
	}
	
	@RequestMapping(value ="/excel/calendar.do" ,method = RequestMethod.GET)
	public String excelCalendar(HttpServletRequest request
							   ,HttpServletResponse response) throws Exception {
		
		return "excel/excelCalendar";
	}
	
	
	
	@RequestMapping(value = "/excel/calendarAction.do" , method = RequestMethod.POST , produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public void excelCalendarAction(HttpServletRequest request
									 ,HttpServletResponse response
									 
									 ) throws Exception {
		System.out.println("엑셀 달력 시작");
	
		InputStream fis =null;
		fis = new FileInputStream("C:\\original.xlsx");
		XSSFWorkbook Cworbook = new XSSFWorkbook(fis);
		XSSFSheet Csheet = Cworbook.getSheetAt(0);
		
		XSSFWorkbook worbook=null;
		Sheet sheet=null;
		Row row=null;
		Row row1=null;
		Row row2=null;
		Row row3=null;
		Cell cell=null; 
		worbook = new XSSFWorkbook();
		sheet = worbook.createSheet("mysheet이름");
		
		 // 테이블 헤더용 스타일
	      CellStyle headStyle = worbook.createCellStyle();
	      CellStyle bodyStyle = worbook.createCellStyle();
	      CellStyle customStyle1 = worbook.createCellStyle();;

	      // 가는 경계선을 가집니다.
	      headStyle.setBorderTop(BorderStyle.THIN);
	      headStyle.setBorderBottom(BorderStyle.THIN);
	      headStyle.setBorderLeft(BorderStyle.THIN);
	      headStyle.setBorderRight(BorderStyle.THIN);
	      // 배경색은 노란색입니다.
	      headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	      headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	      // 데이터용 경계 스타일 테두리만 지정
	      bodyStyle.setBorderTop(BorderStyle.THIN);
	      bodyStyle.setBorderBottom(BorderStyle.THIN);
	      bodyStyle.setBorderLeft(BorderStyle.THIN);
	      bodyStyle.setBorderRight(BorderStyle.THIN);
	      
	      customStyle1.setBorderTop(BorderStyle.MEDIUM);
	      customStyle1.setBorderLeft(BorderStyle.MEDIUM);
	      
	      // 헤더명 설정
	      String[] headerArray = {"NO", "제목","내용","작성자","타입","분류"};
	      row = sheet.createRow(0);
	      for(int i=0; i<headerArray.length; i++) {
	      cell = row.createCell(i);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue(headerArray[i]);
	      }
	      
	      String[] headerArray1 = {"", "","월","", "","화","", "","수","", "","목","", "","금","", "","토","", "","일"};
	      row = sheet.createRow(1);
	      for(int i=0; i<headerArray1.length; i++) {
	      cell = row.createCell(i);
	      cell.setCellValue(headerArray1[i]);
	      }
		
	      int n1 = 0;
	      int n2 = 0;
	      int n3 = 0;
	      int range1 = 2;
	      int range2 = 0;
	      
	      for(int j = 0; j<5 ; j++) {
	    	  
	  
	      
	      for(int i=2; i<9 ; i++) {
	      sheet.addMergedRegion(new CellRangeAddress(range1,range1,range2,range2+2));
	      range2 += 3;
	      if(i == 2) {
	      row1 = sheet.createRow(3); //0번째 행
	      }
			cell=row1.createCell(n1);
			cell.setCellValue("1");
			n1++;
			cell=row1.createCell(n1);
			cell.setCellValue("2");
			n1++;
			cell=row1.createCell(n1);
			cell.setCellValue("3");
			n1++;
			 if(i == 2) {				 
		  row2 = sheet.createRow(4); //0번째 행
			 }
		  	cell=row2.createCell(n2);
			cell.setCellValue("4");
			n2++;
			cell=row2.createCell(n2);
			cell.setCellValue("5");
			n2++;
			cell=row2.createCell(n2);
			cell.setCellValue("6");
			n2++;
			 if(i ==2) {				 
		   row3 = sheet.createRow(5); //0번째 행
			 }
			cell=row3.createCell(n3);
			cell.setCellValue("7");
			n3++;
			cell=row3.createCell(n3);
			cell.setCellValue("8");
			n3++;
			cell=row3.createCell(n3);
			cell.setCellValue("9");
			n3++;
	      }
	      range1 += 4;
	      }
		
		// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");  //파일이름지정.
		//response OutputStream에 엑셀 작성
		worbook.write(response.getOutputStream());
		
	}

}
