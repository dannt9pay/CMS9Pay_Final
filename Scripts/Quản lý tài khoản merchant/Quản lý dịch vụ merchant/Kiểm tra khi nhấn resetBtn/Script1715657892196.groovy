import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 20, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

// Lấy đối tượng WebDriver
def driver = DriverFactory.getWebDriver()

// Định danh của dropdown
def dropdown = driver.findElement(By.name('service_type'))
def usenameInputField = driver.findElement(By.xpath("//input[@placeholder='Nhập tên tài khoản']"))
// Chọn option "Thu hộ IB"
dropdown.findElement(By.xpath('//option[@value=\'DEPOSIT_TRANSFER_IB\']')).click()

def dropdownStt = driver.findElement(By.name('is_active'))

dropdownStt.findElement(By.xpath('//option[@value="1"]')).click()

WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/accountName'), 'dannt11');

String valueUsername  = usenameInputField.getAttribute("value");
println("Giá trị đã nhập trước là: " + valueUsername)

def resetBtn = driver.findElement(By.xpath("//button[contains(text(),'Reset bộ lọc')]"));

resetBtn.click();

def wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)

def inputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Nhập tên tài khoản']")))
// after resetBtn check
//def usenameInputField = driver.findElement(By.xpath("//input[@placeholder='Nhập tên tài khoản']"))
def usenameInputFieldg = driver.findElement(By.xpath("//input[@placeholder='Nhập tên tài khoản']"))

String valueUsernameAf  = usenameInputFieldg.getAttribute("value");
println("Giá trị đã nhập là: " + valueUsernameAf)


//KeywordUtil.markFailed("Values are equal") // Đánh dấu kiểm tra thất bại mặc định
//KeywordUtil.markPassed("Values are not equal")

if (valueUsernameAf != valueUsername) {
    KeywordUtil.markPassed("Values are not equal") // Nếu giá trị giống nhau, đánh dấu kiểm tra thất bại
} else {
    KeywordUtil.markFailed("Values are equal") // Nếu giá trị không giống nhau, đánh dấu kiểm tra thành công
}

