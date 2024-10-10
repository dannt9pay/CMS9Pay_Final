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


RunConfiguration.setExecutionSettingFile('/var/folders/2z/363n2ch977s6j1bhk_j3lpcw0000gq/T/Katalon/Test Cases/Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n merchant/Qu\u1EA3n l\u00FD danh s\u00E1ch t\u00E0i kho\u1EA3n/R\u00FAt ti\u1EC1n/TC_05_Ki\u1EC3m tra khi nh\u1EADp s\u1ED1 ti\u1EC1n b\u00E9 h\u01A1n s\u1ED1 ti\u1EC1n kh\u1EA3 d\u1EE5ng/20241010_163857/execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Rút tiền/TC_05_Kiểm tra khi nhập số tiền bé hơn số tiền khả dụng', new TestCaseBinding('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Rút tiền/TC_05_Kiểm tra khi nhập số tiền bé hơn số tiền khả dụng',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
