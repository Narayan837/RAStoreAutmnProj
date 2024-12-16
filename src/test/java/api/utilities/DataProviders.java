package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		String path = "./testdata/Userdata.xlsx"; // taking xl file from "testData" folder

		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for xlutility and giving path

		int rownum = xlutil.getRowCount("sheet1");
		int colcount = xlutil.getCellCount("sheet1", 1);

		String apidata[][] = new String[rownum][colcount]; // created for 2Dimensional(2D) array which can store

		for (int i = 1; i <= rownum; i++) { // 1 //read the data from xl storing in 2D array
			for (int j = 0; j < colcount; j++) { // 0 // 1 is row, j is col
				apidata[i - 1][j] = xlutil.getCellData("sheet1", i, j); // 1,0
			}
		}
		return apidata; // returning 2D array

	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = "./testdata/Userdata.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);

		int rownum = xlutil.getRowCount("sheet1");

		String apidata[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) { // 1 //read the data from xl storing in 2D array
			apidata[i - 1] = xlutil.getCellData("sheet1", i, 1); // 1,0
		}
		return apidata;
	}

}
