import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
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
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'));
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount))).click();

WebElement userCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUserCode)))
println(userCode.getText())
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnAccount))).click()

WebElement searchTextbox  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathSearchTextbox)))
searchTextbox.sendKeys(GlobalVariable.username);
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathTranfer)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='filter-option pull-left'][normalize-space()='dannt11']")))
driver.findElement(By.xpath("//span[contains(text(),'Chọn tk nhận')]")).click()
driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select form-control open']//input[@type='text']")).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath("//li[@class='active']//a")).click()
driver.findElement(By.xpath("//input[@placeholder='Nhập số tiền']")).sendKeys("100000")
driver.findElement(By.xpath("//span[contains(text(),'Chọn ví nhận')]")).click()
driver.findElement(By.xpath("(//span[@class='text'][contains(text(),'Thu hộ (VND)')])[2]")).click()
driver.findElement(By.xpath("//button[contains(text(),'Xác nhận')]")).click()

Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(),'Vui lòng chọn ví chuyển.')]")).getText(), "Vui lòng chọn ví chuyển.")
