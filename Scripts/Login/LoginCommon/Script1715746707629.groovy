import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
def driver = DriverFactory.getWebDriver()
WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/userNameTextbox'), GlobalVariable.userAccount)

WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/passwordTextbox'), GlobalVariable.password)

WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/loginButton'));

