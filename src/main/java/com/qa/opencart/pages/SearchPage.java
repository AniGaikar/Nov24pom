package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.elementutil.ElementUtil;
import com.qa.opencart.constants.AppConstants;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResult = By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		 eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchCount() {
		int productCount = eleUtil.waitForElementsVisible(searchResult, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product Count::"+productCount);
        return productCount; 	
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
