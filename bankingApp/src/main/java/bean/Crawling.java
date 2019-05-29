package bean;import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class Crawling {
	public static int getnumber = 6;
	

	
    private static String httpRequest(String requestUrl) {  
       
       StringBuffer buffer = null;  
       BufferedReader bufferedReader = null;
       InputStreamReader inputStreamReader = null;
       InputStream inputStream = null;
       HttpURLConnection httpUrlConn = null;
 
       try {  

           URL url = new URL(requestUrl);  
           httpUrlConn = (HttpURLConnection) url.openConnection();  
           httpUrlConn.setDoInput(true);  
           httpUrlConn.setRequestMethod("GET");  
 

           inputStream = httpUrlConn.getInputStream();  
           inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
           bufferedReader = new BufferedReader(inputStreamReader);  
 

           buffer = new StringBuffer();  
           String str = null;  
           while ((str = bufferedReader.readLine()) != null) {  
               buffer.append(str);  
           }  
 
       } catch (Exception e) {  
           e.printStackTrace();  
       }  finally {

           if(bufferedReader != null) {
               try {
                   bufferedReader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if(inputStreamReader != null){
               try {
                   inputStreamReader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if(inputStream != null){
               try {
                   inputStream.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if(httpUrlConn != null){
               httpUrlConn.disconnect();  
           }
       }
       return buffer.toString();  
   }  
 

   private static Securities[] SecuritiesFiter(String html) {  
       Securities[] securitiesArray = new Securities[getnumber];
       String str0 = "";
       String str1 = "";
       String str2 = "";
       String str3 = "";
       String str4 = "";
       double price = 0;
       double change = 0;
       int count = 0;
       

       Pattern p = Pattern.compile("(.*)(<a href=\"/[A-Z]{2}[0-9]{6}/\" title=\")(.*?)(<span>)(.*?)(</span>)(.*?)(<span class=\")(.*?)(\">)(.*?)(</span>)(.*?)(<span class=\")(.*?)(\">)(.*?)(</span>)(.*)");  
       Matcher m = p.matcher(html);
       if (m.matches()) {

    	   str0 = m.group(1);

           str1 = m.group(5);

           str2 = m.group(11);
           price = Double.parseDouble(str2);

           str3 = m.group(17);
           change = Double.parseDouble(str3);
           securitiesArray[count] = new Securities(str1, price, change);
       }
       count++;
       while(count < getnumber) {
    	   m = p.matcher(str0);
           if (m.matches()) { 

        	   str0 = m.group(1);

               str1 = m.group(5);

               str2 = m.group(11);
               price = Double.parseDouble(str2);

               str3 = m.group(17);
               change = Double.parseDouble(str3);
               securitiesArray[count] = new Securities(str1, price, change);
           }
           count++;
       }

       return securitiesArray;
   }   
	

   public static Securities[] getSecuritiesInfo() {  

       String html = httpRequest("https://hq.gucheng.com/SH000001/");      
             

       Securities[] result = SecuritiesFiter(html);
       return result;  
   }  
 


    
}
