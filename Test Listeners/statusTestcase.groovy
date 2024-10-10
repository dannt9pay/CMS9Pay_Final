import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

class statusTestcase {
	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		
		String testCaseId = testCaseContext.getTestCaseId()
		//WebUI.navigateToUrl(GlobalVariable.baseUrl)
		String url
		if (testCaseId == "Test Cases/Quản lý tài khoản merchant/Quản lý dịch vụ merchant/Kiểm tra thay đổi sau khi cấu hình VA trên MCV") {
			url = "https://stg-console.9pay.mobi/login"
		} else {
			url = "https://stg-cms-console.9pay.mobi/login"
		}
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(url)
	}
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		
		String testCaseId = testCaseContext.getTestCaseId()
        if (testCaseId == "Test Cases/Quản lý tài khoản merchant/Quản lý dịch vụ merchant/Kiểm tra chức năng xác nhận cấu hình thay đổi VA") {
            Thread.sleep(30000)
        }
		//WebUI.closeBrowser()
	}
}