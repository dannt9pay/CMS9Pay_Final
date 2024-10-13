import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

def driver = DriverFactory.getWebDriver()

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 30, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'));
WebUI.waitForElementVisible(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/menuManagerMerchantService'),
	10, FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 30)
WebElement menuManagerUserAccount  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))
menuManagerUserAccount.click();

WebElement userCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUserCode)))
println(userCode.getText())
WebElement accountBtn  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnAccount)))
accountBtn.click();
WebElement searchTextbox  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathSearchTextbox)))
searchTextbox.sendKeys(GlobalVariable.username);
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathMoneyOff)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUser)))
driver.findElement(By.xpath(GlobalVariable.xpathViRut)).click()
sleep(1000)
driver.findElement(By.xpath(GlobalVariable.xpathOptionThuho)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathToday)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnContinue)).click()

//Nếu đã tồn tại kì đối soát thì thêm 3 dòng  dưới
//WebElement popupDS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathXacNhanKiDS)))
//Assert.assertEquals(popupDS.getText(), "Đã tồn tại kỳ đối soát trước đó, Bạn có chắc chắn muốn tiếp tục thực hiện giao dịch?")
//driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
try {
	WebElement popupDS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathXacNhanKiDS)))
	Assert.assertEquals(popupDS.getText(), "Đã tồn tại kỳ đối soát trước đó, Bạn có chắc chắn muốn tiếp tục thực hiện giao dịch?")
	driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
} catch (TimeoutException e) {
	println("Popup không xuất hiện, tiếp tục thực hiện giao dịch.")
}

WebElement soDuKhaDung = driver.findElement(By.xpath(GlobalVariable.xpathSoDuKhaDung))
String value = soDuKhaDung.getAttribute('value')

int availableBalances = Integer.parseInt(value.replace(',', ''))
//double amountTransaction = availableBalances - 1000
println("so du kha dung la: " + availableBalances)
String amountTransaction = String.valueOf(availableBalances+90000000)
println("so du kha dung la: " + amountTransaction)
driver.findElement(By.xpath(GlobalVariable.xpathFieldAmount)).sendKeys(amountTransaction)
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()

