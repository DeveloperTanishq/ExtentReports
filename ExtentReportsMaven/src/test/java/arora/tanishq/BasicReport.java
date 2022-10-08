package arora.tanishq;

import java.awt.Desktop;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BasicReport {

	public static void main(String[] args) throws Exception {
		
		ExtentReports extentReports = new ExtentReports();
		
		//creating file instance
		
		File file = new File("basicReport.html");
		
		//entering abs path where we want to store our report.
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		
		//attaching the reporter to the reports instance.
		extentReports.attachReporter(sparkReporter);
		
		//All the test cases should be created under extent reports instance.
		
		ExtentTest testCase1 = extentReports.createTest("Test 1");
		testCase1.pass("This Test Case is passed");
		
		ExtentTest testCase2 = extentReports.createTest("Test 2");
		testCase2.log(Status.FAIL,"This Test Case is passed");
		
		extentReports.createTest("Test 3").skip("This Test case is Skipped");
		
		//logging different types of information
		
		extentReports.createTest("Text Based Testing")
		.log(Status.INFO, "data1")
		.log(Status.INFO, "<b>data2</b>")
		.log(Status.INFO, "<i>data3</i>");
		
		String xmlData = "<note>\r\n"
				+ "  <date>2015-09-01</date>\r\n"
				+ "  <hour>08:30</hour>\r\n"
				+ "  <to>Tove</to>\r\n"
				+ "  <from>Jani</from>\r\n"
				+ "  <body>Don't forget me this weekend!</body>\r\n"
				+ "</note>";
		
		String jsonData = "{\r\n"
				+ "\"employee\":{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}\r\n"
				+ "}";
		
		extentReports
		.createTest("XML based test")
		.info(MarkupHelper.createCodeBlock(xmlData,CodeLanguage.XML));
		
		extentReports
		.createTest("JSON based test")
		.info(MarkupHelper.createCodeBlock(jsonData,CodeLanguage.JSON));
		
		extentReports.flush();
		
		//Automating the file browsing.
		Desktop.getDesktop().browse(new File("basicReport.html").toURI());		
	}

}
