package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GitHubTest {
	


	public static void main(String[] args) throws AWTException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\umapa\\Desktop\\Frameworks\\FrameworkClass\\driver 85\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 11");
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		//List<WebElement> allProductName = driver.findElements(By.partialLinkText("Apple iPhone 11 "));
		
		List<WebElement> allProductName =driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		int numOfProducts = allProductName.size();
		List<WebElement> allProductPrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
		System.out.println(numOfProducts);
		System.out.println(allProductPrice.size());
		String productName, s, s1;
		int productPrice;
		Map<String, Integer> products = new HashMap<String, Integer>();
		Map<String, Integer> mp = new HashMap<String, Integer>();
		// Adding all the product and price into Map
		for (int i = 0; i < numOfProducts; i++) {
			productName = allProductName.get(i).getText();
			if (productName.contains("Apple iPhone 11")) {
				s = allProductPrice.get(i).getText();
				s1 = s.replaceAll(",", "");
				productPrice = Integer.parseInt(s1);
				products.put(productName, productPrice);
				// Counting Duplicate keys(Product Name)
				if (mp.containsKey(productName)) {
					Integer count = mp.get(productName);
					mp.put(productName, count + 1);
				} else {
					mp.put(productName, 1);
				}
			}
		}
		
		System.out.println("\nDuplicate Product:\n========================== ");
		for (Entry<String, Integer> entry : mp.entrySet()) {
			if (entry.getValue() > 1) {
				System.out.println("Duplicate produt Name = " + entry.getKey() + ", Count = " + entry.getValue());
			}
		}
		
		System.out.println("\nBefore Sort\n============");
		for (Entry<String, Integer> entry : products.entrySet()) {
			System.out.println("Product Name = " + entry.getKey() + ", Price = " + entry.getValue());
		}
		System.out.println("\nAfter Sort\n============");
		Set<Entry<String, Integer>> entrySet = products.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<>(entrySet);
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		for (Entry<String, Integer> entry : list) {
			System.out.println("product Name = " + entry.getKey() + ", Price = " + entry.getValue());
		}
		
		// driver.quit();
	}


}
