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
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), GlobalVariable.waitSecond, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Quản lý tài khoản merchant/menuManagerMerchantAccount'));
WebUI.waitForElementVisible(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/menuManagerMerchantService'),
	30, FailureHandling.STOP_ON_FAILURE)
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
sleep(1000)
driver.findElement(By.xpath(GlobalVariable.xpathOptionThuho)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathToday)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDayClicked)).click()

//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.BtnOkXpath))).click()
try {
	WebElement popupDS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathXacNhanKiDS)))
	Assert.assertEquals(popupDS.getText(), "Đã tồn tại kỳ đối soát trước đó, Bạn có chắc chắn muốn tiếp tục thực hiện giao dịch?")
	driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
} catch (TimeoutException e) {
	println("Popup không xuất hiện, tiếp tục thực hiện giao dịch.")
}

WebElement overbalance =  driver.findElement(By.xpath(GlobalVariable.xpathLabelAvailbeWallet))
String inputValue = overbalance.getAttribute("value");
def numericString = inputValue.replaceAll("[^0-9]", "")
int walletBalance = Integer.parseInt(numericString)

WebElement availbeBalance = driver.findElement(By.xpath("//label[contains(text(),'Số dư khả dụng')]/following::input[1]"))
String outputValue = availbeBalance.getAttribute("value");
def outputValueClear = outputValue.replaceAll("[^0-9]", "")
int availbeBalanceInt = Integer.parseInt(outputValueClear)

driver.findElement(By.xpath(GlobalVariable.xpathFieldAmount)).sendKeys("2100000")
driver.findElement(By.xpath("//label[contains(text(),'Chấp nhận cho rút số tiền tạm giữ')]")).click()
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

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomeFuncRutTien'), [:], FailureHandling.STOP_ON_FAILURE)

driver.findElement(By.xpath(GlobalVariable.xpathBtnAccount)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchField)).sendKeys(GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
//WebElement afterWithdraw = driver.findElement(By.xpath(GlobalVariable.xpathAvailbleBalancePayin));
//
//String script1 = "return arguments[0].nextSibling.textContent;";
//String afterWithdraw1 = (String) js.executeScript(script1, afterWithdraw);
//def numericString1 = afterWithdraw1.replaceAll("[^0-9]", "")
//int thuHo1 = Integer.parseInt(numericString1)
//int amountTransaction1 = Integer.parseInt(GlobalVariable.amountValid)
//println("amount1: " + amountTransaction1)
//println("amount2: " + walletBalance)
//println("amount3: " + thuHo1)
//
//
//if (thuHo1 == walletBalance - amountTransaction1) {
//	println("Số tiền đã đúng ")
//	KeywordUtil.markPassed("Test case đã pass với số tiền chưa được trừ khỏi tài khoản.")
//} else {
//	println("Số tiền trừ sai")
//	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
//}
driver.findElement(By.xpath(GlobalVariable.xpathEditUser)).click()
driver.findElement(By.xpath(GlobalVariable.xpathTTHD)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSoTienTamGiu)).clear()
driver.findElement(By.xpath(GlobalVariable.xpathSoTienTamGiu)).sendKeys("0")
driver.findElement(By.xpath(GlobalVariable.xpathCapNhat)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()
