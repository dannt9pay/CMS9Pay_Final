import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 20, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/iconTime'))
def driver = DriverFactory.getWebDriver()

//def menuHeader =  driver.findElement(By.xpath("//div[@class='modal-header']/h4[contains(text(),'Xác nhận thay đổi cấu hình ngân hàng thu hộ VA')]"));
//String actualText =  menuHeader.getText();
//println("Text mong muốn là: " + actualText);
//
//String expectedText = "Xác nhận thay đổi cấu hình ngân hàng thu hộ VA";
//if (actualText.equals(expectedText)) {
//	KeywordUtil.markPassed("This step passed successfully.")
//} else {
//	KeywordUtil.markFailed("This step fail.")
//}
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10)
def iconClick = driver.findElement(By.xpath(GlobalVariable.xpathDropdownSwitchFrom))
iconClick.click();

WebElement techcombankOtp = driver.findElement(By.xpath(GlobalVariable.xpathOptionTCB))
String textTech = techcombankOtp.getText()
println("Đoạn văn: " + textTech)
techcombankOtp.click()
def matcher = (textTech =~ /\bTECHCOMBANK\b/)
String techcombank = matcher.find() ? matcher.group() : ""

def iconClick2 = driver.findElement(By.xpath(GlobalVariable.xpathDropdownSwitchTo))
iconClick2.click();

def element2 = driver.findElement(By.xpath(GlobalVariable.xpathOptionVPTo))
element2.click()

driver.findElement(By.xpath(GlobalVariable.xpathDatePicker)).click()
driver.findElement(By.xpath(GlobalVariable.xpathOkBtn)).click()
driver.findElement(By.xpath(GlobalVariable.xpathOkBtn)).click()
// Cộng thêm 1p và click vào time 
Date now = new Date()
SimpleDateFormat sdf1 = new SimpleDateFormat("mm")
String formattedDate1 = sdf1.format(now)
int currentMinute1 = Integer.parseInt(formattedDate1) + 1
String dateXpath1 = "m-"+ currentMinute1
String clock3 = "//div[@class='dtp']/descendant::*[name()='circle' and @id='" + dateXpath1 + "']"
println("element: " + clock3)
WebElement clock4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(clock3)))
String script1 = "var event = new MouseEvent('click', {" +
                        "view: window," +
                        "bubbles: true," +
                        "cancelable: true" +
                        "});" +
                        "arguments[0].dispatchEvent(event);";
((JavascriptExecutor) driver).executeScript(script1, clock4);
driver.findElement(By.xpath(GlobalVariable.xpathOkBtn)).click()

driver.findElement(By.xpath(GlobalVariable.xpathDatePickerEnd)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOkEnd)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnOkEnd)).click()
// Click vào thời gian khi cộng thêm 2p

SimpleDateFormat sdf = new SimpleDateFormat("mm")
String formattedDate = sdf.format(now)
int currentMinute = Integer.parseInt(formattedDate) + 2
String dateXpath = "m-"+ currentMinute
String clock2 = "//div[@class='dtp']/descendant::*[name()='circle' and @id='" + dateXpath + "']"
//println("element: " + clock2)
WebElement clock1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(clock2)))
//println(clock1)
String script = "var event = new MouseEvent('click', {" +
                        "view: window," +
                        "bubbles: true," +
                        "cancelable: true" +
                        "});" +
                        "arguments[0].dispatchEvent(event);";
((JavascriptExecutor) driver).executeScript(script, clock1);
driver.findElement(By.xpath(GlobalVariable.xpathBtnOkEnd)).click()

driver.findElement(By.xpath(GlobalVariable.xpathOptionBack)).click()
driver.findElement(By.xpath(GlobalVariable.xpathOptionTCBBack)).click()
driver.findElement(By.xpath(GlobalVariable.xpathUpdateBtn)).click()
driver.findElement(By.xpath("//button[normalize-space()='Ok']")).click()