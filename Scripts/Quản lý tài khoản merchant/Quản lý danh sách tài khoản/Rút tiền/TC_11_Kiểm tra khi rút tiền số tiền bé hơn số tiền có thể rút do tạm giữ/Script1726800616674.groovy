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
JavascriptExecutor js = (JavascriptExecutor) driver;
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), GlobalVariable.waitSecond, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'));
WebUI.waitForElementVisible(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/menuManagerMerchantService'),
	10, FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), GlobalVariable.waitSecond)
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
//Nếu chưa config thì check CMS 
WebElement lockBalance = driver.findElement(By.xpath("//strong[contains(text(),'Số dư không được rút:')]"));

String script = "return arguments[0].nextSibling.textContent;";
String lockBalanceAf = (String) js.executeScript(script, lockBalance);
String result = lockBalanceAf.replace("VNĐ", "").trim();
def lockBalanceAf1 = lockBalanceAf.replaceAll("[^0-9]", "")
int lockBalanceBf = Integer.parseInt(lockBalanceAf1)
System.out.println("Thu ho " + lockBalanceBf);

Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathBtnRutTien)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUser)))

driver.findElement(By.xpath(GlobalVariable.xpathViRut)).click()
driver.findElement(By.xpath(GlobalVariable.xpathOptionThuho)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathToday)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDayClicked)).click()

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.BtnOkXpath))).click()

WebElement overbalance =  driver.findElement(By.xpath(GlobalVariable.xpathLabelAvailbeWallet))
String inputValue = overbalance.getAttribute("value");
def numericString = inputValue.replaceAll("[^0-9]", "")
int walletBalance = Integer.parseInt(numericString)

WebElement availbeBalance = driver.findElement(By.xpath("//label[contains(text(),'Số dư khả dụng')]/following::input[1]"))
String outputValue = availbeBalance.getAttribute("value");
def outputValueClear = outputValue.replaceAll("[^0-9]", "")
int availbeBalanceInt = Integer.parseInt(outputValueClear)

driver.findElement(By.xpath(GlobalVariable.xpathFieldAmount)).sendKeys(GlobalVariable.amountValid)

driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()

WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathAcceptFirst)))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton);
approveButton.click()
Thread.sleep(1000)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.BtnOkXpath))).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
driver.navigate().refresh()

WebElement approveButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathAcceptSecond)))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton1);
approveButton1.click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
driver.navigate().refresh()

WebElement approveButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathAcceptThird)))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton2);
approveButton2.click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
driver.navigate().refresh()

WebElement approveButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathAcceptFinally)))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton3);
approveButton3.click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)
//String scriptClicked = "document.evaluate(\"//*[text()='Quản lý tài khoản merchant']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
//js.executeScript(scriptClicked);
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount))).click()

driver.findElement(By.xpath(GlobalVariable.xpathBtnAccount)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchField)).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
WebElement afterWithdraw = driver.findElement(By.xpath(GlobalVariable.xpathAvailbleBalancePayin));

String script1 = "return arguments[0].nextSibling.textContent;";
String afterWithdraw1 = (String) js.executeScript(script1, afterWithdraw);
def numericString1 = afterWithdraw1.replaceAll("[^0-9]", "")
int thuHo1 = Integer.parseInt(numericString1)
int amountTransaction1 = Integer.parseInt(GlobalVariable.amountValid)
if (thuHo1 == walletBalance - amountTransaction1) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền chưa được trừ khỏi tài khoản.")
} else {
	println("Số tiền trừ sai")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}

