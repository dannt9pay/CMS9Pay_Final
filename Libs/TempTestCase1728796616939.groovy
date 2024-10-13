import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner
import com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner
import com.kms.katalon.core.windows.keyword.contribution.WindowsDriverCleaner
import com.kms.katalon.core.testng.keyword.internal.TestNGDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.windows.keyword.contribution.WindowsDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.testng.keyword.internal.TestNGDriverCleaner())


RunConfiguration.setExecutionSettingFile('/var/folders/t5/b7k67ybj7gz9rv6nyq7t561w0000gn/T/Katalon/Test Cases/Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n merchant/Qu\u1EA3n l\u00FD danh s\u00E1ch t\u00E0i kho\u1EA3n/R\u00FAt ti\u1EC1n/TC_08_Ki\u1EC3m tra user ch\u01B0a \u0111\u0103ng k\u00FD d\u1ECBch v\u1EE5 v\u00E0 ch\u1ECDn k\u00EC \u0111\u1ED1i so\u00E1t ch\u01B0a t\u1ED3n t\u1EA1i/20241013_121656/execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Rút tiền/TC_08_Kiểm tra user chưa đăng ký dịch vụ và chọn kì đối soát chưa tồn tại', new TestCaseBinding('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Rút tiền/TC_08_Kiểm tra user chưa đăng ký dịch vụ và chọn kì đối soát chưa tồn tại',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
