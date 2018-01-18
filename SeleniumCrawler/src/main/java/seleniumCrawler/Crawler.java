package seleniumCrawler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Crawler extends Init{
	
	public static void getAllLinks() throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		Thread.sleep(3000);
		
		List<WebElement> elementList = driver.findElements(By.xpath("//a"));
		Iterator<WebElement> aTagIterator = elementList.iterator();
		WebElement aTag = null;
		while(aTagIterator.hasNext()) {
			aTag = aTagIterator.next();
			String href = aTag.getAttribute("href");
			
//			System.out.println("new link......"+href);
//			System.out.println("--------------------------------------------------");
//			display();
			if(href!=null && !pagesVisited.contains(href) && !pagesToVisit.contains(href)) {
//				System.out.println("--------------------------------------------------");
//				System.out.println(checkVisited(href)+"======="+checkToVisit(href));
				pagesToVisit.add(href);
//				System.out.println(href+"+++++++++added");
//				Thread.sleep(2000);
			}
//			else
//				System.out.println(href+"--------ignored");
		}

	}
    public static void crawl(String url) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
    	//pagesVisited.add("http://192.168.150.45/#/intent-mgmt");
    	
    	driver.navigate().to(url);
    	getAllLinks();
		LabelExtractor.extractLabels();
		pagesVisited.add(url);
		System.out.println("pages to visit:  "+pagesToVisit.size());
    	String currentUrl = "";
    	PrintWriter writer = new PrintWriter("labels.txt", "UTF-8");
    	while(!pagesToVisit.isEmpty()) {
    		
    		currentUrl = pagesToVisit.remove(0);
    		driver.navigate().to(currentUrl);
    		pagesVisited.add(currentUrl);
    		Thread.sleep(3000);
    		getAllLinks();
    		String label = LabelExtractor.extractLabels();
    		writer.println(currentUrl+"......................................");
    		writer.println(label);
    		
    		System.out.println("pages to visit:  "+pagesToVisit.size());
//    		display();
    		
    	}
    	writer.close();
    }
    private static boolean checkVisited(String url) {
    	for (String temp : pagesVisited) {
    		System.out.println("visited,,,,,,,,url:  "+url+"temp:  "+temp);
    		if(temp==url) {
    			System.out.println("false");
        		return false;
        		
        	}
		}
    	
    	return true;
    	
    }
    private static boolean checkToVisit(String url) {
    	for (String temp : pagesToVisit) {
    		System.out.println("to visit,,,,,,,,url:  "+url+"temp:  "+temp);
    		if(temp==url) {
    			System.out.println("false");
        		return false;
        	}
		}
    	
    	return true;
    	
    }
    private static void display() {
    	System.out.println("----------------------- To Visit---------------------------");
    	for (String temp : pagesToVisit) {
    		System.out.println(temp);
    	}
    	System.out.println("-----------------------Visited---------------------------");
    	for (String temp : pagesVisited) {
    		System.out.println(temp);
    	}
    	System.out.println("---------------------------------------------------------");
    }
}