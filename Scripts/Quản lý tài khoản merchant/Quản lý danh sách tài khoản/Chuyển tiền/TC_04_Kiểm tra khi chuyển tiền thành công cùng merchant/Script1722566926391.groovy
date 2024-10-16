import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
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
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)
WebElement startElement = driver.findElement(By.xpath(GlobalVariable.xpathChiHoBtn));
JavascriptExecutor js = (JavascriptExecutor) driver;
String script = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent = (String) js.executeScript(script, startElement);
System.out.println("chiHoWallet " + nextTextNodeContent);
def numericString = nextTextNodeContent.replaceAll("[^0-9]", "")
int chiHoBf = Integer.parseInt(numericString)
System.out.println("Chi ho " + chiHoBf);
Actions actions = new Actions(driver)
WebElement thuHoElm = driver.findElement(By.xpath(GlobalVariable.xpathThuHoBtn));
JavascriptExecutor jsBF = (JavascriptExecutor) driver;
String scriptBF = "return arguments[0].nextSibling.textContent;";
String thuHoWallet = (String) js.executeScript(scriptBF, thuHoElm);
def thuHoInt = thuHoWallet.replaceAll("[^0-9]", "")
int thuHoBf = Integer.parseInt(thuHoInt)
System.out.println("Thu ho " + thuHoBf);
driver.findElement(By.xpath(GlobalVariable.xpathTranfer)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))
driver.findElement(By.xpath(GlobalVariable.xpathTaiKhoanNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox1)).sendKeys(GlobalVariable.username)
WebElement clicked = driver.findElement(By.xpath(GlobalVariable.xpathClicked))
actions.moveToElement(clicked).click().perform()
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys(amount)
driver.findElement(By.xpath(GlobalVariable.xpathChonViNhan)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChiHo2)).click()
driver.findElement(By.xpath(GlobalVariable.xpathChonViChuyen)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo1)).click()
driver.findElement(By.xpath(GlobalVariable.xpathButtonSubmit)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAfterAccept)))

WebElement rutTien = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextTitle)));
Assert.assertEquals(rutTien.getText(), "Rút tiền")
	
WebElement approveTss = driver.findElement(By.xpath(GlobalVariable.xpathBtnDuyetGDRut))
js.executeScript("arguments[0].scrollIntoView(false);", approveTss);
approveTss.click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)
WebElement chiHoAf = driver.findElement(By.xpath(GlobalVariable.xpathChiHoBtn));
JavascriptExecutor jsChiHoAf = (JavascriptExecutor) driver;
String chihoAf = "return arguments[0].nextSibling.textContent;";
String chihointaf = (String) js.executeScript(chihoAf, chiHoAf);
System.out.println("chiHoWallet " + chihointaf);
def numericString1 = chihointaf.replaceAll("[^0-9]", "")
int chiHoAf1 = Integer.parseInt(numericString1)
System.out.println("Chi ho " + chiHoAf1);

WebElement thuHoElm1 = driver.findElement(By.xpath(GlobalVariable.xpathThuHoBtn));
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
