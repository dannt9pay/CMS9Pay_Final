import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
int waitTime = Integer.parseInt(GlobalVariable.waitSecond)

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), waitTime, FailureHandling.STOP_ON_FAILURE)
def driver = DriverFactory.getWebDriver()

WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), waitTime)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

List<WebElement> elements = driver.findElements(By.xpath("//label[contains(.,'Nhà cung cấp EPAY')]"));
Random random = new Random();
WebElement randomElement = elements.get(random.nextInt(elements.size()));
randomElement.click()

Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),'Xác nhận thay đổi nhà cung cấp chi hộ mặc định')]")).getText(), "Xác nhận thay đổi nhà cung cấp chi hộ mặc định")

