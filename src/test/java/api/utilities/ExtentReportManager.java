package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		/*
		 * SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=new
		 * Date(); String currentdatetimestamp = df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter("./reports/" + repName);

		sparkReporter.config().setDocumentTitle("RestAssured Automation Project Report");
		sparkReporter.config().setReportName("Pet Store Users API");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("user", "Narayan");

//		String os = testContext.getCurrentXmlTest().getParameter("os");
//		extent.setSystemInfo("Operating System", os);
//
//		String browser = testContext.getCurrentXmlTest().getParameter("browser");
//		extent.setSystemInfo("Browser", browser);
//		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
//		if (!includeGroups.isEmpty()) {
//			extent.setSystemInfo("Groups", includeGroups.toString());
//		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups()); // To display groups in report
		test.log(Status.PASS, "Test Passed");

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, " Test failed ");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped ");
		test.log(Status.SKIP, result.getThrowable().getMessage());

	}

//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//
//	}
//
//	@Override
//	public void onTestFailedWithTimeout(ITestResult result) {
//
//	}
//
//	@Override
//	public void onStart(ITestContext context) {
//
//	}

	public void onFinish(ITestContext context) {
		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

//		try {
//			URL url = new URL("file:///" + System.getProperty("user.dir") + "/reports/" + repName);
//			// Create the email message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.google.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("amanob@gmail.com", "Aman@123"));
//			email.setSSLOnConnect(true);
//			email.setFrom("amanob@gmail.com"); // Sender
//			email.setSubject("Test Results");
//			email.setMsg("Please find Attached Report.....");
//			email.addTo("lsahu4153@gmail.com"); // Receiver(group of email in one time)
//			email.attach(url, "extent report", "Please check report...");
//			email.send(); // send email
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
