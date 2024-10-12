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


RunConfiguration.setExecutionSettingFile('/var/folders/t5/b7k67ybj7gz9rv6nyq7t561w0000gn/T/Katalon/Test Cases/Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n merchant/Qu\u1EA3n l\u00FD danh s\u00E1ch t\u00E0i kho\u1EA3n/N\u1EA1p ti\u1EC1n/TC_10_Ki\u1EC3m tra khi ch\u1ECDn v\u00ED \u0111\u00FAng v\u00E0 ch\u1ECDn lo\u1EA1i giao d\u1ECBch l\u00E0 ho\u00E0n ph\u00ED/20241012_231334/execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Nạp tiền/TC_10_Kiểm tra khi chọn ví đúng và chọn loại giao dịch là hoàn phí', new TestCaseBinding('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/Nạp tiền/TC_10_Kiểm tra khi chọn ví đúng và chọn loại giao dịch là hoàn phí',[:]), FailureHandling.STOP_ON_FAILURE , false)
    
