package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.elementutil.ElementUtil;
import com.qa.opencart.constants.AppConstants;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2.to call private driver
	public LoginPage(WebDriver driver) {
		this.driver =driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.page action/method what do you want from page
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("login page title:"+title);
		return title;
	}
	
	public String getLoginPageUrl() {

		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println(url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementPresence(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		
	}
	
	public Accountpage dologin(String un,String pwd) {
		eleUtil.waitForElementVisible(email,AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new Accountpage(driver); //this is called a page chaging mode
	}
	
	//when you want to use stattic you need to change every method driver, it will creaate a problem in parrlle excution

	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
