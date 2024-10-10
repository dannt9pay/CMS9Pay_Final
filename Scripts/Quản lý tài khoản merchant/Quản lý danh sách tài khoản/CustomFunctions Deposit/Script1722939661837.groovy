import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable
def driver = DriverFactory.getWebDriver()

WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'Quản lý tài khoản merchant')]"));
//((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
Actions actions = new Actions(driver)
actions.moveToElement(element1).click().perform()
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15)
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount))).click()


