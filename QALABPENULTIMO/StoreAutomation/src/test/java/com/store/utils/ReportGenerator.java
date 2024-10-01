package com.store.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportGenerator {

    ExtentReports extent;

    public void setupReport() {
        // Usamos ExtentSparkReporter para generar reportes HTML
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public void generateReport(String testName, boolean passed) {
        ExtentTest test = extent.createTest(testName);
        if (passed) {
            test.pass("Test passed");
        } else {
            test.fail("Test failed");
        }
    }

    public void flushReport() {
        extent.flush();  // Genera el reporte final
    }
}