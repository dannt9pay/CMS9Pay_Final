import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.TimeoutException
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
JavascriptExecutor js = (JavascriptExecutor) driver;
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'));
WebUI.waitForElementVisible(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/menuManagerMerchantService'),
	10, FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), GlobalVariable.waitSecond)
WebElement menuManagerUserAccount  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))
menuManagerUserAccount.click();

WebElement userCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUserCode)))
//println(userCode.getText())
WebElement accountBtn  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnAccount)))
accountBtn.click();
WebElement searchTextbox  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathSearchTextbox)))
searchTextbox.sendKeys(GlobalVariable.username);
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
WebElement startElement = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));

String script = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent = (String) js.executeScript(script, startElement);
System.out.println("Thu ho " + nextTextNodeContent);
def numericString = nextTextNodeContent.replaceAll("[^0-9]", "")
int thuHo = Integer.parseInt(numericString)
System.out.println("Thu ho " + thuHo);

Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathMoneyOff)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='filter-option pull-left'][normalize-space()='"+GlobalVariable.username+"']")))
driver.findElement(By.xpath(GlobalVariable.xpathViRut)).click()
driver.findElement(By.xpath(GlobalVariable.xpathOptionThuho)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathToday)).click()

driver.findElement(By.xpath(GlobalVariable.xpathBtnContinue)).click()
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
//println("so du kha dung la: " + availableBalances)
String amountTransaction = 100000
//println("so du kha dung la: " + amountTransaction)
driver.findElement(By.xpath(GlobalVariable.xpathFieldAmount)).sendKeys(amountTransaction)
driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()

WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathAcceptFirst)))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton);
approveButton.click()
Thread.sleep(2000)
driver.findElement(By.xpath(GlobalVariable.xpathCfBtn)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomeFuncRutTien'), [:], FailureHandling.STOP_ON_FAILURE)
//WebElement element1 = driver.findElement(By.xpath(GlobalVariable.xpathManagerMC));
//Actions actions = new Actions(driver)
//actions.moveToElement(element1).click().perform()
////actions.moveToElement(element1).click().perform()
////WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)
//WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerMC))).click()
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount))).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnAccount)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchField)).sendKeys(GlobalVariable.username);
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
WebElement startElement1 = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));

String script1 = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent1 = (String) js.executeScript(script1, startElement1);

if (nextTextNodeContent1 == nextTextNodeContent) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền chưa được trừ khỏi tài khoản.")
} else {
	println("Số tiền trừ sai")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}
