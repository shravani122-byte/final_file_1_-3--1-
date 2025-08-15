package com.raksha_kavach.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginAuthController {
       
      private  static final String API_KEY ="AIzaSyDAjZo-VlwU5_S4yxDZ8VIEUyO7a9jfp0A";
        public boolean signInWithEmailAndPassword(String email,String password){

        try{
            URL url =new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="+API_KEY);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            String payload=String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",email,password);
            OutputStream os=null;
            os=conn.getOutputStream();
            os.write(payload.getBytes());
            int responseCode=conn.getResponseCode();
            if(responseCode == 200){
              // System.out.println("Login Succesful");
               return true;
            }
            else{
               //System.out.println("login invalid");
               try(BufferedReader br =new BufferedReader(new InputStreamReader(conn.getErrorStream()))){
                String line;
                while((line=br.readLine())!=null){
                    System.out.println(line);
                }

            }
            return false;
        }

        
    }catch(Exception ae){
             ae.printStackTrace();
             return false;
        }
        
    }
}

    
    
    





