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
String amount = 100000;
WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15)
//Lấy số dư chi hộ trước khi chuyển
//WebElement startElement = driver.findElement(By.xpath("(//strong[contains(text(),'Chi hộ:')])[1]"));
//String script = "return arguments[0].nextSibling.textContent;";
//String nextTextNodeContent = (String) js.executeScript(script, startElement);
//System.out.println("chiHoWallet " + nextTextNodeContent);
//def numericString = nextTextNodeContent.replaceAll("[^0-9]", "")
//int chiHoBf = Integer.parseInt(numericString)
//System.out.println("Chi ho ví chuyển trước " + chiHoBf);

//Lấy số dư ví thu hộ trước khi chuỷen
WebElement thuHoElm = driver.findElement(By.xpath("(//strong[contains(text(),'Thu hộ:')])[1]"));
String scriptBF = "return arguments[0].nextSibling.textContent;";
String thuHoWallet = (String) js.executeScript(scriptBF, thuHoElm);
def thuHoInt = thuHoWallet.replaceAll("[^0-9]", "")
int thuHoBf = Integer.parseInt(thuHoInt)
System.out.println("Thu ho ví chuyển trước " + thuHoBf);

//Lấy số dư ví chi hộ của tài khoản nhận trước khi chuyển
WebElement startElement1 = driver.findElement(By.xpath("(//strong[contains(text(),'Chi hộ:')])[2]"));
String script1 = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent1 = (String) js.executeScript(script1, startElement1);
System.out.println("chiHoWallet " + nextTextNodeContent1);
def numericString1 = nextTextNodeContent1.replaceAll("[^0-9]", "")
int chiHoBf1 = Integer.parseInt(numericString1)
System.out.println("Chi ho ví nhận trước gd " + chiHoBf1);

driver.findElement(By.xpath(GlobalVariable.xpathTranfer)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='filter-option pull-left'][normalize-space()='dannt11']")))
driver.findElement(By.xpath("//span[contains(text(),'Chọn tk nhận')]")).click()
driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select form-control open']//input[@type='text']")).sendKeys(GlobalVariable.username1)
driver.findElement(By.xpath("//li[@class='active']//a")).click()
driver.findElement(By.xpath("//input[@placeholder='Nhập số tiền']")).sendKeys(amount)
driver.findElement(By.xpath("//span[contains(text(),'Chọn ví nhận')]")).click()
driver.findElement(By.xpath("(//span[@class='text'][contains(text(),'Chi hộ (VND)')])[2]")).click()
driver.findElement(By.xpath("//span[contains(text(),'Chọn ví chuyển')]")).click()
driver.findElement(By.xpath("(//span[@class='text'][contains(text(),'Thu hộ (VND)')])[1]")).click()
driver.findElement(By.xpath("//button[contains(text(),'Xác nhận')]")).click()
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.BtnOkXpath)))
Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Bạn đang chuyển tiền khác sub-merchant?')]")).getText(), "Bạn đang chuyển tiền khác sub-merchant?")
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THÔNG TIN GIAO DỊCH')]")))

driver.navigate().refresh()
WebElement rutTien = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Rút tiền')]")));
Assert.assertEquals(rutTien.getText(), "Rút tiền")
WebElement approveTss = driver.findElement(By.xpath("//button[contains(text(),'DUYỆT GIAO DỊCH RÚT')]"))
js.executeScript("arguments[0].scrollIntoView(false);", approveTss);
approveTss.click()
Thread.sleep(1000)
driver.findElement(By.xpath(GlobalVariable.BtnOkXpath)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOk)).click()

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions.groovy'), [:], FailureHandling.STOP_ON_FAILURE)

WebElement thuHoElm12 = driver.findElement(By.xpath("(//strong[contains(text(),'Thu hộ:')])[1]"));
String scriptAf = "return arguments[0].nextSibling.textContent;";
String thuHoWalletAf = (String) js.executeScript(scriptAf, thuHoElm12);
def thuHoIntAf = thuHoWalletAf.replaceAll("[^0-9]", "")
int thuHoAf = Integer.parseInt(thuHoIntAf)
System.out.println("Thu ho ví chuyển sau gd " + thuHoAf);

//Lấy số dư ví chi hộ của tài khoản nhận sau khi chuyển
WebElement startElement2 = driver.findElement(By.xpath("(//strong[contains(text(),'Chi hộ:')])[2]"));
String script2 = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContentAf1 = (String) js.executeScript(script2, startElement2);
System.out.println("chiHoWallet " + nextTextNodeContentAf1);
def numberBalance = nextTextNodeContentAf1.replaceAll("[^0-9]", "")
int chiHoBf2 = Integer.parseInt(numberBalance)
System.out.println("Chi ho ví nhận sau gd " + chiHoBf2);

int amountInt = Integer.parseInt(amount);
int sotienThu = thuHoBf - amountInt;
int sotienChuyen = chiHoBf1 + amountInt;
System.out.println("Chi ho ví nhận sau gd " + sotienThu);
System.out.println("Chi ho ví nhận sau gd " + sotienChuyen);
if (thuHoBf - amountInt == thuHoAf && chiHoBf1 + amountInt == chiHoBf2) {
	println("Số tiền đã đúng ")
	KeywordUtil.markPassed("Test case đã pass với số tiền đã được cộng đúng.")
} else {
	println("Số tiền chưa được cộng đúng")
	KeywordUtil.markFailed("Test case đã fail với số tiền chưa được cộng đúng.")
}
