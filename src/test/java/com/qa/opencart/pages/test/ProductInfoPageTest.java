package com.qa.opencart.pages.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfopageSetup() {
		accpage = loginPage.dologin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	
	@DataProvider
	public Object [][] getProductImageTestData(){
		return new Object [][] {
			{"Macbook","MacBook Pro",4},
				{"Macbook","MacBook Air",4},
				{"iMac","iMac",3},
				{"Apple","Apple Cinema 30\"",6}
			};
	}
	
	@Test(dataProvider = "getProductImageTestData")
	public void productImagesCountTest(String searchKey,String productName,int imageCount) {
	searchPage = accpage.perfromSearch(searchKey);
	productInfoPage = searchPage.selectProduct(productName);
	int actImagecount = productInfoPage.getProductImagesCount();
	Assert.assertEquals(actImagecount,imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accpage.perfromSearch("MacBook");
		productInfoPage= searchPage.selectProduct("MacBook Pro");
		Map<String,String>actProductInfoMap = productInfoPage.getProductInfo();
		System.out.println(actProductInfoMap);
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"),"MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productPrice"), "$2,000.00");
        
		softAssert.assertAll();
		
		//assert vs  verify(softassert)
		
	}
	
	@Test
	public void addToCartTest() {
		searchPage = accpage.perfromSearch("MacBook");
		productInfoPage= searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actCartMesg = productInfoPage.addProductToCart();
		//
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf("MacBook Pro")>=0);
		
		softAssert.assertEquals(actCartMesg,"Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
		
		
		
		
	}

}
