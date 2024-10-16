import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
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
//actions.moveToElement(element1).click().perform()
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20)
WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathManagerListAccount)))
actions.moveToElement(element2).click().perform()
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.xpathUserCode)))

wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathBtnAccount))).click()
WebElement searchTextbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GlobalVariable.xpathSearchTextbox)))
searchTextbox.sendKeys('dannt11')
WebElement option1 = driver.findElement(By.xpath(GlobalVariable.xpathOption))
actions.moveToElement(option1).click().perform()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()
//Assert.assertEquals(driver.findElement(By.xpath(GlobalVariable.xpathAccountName)).getText(), GlobalVariable.username)
driver.findElement(By.xpath(GlobalVariable.xpathUser)).click()
driver.findElement(By.xpath(GlobalVariable.xpathSearchTextbox)).sendKeys('dannt21')
driver.findElement(By.xpath(GlobalVariable.xpathClicked)).click()
driver.findElement(By.xpath(GlobalVariable.xpathBtnSearch)).click()