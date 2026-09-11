package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports configReport(){
        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter rep = new ExtentSparkReporter(path);
        rep.config().setReportName("E2E Automation Report");
        rep.config().setDocumentTitle("E2E Report");

        ExtentReports ext  = new ExtentReports();
        ext.attachReporter(rep);
        ext.setSystemInfo("Tester","Auto tester");
        return ext;
    }
}
