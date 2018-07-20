package com.loylty.automationframework.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.loylty.automationframework.TestRunner;
import com.loylty.automationframework.util.common.ConfigReader;
import com.loylty.businessobject.TestCase;


public class TestClassUtil {


	
	

	 
		@DataProvider(name = "testObjDP")
		public static Object[][] getTestDataObject(Method method) {

			String methodName = method.getName();
			Object[][] allDataSet = TestRunner.testData.get(methodName);

			Object[][] finalDataSet = new Object[allDataSet.length][1];
			int index = 0;

			for (Object[] eachDataSet : allDataSet) {
				Map<String, String> eachTestData = new HashMap<String, String>();
				TestCase testcase = new TestCase();
				testcase.setTestMethodName(methodName);
				for (Object eachData : eachDataSet) {
					String dataKeyVal = eachData.toString();
					String[] data = dataKeyVal.split("=");
					eachTestData.put(data[0], data[1]);
				}
				testcase.setTestData(eachTestData);
				finalDataSet[index++] = new Object[] { testcase };
			}

			return finalDataSet;
		}
	 
	
	@DataProvider(name = "defaultDP")
	public static Object[][] getTestData(Method method) 
	{

		String methodName = method.getName();
		Object[][] allDataSet = TestRunner.testData.get(methodName);

		if (allDataSet == null) {
			return new Object[][] { {} };
		}

		Object[][] convertedDataSet = new Object[allDataSet.length][];

		int convertedSetIndex = 0;
		for (Object[] singleDataSet : allDataSet) {// Lky, 26
			int typeIndex = 0;
			Object[] finalSingleDataSet = new Object[singleDataSet.length];

			Class[] types = method.getParameterTypes();
			for (Class eachType : types) {// types : string, int
				String elementType = eachType.getName();
				switch (elementType) {
				case "boolean":
					boolean booleanData = Boolean.valueOf(singleDataSet[typeIndex].toString());
					finalSingleDataSet[typeIndex++] = booleanData;
					break;
				case "char":
					char charData = singleDataSet[typeIndex].toString().charAt(0);
					finalSingleDataSet[typeIndex++] = charData;
					break;
				case "double":

					break;
				case "float":

					break;
				case "int":
					int intData = Integer.parseInt(singleDataSet[typeIndex].toString());
					finalSingleDataSet[typeIndex++] = intData;
					break;
				case "java.lang.String":
					String strData = singleDataSet[typeIndex].toString().split("=")[1];
					finalSingleDataSet[typeIndex++] = strData;
					break;
				case "long":

					break;
				default:

				}
			}
			convertedDataSet[convertedSetIndex++] = finalSingleDataSet;
		}
		return convertedDataSet;

	}


}
