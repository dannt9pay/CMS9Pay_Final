import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

//WebUI.openBrowser('')
//
//WebUI.maximizeWindow();
//
//WebUI.navigateToUrl('https://stg-cms-console.9pay.mobi/login')
//
//WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/userNameTextbox'), 'dannt@9pay.vn')
//
//WebUI.sendKeys(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/passwordTextbox'), 'dannt@9pay.vn')
//
//WebUI.click(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/loginButton'));
int waitTime = Integer.parseInt(GlobalVariable.waitSecond)

WebUI.callTestCase(findTestCase('Test Cases/Login/LoginCommon'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Trang chủ/userName'), waitTime, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Quản lý tài khoản merchant/Quản lý danh sách tài khoản/CustomFunctions'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Quản lý tài khoản merchant/Quản lý dịch vụ merchant/headerMenu'), 3)

