import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

def driver = DriverFactory.getWebDriver()
String amount = 100000;
String amountMax = 9999999999;
Actions actions = new Actions(driver)
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)

//Chỉ chọn tài khoản nhận và để trống tất cả các trường còn lại
driver.findElement(By.xpath(GlobalVariable.xpathTranfer)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)

WebElement clicked = driver.findElement(By.xpath(GlobalVariable.xpathClicked))
actions.moveToElement(clicked).click().perform()
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen1)).getText(), "Vui lòng chọn ví chuyển.")
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathNhapSoTienChuyen)).getText(), "Vui lòng nhập số tiền chuyển.")
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan1)).getText(), "Vui lòng chọn ví nhận.")

//Để trống ví chuyển và nhấn xác nhận
driver.navigate().refresh()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys(amount)
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathClicked)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo)).click()
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen1)).getText(), "Vui lòng chọn ví chuyển.")

//Để trống trường ví nhận và nhấn xác nhận
driver.navigate().refresh()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys(amount)
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)
WebElement click3 =  driver.findElement(By.xpath(GlobalVariable.xpathClicked))
actions.moveToElement(click3).click().perform()
driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo1)).click()
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan1)).getText(), "Vui lòng chọn ví nhận.")

//Không nhập số tiền
driver.navigate().refresh()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathClicked)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo1)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo)).click()
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathNhapSoTienChuyen)).getText(), "Vui lòng nhập số tiền chuyển.")

//Chuyển số tiền lớn hơn số tiền khả dụng
driver.navigate().refresh()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathClicked)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys(amountMax)
driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChiHo2)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo1)).click()
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathKhongDuTien)).getText(), "Tài khoản ví không đủ tiền để thực hiện giao dịch")

//Chọn cùng ví khi chung 1 tài khoản MC
driver.navigate().refresh()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathClicked)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys(amount)
driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo1)).click()
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDepositOkBtn)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathViNhanKhacViChuyen)).getText(), "Ví nhận phải khác ví chuyển")

