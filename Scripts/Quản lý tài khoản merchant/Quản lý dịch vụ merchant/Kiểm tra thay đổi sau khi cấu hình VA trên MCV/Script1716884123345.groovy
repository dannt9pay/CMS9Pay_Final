import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

def driver = DriverFactory.getWebDriver()
driver.findElement(By.xpath("//input[@placeholder='Tài khoản']")).sendKeys("dannt11")
driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("9Pay@1234")
driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click()

WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Quản lý dịch vụ Thu')]"))).click()
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo yêu cầu thu tiền')]"))).click()
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Chọn ngân hàng')]"))).click()

List<WebElement> elements = DriverFactory.getWebDriver().findElements(By.xpath("//li[contains(text(),'NH')]"))

if (elements.size() > 0) {
	Random rand = new Random()
	WebElement randomElement = elements.get(rand.nextInt(elements.size()))
	randomElement.click()
} else {
	println('Không tìm thấy phần tử nào chứa văn bản "NH"')
}

driver.findElement(By.xpath("//input[@placeholder='Giá trị GD']")).sendKeys("100000")
driver.findElement(By.xpath("//button[contains(text(),'Tiếp tục')]")).click()

wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'VPBANK')]")))
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'TECHCOMBANK')]")))
