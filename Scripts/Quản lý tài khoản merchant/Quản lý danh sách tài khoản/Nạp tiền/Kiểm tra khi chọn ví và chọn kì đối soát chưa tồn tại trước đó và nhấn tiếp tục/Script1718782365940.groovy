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
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'))
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))click()
WebElement userCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUserCode)))
println(userCode.getText())
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnAccount)))click()
WebElement searchTextbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathSearchTextbox)))
searchTextbox.sendKeys('dannt11')
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
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

driver.findElement(By.xpath("//span[contains(text(),'Chọn ví')]")).click()
driver.findElement(By.xpath("//span[contains(text(),'Thu hộ (VND)')]")).click()
driver.findElement(By.xpath("//input[@placeholder='Chọn ngày']")).click()
driver.findElement(By.xpath("//li[normalize-space()='Hôm nay']")).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnContinue)).click()
//driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDepositTextbox)).sendKeys("150000")
driver.findElement(By.xpath(GlobalVariable.xpathButtonSubmit)).click()

wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathTextAfterAccept))).isDisplayed()

driver.findElement(By.xpath(GlobalVariable.xpathButtonApprove)).click()
driver.findElement(By.xpath(GlobalVariable.xpathCfBtn)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()

String monney = driver.findElement(By.xpath("//th[contains(text(),'Số dư sau GD')]//following-sibling::td")).getText()

def moneyString = monney.replaceAll("[^0-9]", "")
int moneyEnd = Integer.parseInt(moneyString)
println(">>> so tien la: " + moneyEnd)
if (moneyEnd == thuHo + 150000) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền đã được cộng đúng.")
} else {
	println("Số tiền chưa được cộng đúng")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}
