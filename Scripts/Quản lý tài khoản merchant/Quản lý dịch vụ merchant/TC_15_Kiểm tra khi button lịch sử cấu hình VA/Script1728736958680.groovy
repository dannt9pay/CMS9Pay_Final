import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

int waitTime = Integer.parseInt(GlobalVariable.waitSecond)

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), waitTime, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

def driver = DriverFactory.getWebDriver()

WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)


driver.findElement(By.xpath("//button[contains(text(),'Lịch sử cấu hình VA')]")).click()

Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Lịch sử cấu hình chuyển mạch VA')]")).getText(), "Lịch sử cấu hình chuyển mạch VA")

//driver.findElement(By.xpath("//td[@class='text-center']//a[@href='/user/WPK14JZ4']")).click()
//driver.close()


