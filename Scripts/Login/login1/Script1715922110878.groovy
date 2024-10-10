import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.testng.Assert

import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


TestData loginData = TestDataFactory.findTestData('Data Files/login/login1')

// Lặp qua tất cả các hàng trong dữ liệu kiểm thử
for (def index : (0..loginData.getRowNumbers() - 1)) {
	String username = loginData.getValue('username', index + 1)
	String password = loginData.getValue('password', index + 1)
	String expectedResult = loginData.getValue('expectedResult', index + 1)
	
	def driver = DriverFactory.getWebDriver()

	// Thực hiện chức năng đăng nhập
	WebUI.setText(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/userNameTextbox'), username)
	WebUI.setText(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/passwordTextbox'), password)
	WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/loginButton'))

	// Kiểm tra kết quả
	if (expectedResult == 'Fail') {
	
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Không tìm thấy thông tin tài khoản')]")).getText(), "Không tìm thấy thông tin tài khoản")
		driver.findElement(By.xpath("//button[normalize-space()='Ok']")).click()
	} else {
		WebElement loginSuccess = driver.findElement(By.xpath("//a[normalize-space()='9PAY SERVICE ADMINISTRATOR']"))
		loginSuccess.isDisplayed()
	}
		
//	WebUI.closeBrowser()
}
