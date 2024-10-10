import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

def driver = DriverFactory.getWebDriver()
JavascriptExecutor js = (JavascriptExecutor) driver;
WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 15)

WebElement startElement = driver.findElement(By.xpath("(//strong[contains(text(),'Chi hộ:')])[1]"));
//JavascriptExecutor js1 = (JavascriptExecutor) driver;
String script = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent = (String) js.executeScript(script, startElement);
System.out.println("chiHoWallet " + nextTextNodeContent);
def numericString = nextTextNodeContent.replaceAll("[^0-9]", "")
int chiHoBf = Integer.parseInt(numericString)
System.out.println("Chi ho " + chiHoBf);


WebElement thuHoElm = driver.findElement(By.xpath("(//strong[contains(text(),'Thu hộ:')])[1]"));
//JavascriptExecutor jsBF1 = (JavascriptExecutor) driver;
String scriptBF = "return arguments[0].nextSibling.textContent;";
String thuHoWallet = (String) js.executeScript(scriptBF, thuHoElm);
def thuHoInt = thuHoWallet.replaceAll("[^0-9]", "")
int thuHoBf = Integer.parseInt(thuHoInt)
System.out.println("Thu ho " + thuHoBf);


WebElement startElement1 = driver.findElement(By.xpath("(//strong[contains(text(),'Chi hộ:')])[2]"));
//JavascriptExecutor js1 = (JavascriptExecutor) driver;
String script1 = "return arguments[0].nextSibling.textContent;";
String nextTextNodeContent1 = (String) js.executeScript(script1, startElement1);
System.out.println("chiHoWallet " + nextTextNodeContent1);
def numericString1 = nextTextNodeContent1.replaceAll("[^0-9]", "")
int chiHoBf1 = Integer.parseInt(numericString1)
System.out.println("Chi ho " + chiHoBf1);


WebElement thuHoElm1 = driver.findElement(By.xpath("(//strong[contains(text(),'Thu hộ:')])[2]"));
//JavascriptExecutor jsBF1 = (JavascriptExecutor) driver;
String scriptBF1 = "return arguments[0].nextSibling.textContent;";
String thuHoWallet1 = (String) js.executeScript(scriptBF1, thuHoElm1);
def thuHoInt1 = thuHoWallet1.replaceAll("[^0-9]", "")
int thuHoBf1 = Integer.parseInt(thuHoInt1)
System.out.println("Thu ho " + thuHoBf1);