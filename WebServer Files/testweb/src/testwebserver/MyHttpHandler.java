/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testwebserver;

//import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 *
 * @author leppa
 */


public class MyHttpHandler  implements HttpHandler {    

    Vector<String> keys = new Vector();
    Vector<String> values = new Vector();

  public void handle(HttpExchange httpExchange) throws IOException {

    String requestParamValue=null; 
    keys.clear();
    values.clear();
   
   if("POST".equals(httpExchange.getRequestMethod())) { 

       System.out.println("Got a POST");
       requestParamValue = handlePostRequest(httpExchange);        

   }  


    handleResponse(httpExchange,requestParamValue); 

  }


   private String handlePostRequest(HttpExchange httpExchange) {

           InputStream is = httpExchange.getRequestBody();           
           BufferedReader br = new BufferedReader(new InputStreamReader(is));           
           String s;
            try {
                while ((s=br.readLine()) != null)
                {
                    StringTokenizer st = new StringTokenizer(s, "&");
                    while (st.hasMoreTokens())
                    {
                           String parm = st.nextToken();
                           String key = parm.split("=")[0];
                           String value = parm.split("=")[1];
                           keys.add(key);
                           values.add(value);
                           System.out.println("Got a pair : key=" +key + " value=" + value);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           //String name = parms.getFirst("fname");
           //..System.out.println("Got name : " + name);
           return new Integer(keys.size()).toString();

   }


   private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {

            OutputStream outputStream = httpExchange.getResponseBody();

            StringBuilder htmlBuilder = new StringBuilder();

            

            htmlBuilder.append("<html>").

                    append("<body>").

                    append("<h1>").

                    append("I got ")

                    .append(requestParamValue)
                    
                    .append(" values !")

                    .append("</h1>")

                    .append("</body>")

                    .append("</html>");


            // encode HTML content 

            String htmlResponse = htmlBuilder.toString(); //StringEscapeUtils.escapeHtml4(htmlBuilder.toString());

     

            // this line is a must

            httpExchange.sendResponseHeaders(200, htmlResponse.length());


            outputStream.write(htmlResponse.getBytes());

            outputStream.flush();

            outputStream.close();

        }
   
}
		