import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/userNameTextbox'), GlobalVariable.userAccount)

WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/passwordTextbox'), GlobalVariable.password)

WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/loginButton'));
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'))

WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10)

WebElement menuManagerUserAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))

menuManagerUserAccount.click()

def driver = DriverFactory.getWebDriver()
driver.findElement(By.xpath("//a[contains(text(),'Quản lý TK nhận tiền merchant')]")).click()

String texxt = driver.findElement(By.xpath("//span[@class='font-bold col-teal']")).getText()

System.out.println("Thu ho " + texxt);
