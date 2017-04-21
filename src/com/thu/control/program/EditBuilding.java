package com.thu.control.program;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thu.control.bean.BuildingBean;
import com.thu.control.dao.BuildingDAO;

public class EditBuilding {
	private static File f;

	public EditBuilding(File f) {
		this.f = f;
	}
/*
	public Map<String, ArrayList> start() {
		Map<String, ArrayList> rMap = new HashMap();
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
			XSSFSheet buildingSheet = myWorkBook.getSheetAt(0);
			XSSFSheet RoomSheet = myWorkBook.getSheetAt(1);
			Iterator<Row> rowIterator = buildingSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row1 = rowIterator.next();
				if (row1.getRowNum() == 0)
					continue;
				String BuildingName = row1.getCell(0).toString();
				String BuildingEn = row1.getCell(1).toString();
				String floor = row1.getCell(2).toString();
				String roomName = row1.getCell(3).toString();
				String roomRemark = row1.getCell(4).toString();
				ArrayList<String> dArray = new ArrayList();
				dArray.add(BuildingName);
				dArray.add(BuildingEn);
				dArray.add(floor);
				dArray.add(roomName);
				dArray.add(roomRemark);

				BuildingDAO dao = new BuildingDAO();
				if (floor == null) {
					int result = dao.putBuilding(dArray);
					rMap = branchResult(result, dArray, rMap);
				} else if (roomName == null) {
					if (BuildingName == null || BuildingEn == null) {
						rMap = branchResult(3, dArray, rMap);
					} else {
						int result = dao.putFloor(dArray);
						rMap = branchResult(result, dArray, rMap);
					}
				} else {
					if (BuildingName == null || BuildingEn == null) {
						rMap = branchResult(3, dArray, rMap);
					} else{
						
					}
				}
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rMap;
	}

	private Map<String, ArrayList> branchResult(int result, ArrayList dArray, Map<String, ArrayList> rMap) {
		if (result == 0) {
			if (rMap.containsKey("insert")) {
				ArrayList array = rMap.get("insert");
				array.add(dArray);
				rMap.put("insert", array);
			} else {
				rMap.put("insert", dArray);
			}
		} else if (result == 1) {
			if (rMap.containsKey("update")) {
				ArrayList array = rMap.get("update");
				array.add(dArray);
				rMap.put("update", array);
			} else {
				rMap.put("update", dArray);
			}
		} else if (result == 2) {
			if (rMap.containsKey("ambigious")) {
				ArrayList array = rMap.get("ambigious");
				array.add(dArray);
				rMap.put("ambigious", array);
			} else {
				rMap.put("ambigious", dArray);
			}
		} else {
			if (rMap.containsKey("error")) {
				ArrayList array = rMap.get("error");
				array.add(dArray);
				rMap.put("error", array);
			} else {
				rMap.put("error", dArray);
			}
		}
		return rMap;
	}*/
}
