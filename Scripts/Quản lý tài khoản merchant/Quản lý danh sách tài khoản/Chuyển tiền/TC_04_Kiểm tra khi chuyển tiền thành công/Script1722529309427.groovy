import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

def driver = DriverFactory.getWebDriver()
String amount = 100000;
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15)
WebElement startElement = driver.findElement(By.xpath("//strong[contains(text(),'Chi hộ:')]"));
JavascriptExecutor js = (JavascriptExecutor) driver;
String script = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent = (String) js.executeScript(script, startElement);
System.out.println("chiHoWallet " + nextTextNodeContent);
def numericString = nextTextNodeContent.replaceAll("[^0-9]", "")
int chiHoBf = Integer.parseInt(numericString)
System.out.println("Chi ho " + chiHoBf);

WebElement thuHoElm = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));
JavascriptExecutor jsBF = (JavascriptExecutor) driver;
String scriptBF = "return arguments[0].nextSibling.textContent;";
String thuHoWallet = (String) js.executeScript(scriptBF, thuHoElm);
def thuHoInt = thuHoWallet.replaceAll("[^0-9]", "")
int thuHoBf = Integer.parseInt(thuHoInt)
System.out.println("Thu ho " + thuHoBf);
driver.findElement(By.xpath(GlobalVariable.xpathTranfer)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='filter-option pull-left'][normalize-space()='dannt11']")))
driver.findElement(By.xpath("//span[contains(text(),'Chọn tk nhận')]")).click()
driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select form-control open']//input[@type='text']")).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath("//li[@class='active']//a")).click()
driver.findElement(By.xpath("//input[@placeholder='Nhập số tiền']")).sendKeys(amount)
driver.findElement(By.xpath("//span[contains(text(),'Chọn ví nhận')]")).click()
driver.findElement(By.xpath("(//span[@class='text'][contains(text(),'Chi hộ (VND)')])[2]")).click()
driver.findElement(By.xpath("//span[contains(text(),'Chọn ví chuyển')]")).click()
driver.findElement(By.xpath("(//span[@class='text'][contains(text(),'Thu hộ (VND)')])[1]")).click()
driver.findElement(By.xpath("//button[contains(text(),'Xác nhận')]")).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THÔNG TIN GIAO DỊCH')]")))

WebElement rutTien = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Rút tiền')]")));
Assert.assertEquals(rutTien.getText(), "Rút tiền")
	
WebElement approveTss = driver.findElement(By.xpath("//button[contains(text(),'DUYỆT GIAO DỊCH RÚT')]"))
js.executeScript("arguments[0].scrollIntoView(false);", approveTss);
approveTss.click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)
WebElement chiHoAf = driver.findElement(By.xpath("//strong[contains(text(),'Chi hộ:')]"));
JavascriptExecutor jsChiHoAf = (JavascriptExecutor) driver;
String chihoAf = "return arguments[0].nextSibling.textContent;";
String chihointaf = (String) js.executeScript(chihoAf, chiHoAf);
System.out.println("chiHoWallet " + chihointaf);
def numericString1 = chihointaf.replaceAll("[^0-9]", "")
int chiHoAf1 = Integer.parseInt(numericString1)
System.out.println("Chi ho " + chiHoAf1);

WebElement thuHoElm1 = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));
JavascriptExecutor jsAf = (JavascriptExecutor) driver;
String scriptAf = "return arguments[0].nextSibling.textContent;";
String thuHoWalletAf = (String) js.executeScript(scriptAf, thuHoElm1);
def thuHoIntAf = thuHoWalletAf.replaceAll("[^0-9]", "")
int thuHoAf = Integer.parseInt(thuHoIntAf)
int amountInt = Integer.parseInt(amount);
System.out.println("Thu ho " + thuHoAf);
if (chiHoBf + amountInt == chiHoAf1 && thuHoAf + amountInt == thuHoBf) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền đã được cộng đúng.")
} else {
	println("Số tiền chưa được cộng đúng")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}
