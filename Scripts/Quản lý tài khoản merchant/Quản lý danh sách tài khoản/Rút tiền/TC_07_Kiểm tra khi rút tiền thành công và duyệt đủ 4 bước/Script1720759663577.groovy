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
WebElement beforeWithdrawing = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));

String script = "return arguments[0].nextSibling.textContent;";
String beforeWithdrawing1 = (String) js.executeScript(script, beforeWithdrawing);
System.out.println("Thu ho " + beforeWithdrawing1);
def numericString = beforeWithdrawing1.replaceAll("[^0-9]", "")
int thuHo = Integer.parseInt(numericString)
System.out.println("Thu ho " + thuHo);

Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
driver.findElement(By.xpath("//i[normalize-space()='money_off']")).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='filter-option pull-left'][normalize-space()='"+GlobalVariable.username+"']")))
driver.findElement(By.xpath("//span[contains(text(),'Chọn ví rút')]")).click()
driver.findElement(By.xpath("//span[contains(text(),'Thu hộ (VND)')]")).click()
driver.findElement(By.xpath("//input[@placeholder='Chọn ngày']")).click()
driver.findElement(By.xpath("//li[normalize-space()='Hôm nay']")).click()

driver.findElement(By.xpath("//div[@class='btn btn-lg btn-primary waves-effect']")).click()
driver.findElement(By.xpath("//button[contains(text(),'Đồng ý')]")).click()

WebElement soDuKhaDung = driver.findElement(By.xpath("//label[contains(text(),'Số dư khả dụng')]//parent::div//following-sibling::div//input"))
String value = soDuKhaDung.getAttribute('value')

int availableBalances = Integer.parseInt(value.replace(',', ''))
println("so du kha dung la: " + availableBalances)
//Chỗ này nếu số dư lớn hơn thì check lại phần trừ đi này 
int amountTransaction = 2000000
println("so du kha dung la: " + amountTransaction)
if (amountTransaction > availableBalances) {
	amountTransaction = availableBalances; // Gán số tiền rút bằng số dư khả dụng
	println("Số tiền rút được điều chỉnh lại là: " + amountTransaction);
}
driver.findElement(By.xpath("//input[@name='amount']")).sendKeys(String.valueOf(amountTransaction))
driver.findElement(By.xpath("//button[contains(text(),'Xác nhận')]")).click()

WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'VH DUYỆT GD RÚT LẦN 1')]")))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton);
approveButton.click()
Thread.sleep(1000)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Đồng ý')]"))).click()
driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click()
driver.navigate().refresh()
WebElement approveButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'VH DUYỆT GD RÚT LẦN 2')]")))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton1);
approveButton1.click()
Thread.sleep(1000)
driver.findElement(By.xpath("//button[contains(text(),'Đồng ý')]")).click()
driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click()
driver.navigate().refresh()
WebElement approveButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'KT DUYỆT GD RÚT LẦN 1')]")))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton2);
approveButton2.click()
Thread.sleep(1000)
driver.findElement(By.xpath("//button[contains(text(),'Đồng ý')]")).click()
driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click()
driver.navigate().refresh()
WebElement approveButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'KT DUYỆT GD RÚT LẦN 2')]")))
js.executeScript("arguments[0].scrollIntoView(true);", approveButton3);
approveButton3.click()
Thread.sleep(1000)
driver.findElement(By.xpath("//button[contains(text(),'Đồng ý')]")).click()
driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click()
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomeFuncRutTien'), [:], FailureHandling.STOP_ON_FAILURE)
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Quản lý tài khoản merchant')]"))).click()
//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Quản lý danh sách tài khoản')]"))).click()
driver.findElement(By.xpath("//span[contains(text(),'Tài khoản')]")).click()
driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(GlobalVariable.username);
driver.findElement(By.xpath(GlobalVariable.xpathOption)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
WebElement afterWithdraw = driver.findElement(By.xpath("//strong[contains(text(),'Thu hộ:')]"));

String script1 = "return arguments[0].nextSibling.textContent;";
String afterWithdraw1 = (String) js.executeScript(script1, afterWithdraw);
def numericString1 = afterWithdraw1.replaceAll("[^0-9]", "")
int thuHo1 = Integer.parseInt(numericString1)
//int amountTransaction1 = amountTransaction
if (thuHo1 == thuHo - amountTransaction) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền chưa được trừ khỏi tài khoản.")
} else {
	println("Số tiền trừ sai")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}
