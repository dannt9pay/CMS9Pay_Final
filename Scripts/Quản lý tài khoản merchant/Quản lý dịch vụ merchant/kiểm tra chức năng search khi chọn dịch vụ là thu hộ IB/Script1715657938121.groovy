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

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), 20, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

// Lấy đối tượng WebDriver
def driver = DriverFactory.getWebDriver()

// Định danh của dropdown
def dropdown = driver.findElement(By.name('service_type'))

// Chọn option "Thu hộ IB"
dropdown.findElement(By.xpath('//option[@value=\'DEPOSIT_TRANSFER_IB\']')).click()

WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/searchBtn'))

String xpath = "//th/span[@class='font-bold col-teal']/i[@class='material-icons' and text()='check_circle']"

// Tạo một danh sách để lưu trữ các phần tử
List<WebElement> elements = new ArrayList<WebElement>()

// Tìm tất cả các phần tử phù hợp với XPath và thêm vào danh sách
elements = driver.findElements(By.xpath(xpath))

// In số lượng phần tử tìm thấy
println("Số lượng phần tử: " + elements.size())

def expectedElements = 25;

if (elements.size() == expectedElements) {
	println("Số lượng icon tìm thấy là đúng: "+ elements.size())
	KeywordUtil.markPassed("Test case đã pass với số lượng icon đúng.")
} else {
	println("Số lượng icon tìm thấy không đúng. Số lượng thực tế: "+ elements.size() +", Số lượng mong đợi: " + expectedElements)
	KeywordUtil.markFailed("Test case đã fail với số lượng icon không đúng.")
}


