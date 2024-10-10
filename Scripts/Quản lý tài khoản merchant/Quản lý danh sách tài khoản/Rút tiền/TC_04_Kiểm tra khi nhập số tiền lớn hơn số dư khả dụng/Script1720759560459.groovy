import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

def driver = DriverFactory.getWebDriver()
//Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'));
WebUI.waitForElementVisible(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/menuManagerMerchantService'),
	10, FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), GlobalVariable.waitSecond)
//WebElement menuManagerUserAccount  = wait.until{
//	it.findElement(By.xpath(GlobalVariable.xpathManagerListAccount))
//}
WebElement menuManagerUserAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))
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
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='filter-option pull-left'][normalize-space()='"+GlobalVariable.username+"']")))
driver.findElement(By.xpath(GlobalVariable.xpathViRut)).click()
driver.findElement(By.xpath(GlobalVariable.xpathOptionThuho)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathToday)).click()
//"+GlobalVariable.username+"
driver.findElement(By.xpath(GlobalVariable.xpathBtnContinue)).click()
driver.findElement(By.xpath(GlobalVariable.xpathCfBtn)).click()

WebElement soDuKhaDung = driver.findElement(By.xpath(GlobalVariable.xpathSoDuVi))
String value = soDuKhaDung.getAttribute('value')
println(">>>: "+ value)
int availableBalances = Integer.parseInt(value.replace(',', ''))
//double amountTransaction = availableBalances - 1000
println("so du kha dung la: " + availableBalances)
String amountTransaction = String.valueOf(availableBalances+9000000)
println("so du kha dung la: " + amountTransaction)
driver.findElement(By.xpath(GlobalVariable.xpathFieldAmount)).sendKeys(amountTransaction)
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Số tiền rút không được lớn hơn " + value+ "VNĐ')]"))).getText(), "Số tiền rút không được lớn hơn "+value+"VNĐ")