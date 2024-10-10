import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

def driver = DriverFactory.getWebDriver()
JavascriptExecutor js = (JavascriptExecutor) driver;
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Quản lý dịch vụ Thu Chi tổng hợp/QuanLyThuChiTongHop'))
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathQuanlyGDThuChiHo))).click();
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathCotHienThi))).click();
driver.findElement(By.xpath(GlobalVariable.xpathCheckTTDT)).click()
driver.findElement(By.xpath(GlobalVariable.xpathCapNhatCHT)).click()

driver.findElement(By.xpath(GlobalVariable.xpathLoaiGD)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChiHo)).click()
driver.findElement(By.xpath(GlobalVariable.xpathTrangThaiDT)).click()
driver.findElement(By.xpath(GlobalVariable.xpathTrangThaiDXL)).click()
driver.findElement(By.xpath(GlobalVariable.xpathTimKiem)).click()

List<WebElement> elements = driver.findElements(By.xpath(GlobalVariable.listWebElement))
if (elements.size() > 0) {
	Random random = new Random()
	WebElement randomElement = elements.get(random.nextInt(elements.size()))
	js.executeScript("arguments[0].scrollIntoView(true);", randomElement)
	js.executeScript("arguments[0].click();", randomElement)
} else {
	 println("Không tìm thấy phần tử nào!")
}

String maGD = driver.findElement(By.xpath(GlobalVariable.xpathMaGD)).getText()
sleep(1000)
WebElement btnUpdateFail = driver.findElement(By.xpath(GlobalVariable.xpathBtnUpdateFail))
js.executeScript("arguments[0].scrollIntoView(true);", btnUpdateFail)
driver.findElement(By.xpath(GlobalVariable.xpathBtnUpdateSuccess)).click()

driver.findElement(By.xpath(GlobalVariable.xpathBtnDongY)).click()
driver.navigate().refresh()
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnThanhCongGDCha))).click();
sleep(1000)
driver.findElement(By.xpath(GlobalVariable.xpathBtnDongY)).click()
Assert.assertEquals(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(GlobalVariable.xpathTrangThai))).getText(), "Thành công")