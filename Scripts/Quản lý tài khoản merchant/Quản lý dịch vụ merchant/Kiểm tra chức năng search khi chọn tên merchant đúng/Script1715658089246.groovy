import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 20, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/accountName'), 'dannt11');

WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/searchBtn'));

WebUI.verifyElementPresent(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/accountNameWhenSearch'), 2)

