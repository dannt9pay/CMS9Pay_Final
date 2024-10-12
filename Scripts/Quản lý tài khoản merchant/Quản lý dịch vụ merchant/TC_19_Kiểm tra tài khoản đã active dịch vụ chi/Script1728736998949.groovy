import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

int waitTime = Integer.parseInt(GlobalVariable.waitSecond)

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), waitTime, FailureHandling.STOP_ON_FAILURE)

def driver = DriverFactory.getWebDriver()

WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), waitTime)
WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

List<WebElement> elements = driver.findElements(By.xpath("//tbody//td[@class='text-center'][2]"));
Random random = new Random();
WebElement randomElement = elements.get(random.nextInt(elements.size()));
def element1 = randomElement.getText();
println("tên merchant: " + element1)

driver.findElement(By.xpath("//input[@placeholder='Nhập tên tài khoản']")).sendKeys(element1)
driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]")).click()
driver.findElement(By.xpath("//a[contains(text(),'Quản lý phí dịch vụ merchant')]")).click()
driver.findElement(By.xpath("//span[@class='filter-option pull-left']")).click()
driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(element1)
driver.findElement(By.xpath("//ul[@role='menu']//li[@class='active']//a")).click()
driver.findElement(By.xpath("//button[contains(text(),'Tìm kiếm')]")).click()


def chiho = driver.findElement(By.xpath("//td[2][@class='text-center' and contains(text(), 'Chi hộ')]"))
println("Giá trị của chi hộ là: " + chiho.getText())
Assert.assertEquals(chiho.getText(), "Chi hộ")

