import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'))

WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10)

WebUI.click(findTestObject('Quản lý tài khoản merchant/menuQLTKNT'))

WebUI.click(findTestObject('Quản lý tài khoản merchant/btnTK'))

WebUI.setText(findTestObject('Quản lý tài khoản merchant/textBox'), Search)

WebUI.click(findTestObject('Quản lý tài khoản merchant/btnCl'))

