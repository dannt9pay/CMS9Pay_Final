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


RunConfiguration.setExecutionSettingFile('/var/folders/t5/b7k67ybj7gz9rv6nyq7t561w0000gn/T/Katalon/Test Cases/Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n merchant/Qu\u1EA3n l\u00FD danh s\u00E1ch t\u00E0i kho\u1EA3n/R\u00FAt ti\u1EC1n/TC_13_Ki\u1EC3m tra khi r\u00FAt ti\u1EC1n s\u1ED1 ti\u1EC1n l\u1EDBn h\u01A1n s\u1ED1 ti\u1EC1n c\u00F3 th\u1EC3 r\u00FAt do t\u1EA1m gi\u1EEF v\u00E0 ch\u1ECDn ch\u1EA5p nh\u1EADn cho r\u00FAt s\u1ED1 ti\u1EC1n t\u1EA1m gi\u1EEF/20241013_233556/execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Rút tiền/TC_13_Kiểm tra khi rút tiền số tiền lớn hơn số tiền có thể rút do tạm giữ và chọn chấp nhận cho rút số tiền tạm giữ', new TestCaseBinding('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Rút tiền/TC_13_Kiểm tra khi rút tiền số tiền lớn hơn số tiền có thể rút do tạm giữ và chọn chấp nhận cho rút số tiền tạm giữ',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
