import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable
def driver = DriverFactory.getWebDriver()

sleep(2000)
WebElement element1 = driver.findElement(By.xpath(GlobalVariable.xpathManagerMC));
Actions actions = new Actions(driver)
actions.moveToElement(element1).click().perform()
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 30)
WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerServiceMC)))
actions.moveToElement(element2).click().perform()


