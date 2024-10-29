package com.qa.opencart.pages.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountpageTest extends BaseTest{
	
	
	@BeforeTest
	public void accPageSetup() {
		accpage = loginPage.dologin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void  accPageTitleTest() {
		String title=accpage.getAccPageTitle();
		Assert.assertEquals(title,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accPageURLTest() {
		String url = accpage.getAccPageURL();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void  isLogoutLinkExistTest() {
		Assert.assertTrue(accpage.isLogoutLinkExist());
	}
	
	
	@Test
	public void accPageHeadersCountTest() {
		List<String> actualAccPageHeadersList = accpage.getAccountsPageHeadersList();
		System.out.println("Acc page Headers List"+actualAccPageHeadersList);
		Assert.assertEquals(accpage.getAccountsPageHeadersList().size(),AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccPageHeadersList = accpage.getAccountsPageHeadersList();
		System.out.println("Acc page Headers List"+actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList,AppConstants.EXPECTED_ACCOUNT_PAGE_HEADER_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object [][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searProducthCountTest(String searchKey) {
		searchPage = accpage.perfromSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object [][] {
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String serachKey,String productName) {
		searchPage = accpage.perfromSearch(serachKey);
		if(searchPage.getSearchCount()>0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}
	}

}
