package seleniumCrawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabelExtractor extends Init {

	public static String extractLabels() throws InterruptedException {
		Thread.sleep(3000);
		String code= driver.getPageSource();
		int bodyIndex = 0;
		int end = 0;
		bodyIndex = code.indexOf("<body");
		end = code.length();
//		end = code.indexOf("</body");
		System.out.println("value of bodyIndex : "+ bodyIndex);
		System.out.println("value of end : "+ end);
		code = code.substring(bodyIndex, end);
		end  = code.length();
		int i =1;
		int j =0;
		String label = "";
		///////////////////////////////////////////////////////////////////////////////////////////////////
		for(;i!=0;) {
			//find i
			String patternStr = ">[a-zA-Z]+";
//			System.out.println(patternStr);
		    Pattern pattern = Pattern.compile(patternStr);
		    Matcher matcher = pattern.matcher(code);
		    if(matcher.find()){
		    
		    i = matcher.start();//this will give index of error
		    
		    }
		    else
		    	break;
		    code = code.substring(i, end);
		    end = code.length();
		    //find j
		    String patternStr1 = "<";
//		    String patternStr1 = "</div";
//			System.out.println(i);
		    Pattern pattern1 = Pattern.compile(patternStr1);
		    Matcher matcher1 = pattern1.matcher(code);
		    if(matcher1.find()){

		    j = matcher1.start();//this will give index of error
		    }
//		    System.out.println(j);
		    label += ",  "+code.substring(0, j);
//		    System.out.println(label);
		    code = code.substring(j, end);
		    end = code.length();
		    
		}
//		System.out.println(label);
		return label;
		
	}
}
