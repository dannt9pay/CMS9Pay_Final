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

int waitTime = Integer.parseInt(GlobalVariable.waitSecond)

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), waitTime, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

// Lấy đối tượng WebDriver
def driver = DriverFactory.getWebDriver()

// Định danh của dropdown
def dropdown = driver.findElement(By.name('service_type'))

// Chọn option "Thu hộ IB"
dropdown.findElement(By.xpath('//option[@value=\'DEPOSIT_TRANSFER_IB\']')).click()

def dropdownStt = driver.findElement(By.name('is_active'))

dropdownStt.findElement(By.xpath('//option[@value="1"]')).click()

WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/searchBtn'))

String xpathCollectionIB = "//th/span[@class='font-bold col-teal']/i[@class='material-icons' and text()='check_circle']"
String xpathSttActive = "//td//span[contains(text(),'Đang hoạt động')]"

// Tạo một danh sách để lưu trữ các phần tử
List<WebElement> CollectionIBs = new ArrayList<WebElement>()
List<WebElement> SttActives = new ArrayList<WebElement>()

// Tìm tất cả các phần tử phù hợp với XPath và thêm vào danh sách
CollectionIBs = driver.findElements(By.xpath(xpathCollectionIB))
SttActives = driver.findElements(By.xpath(xpathSttActive))

// In số lượng phần tử tìm thấy
//println("Số lượng phần tử: " + elements.size())

def expectedElements = 25;

if (SttActives.size() == expectedElements) {
	println("Số lượng icon tìm thấy là đúng: "+ CollectionIBs.size())
	KeywordUtil.markPassed("Test case đã pass với số lượng icon đúng.")
} else {
	println("Số lượng icon tìm thấy không đúng. Số lượng thực tế: "+ CollectionIBs.size() +", Số lượng mong đợi: " + expectedElements)
	KeywordUtil.markFailed("Test case đã fail với số lượng icon không đúng.")
}


