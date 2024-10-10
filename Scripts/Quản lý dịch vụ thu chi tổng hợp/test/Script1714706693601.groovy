import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.Assert
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


WebUI.openBrowser('')

WebUI.maximizeWindow();
def driver = DriverFactory.getWebDriver()

WebUI.navigateToUrl('https://stg-console.9pay.mobi/admin')

driver.findElement(By.xpath("//input[@placeholder='Tài khoản']")).sendKeys("autotest")

driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("autotest@9s.vn")

driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click()

sleep(5)
driver.findElement(By.xpath("//a[contains(text(),'Quản lý giao dịch')]")).click()
sleep(3)

driver.findElement(By.xpath("//a[@href='/admin/transaction'][contains(text(),'Xem lịch sử giao dịch')]")).click()

driver.findElement(By.xpath("//input[@placeholder='Mã giao dịch']")).sendKeys("ZGL56X4D")

driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]")).click()

driver.findElement(By.xpath("//a[contains(text(),'Hoàn tiền')]")).click()

Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Giao dịch đã được yêu cầu hoàn')]")).getText(), "Giao dịch đã được yêu cầu hoàn. Vui lòng chờ.")