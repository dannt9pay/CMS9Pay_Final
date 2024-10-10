import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.DecimalFormat

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
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
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10)
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
driver.findElement(By.xpath(GlobalVariable.xpathOptionThuho)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDay)).click()
driver.findElement(By.xpath(GlobalVariable.xpathToday)).click()
driver.findElement(By.xpath(GlobalVariable.xpathDayClicked)).click()

//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.BtnOkXpath))).click()

WebElement overbalance =  driver.findElement(By.xpath("//label[contains(text(),'Số dư Ví')]/following::input[1]"))
String inputValue = overbalance.getAttribute("value");
def numericString = inputValue.replaceAll("[^0-9]", "")
int walletBalance = Integer.parseInt(numericString)

WebElement availbeBalance = driver.findElement(By.xpath("//label[contains(text(),'Số dư khả dụng')]/following::input[1]"))
String outputValue = availbeBalance.getAttribute("value");
def outputValueClear = outputValue.replaceAll("[^0-9]", "")
int availbeBalanceInt = Integer.parseInt(outputValueClear)

driver.findElement(By.xpath(GlobalVariable.xpathFieldAmount)).sendKeys(GlobalVariable.amountValid)

driver.findElement(By.xpath(GlobalVariable.xpathConfirm)).click()
int test1 = availbeBalanceInt - lockBalanceBf
DecimalFormat formatter = new DecimalFormat("#,###");
String formattedResult = formatter.format(test1);
System.out.println(formattedResult);
String xpathErrorGreatThanAvailbleBalance = "//p[contains(text(),'Số tiền rút không được lớn hơn " + formattedResult + "VNĐ')]"
Assert.assertEquals(driver.findElement(By.xpath(xpathErrorGreatThanAvailbleBalance)).getText(), "Số tiền rút không được lớn hơn " + formattedResult + "VNĐ")