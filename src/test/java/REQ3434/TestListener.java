package REQ3434;

import org.testng.ITestListener;
import org.testng.ITestNGListener.*;
import org.testng.ITestResult;

import java.awt.*;


public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("I started again");
    }
}
