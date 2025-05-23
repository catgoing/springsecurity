package com.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class Excel {
	
	private List<File> directories;
	
	static final int MAX_CELL_DATA_LENGTH = 32767;
	
	public void run(String dir) {
		
		
		System.out.println("dir: " + dir);
		int fileno = 1;
		Workbook workbook = null;
		
		directories = new ArrayList<>();
		boolean hasFile = false;
		
		File f = new File(dir);
		System.out.println("f: " + f.getName());
		System.out.println("f: " + f.getPath());
		String fileName = null;
		File resultDir = new File(f.getParentFile().getParent() + "/" + f.getParentFile().getName() + "_result");
		Sheet sheet = null;
		int rownum = 0;
		System.out.println(f.listFiles());
		for (File file : f.listFiles()) {
			boolean makeFile = false; 
//			System.out.println(file.getName());
			if(file.isDirectory()) {
				run(file.getPath());
				continue;
			}
			
			hasFile = true;
			makeFile = true;
			
			
			if(makeFile && workbook == null) {
//				workbook = new XSSFWorkbook();
				workbook = new SXSSFWorkbook();
				fileName = resultDir.getPath() + "/" + f.getName() + ".xlsx";
				File of = new File(fileName);
				if(of.exists()) {
					of.delete();
				}
				System.out.println("createsheet");
				sheet = workbook.createSheet();
				sheet.setColumnWidth(0, 15000);
				sheet.setColumnWidth(1, 15000);
				sheet.setColumnWidth(2, 15000);
				sheet.setColumnWidth(3, 15000);
				
				Row headerRow = sheet.createRow(rownum++); //0번째 줄 생성 - 헤더(맨 윗줄)
				CellStyle cs = workbook.createCellStyle();
				cs.setAlignment(HorizontalAlignment.CENTER);
				Cell cell = headerRow.createCell(0);
				cell.setCellValue("프로그램명");
				cell.setCellStyle(cs);
				cell = headerRow.createCell(1);
				cell.setCellValue("preaction");
				cell.setCellStyle(cs);
				cell = headerRow.createCell(2);
				cell.setCellValue("postaction");
				cell.setCellStyle(cs);
				cell = headerRow.createCell(3);
				cell.setCellValue("SQL");
				cell.setCellStyle(cs);
			}
				
			String line = "";
			
			StringBuilder preaction = new StringBuilder();
			StringBuilder postaction = new StringBuilder();
			StringBuilder sql = new StringBuilder();
			
			boolean isPre = false;
			boolean isPost = false;
			boolean isSql = false;
			
			try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
				System.out.println(fileno + ". " + file.getName());
				System.out.println("=============start=============");
				Row dataRow = null;
				while((line = bf.readLine()) != null) {
////					System.out.println(line);
					if(line.contains("<preaction")) {
						isPre = true;
						dataRow = sheet.createRow(rownum++);
						Cell pName = dataRow.createCell(0);
						pName.setCellValue(file.getName());
					} else if(line.contains("<postaction")) {
						isPost = true;
					} else if(line.contains("<SQL")) {
						isSql = true;
					}
//					
					if(isPre) {
						preaction.append(line);
						preaction.append(System.lineSeparator());
					}
					if(isPost) {
						postaction.append(line);
						postaction.append(System.lineSeparator());
					}
					if(isSql) {
						sql.append(line);
						sql.append(System.lineSeparator());
					}
//					
					if(line.contains("</preaction") || (isPre && line.contains("/>"))) {
						isPre = false;
						Cell pre = dataRow.createCell(1);
						preaction.replace(preaction.indexOf("<preaction"), preaction.indexOf(">") + 1, "");
						if(preaction.indexOf("</preaction>") > -1) {
							preaction.replace(preaction.indexOf("</preaction>"), preaction.indexOf("</preaction>") + 12, "");
						}
						pre.setCellValue(preaction.toString());
						preaction.setLength(0);
					} 
						else if(line.contains("</postaction")|| (isPost && line.contains("/>"))) {
						isPost = false;
						Cell post = dataRow.createCell(2);
						postaction.replace(postaction.indexOf("<postaction"), postaction.indexOf(">") + 1, "");
						if(postaction.indexOf("</postaction>") > -1) {
							postaction.replace(postaction.indexOf("</postaction>"), postaction.indexOf("</postaction>") + 13, "");
						}
						post.setCellValue(postaction.toString());
						postaction.setLength(0);
					} else if(line.contains("</SQL")) {
						isSql = false;
						Cell csql = dataRow.createCell(3);
						sql.replace(sql.indexOf("<SQL"), sql.indexOf(">") + 1, "");
						sql.replace(sql.indexOf("</SQL>"), sql.indexOf("</SQL>") + 6, "");
						
						//셀에 들어갈 수 있는 최대 문자 길이 제한에 따른 로직
						if(sql.length() > MAX_CELL_DATA_LENGTH) {
							String first = sql.substring(0, MAX_CELL_DATA_LENGTH);
							csql.setCellValue(sql.substring(0, MAX_CELL_DATA_LENGTH));
							String left = sql.substring(MAX_CELL_DATA_LENGTH);
							while(left.length() > MAX_CELL_DATA_LENGTH) {
								String cellData = left.substring(0, MAX_CELL_DATA_LENGTH);
								dataRow = sheet.createRow(rownum++);
								csql = dataRow.createCell(3);
								csql.setCellValue(cellData);
								left = left.substring(MAX_CELL_DATA_LENGTH);
							}
							dataRow = sheet.createRow(rownum++);
							csql = dataRow.createCell(3);
							csql.setCellValue(left);
						} else {
							csql.setCellValue(sql.toString());
						}
						sql.setLength(0);
					}
//					
				}
				System.out.println("=============end=============");
				System.out.println(fileno + ". " + file.getName());

				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			fileno++;
		}
		
		if(hasFile) {
			if(!resultDir.exists()) {
				resultDir.mkdirs();
			}
			try (FileOutputStream outputStream = new FileOutputStream(fileName, true);) {
			    workbook.write(outputStream);
			    workbook.close();
			    
			    System.out.println(fileName);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
		
		System.out.println("**********************************done**********************************");
		
//		for (int i = 0; i < directories.size(); i++) {
//			for (File file : directories.get(i).listFiles()) {
//				
//				System.out.println(file.getName());
//				if(file.isDirectory()) {
//					directories.add(file);
//					continue;
//				}
//			
//			}
//		}
		
	}
	
}
