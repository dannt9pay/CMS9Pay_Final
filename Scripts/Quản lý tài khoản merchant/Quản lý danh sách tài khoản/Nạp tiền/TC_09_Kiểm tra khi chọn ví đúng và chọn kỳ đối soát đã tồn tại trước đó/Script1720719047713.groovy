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

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 20, FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions Deposit'), [:], FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)
WebElement userCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUserCode)))
println(userCode.getText())
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnAccount)))click()
WebElement searchTextbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathSearchTextbox)))
searchTextbox.sendKeys('dannt11')
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathAccountName)))
WebElement startElement = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));
JavascriptExecutor js = (JavascriptExecutor) driver;
String script = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent = (String) js.executeScript(script, startElement);
System.out.println("Thu ho " + nextTextNodeContent);
def numericString = nextTextNodeContent.replaceAll("[^0-9]", "")
int thuHo = Integer.parseInt(numericString)
System.out.println("Thu ho " + thuHo);
driver.findElement(By.xpath(GlobalVariable.xpathBtnDeposit)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAccount)))

driver.findElement(By.xpath(GlobalVariable.xpathChonVi)).click()
driver.findElement(By.xpath(GlobalVariable.xpathThuHo3)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
//driver.findElement(By.xpath(GlobalVariable.xpathHomQua)).click()
driver.findElement(By.xpath(GlobalVariable.xpathHomNay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnContinue)).click()
//driver.findElement(By.xpath("//button[contains(text(),'Đồng ý')]")).click()
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys("150000")
driver.findElement(By.xpath(GlobalVariable.xpathButtonSubmit)).click()

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathButtonApprove)))
WebElement startElement1 = driver.findElement(By.xpath(GlobalVariable.xpathButtonApprove))
js.executeScript("arguments[0].scrollIntoView(false);", startElement1);
driver.findElement(By.xpath(GlobalVariable.xpathButtonApprove)).click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.xpathCfBtn)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
String loaiGiaodich = driver.findElement(By.xpath(GlobalVariable.xpathNapTien)).getText()
Assert.assertEquals(loaiGiaodich, "Nạp tiền")

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)

WebElement startElementAf = driver.findElement(By.xpath(GlobalVariable.xpathThuHoBtn));
JavascriptExecutor jsAf = (JavascriptExecutor) driver;
String scriptAf = "return arguments[0].nextSibling.textContent;";
String balanceStrAf = (String) jsAf.executeScript(scriptAf, startElementAf);
//System.out.println("Thu ho " + balanceStrAf);
def numericStringAf = balanceStrAf.replaceAll("[^0-9]", "")
int balanceAf = Integer.parseInt(numericStringAf)
System.out.println("Thu ho " + balanceAf);

if (balanceAf == thuHo + 150000) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền đã được cộng đúng.")
} else {
	println("Số tiền chưa được cộng đúng")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}