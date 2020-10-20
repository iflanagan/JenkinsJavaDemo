package com.IanFlanagan;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RESTAPITest {

    public static void main(String[] args) throws IOException {

        String command = " curl https://api.rollbar.com/api/1/status/ping";
        String myaccesstoken = "6c96e43dab914f658b12a1e1a1ca5517" ;
        String myenv = "DEV";
        String buildNum = "1.9";
        String myUser = "ianianf";

        String mysecondcommand = "curl https://api.rollbar.com/api/1/deploy/ -F access_token=" +myaccesstoken+
                " -F environment=" +myenv+
                " -F revision=" +buildNum+
                " -F local_username=" +myUser;

        System.out.println("My Second Command: " +mysecondcommand);;

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String response = br.readLine();
        System.out.println(response);

        System.out.println("========new one now======");

         rt = Runtime.getRuntime();
         pr = rt.exec(mysecondcommand);
         br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        while ((response = br.readLine()) != null) {

            if (response.contains("deploy_id")) {

                Matcher matcher = Pattern.compile(":").matcher(response);
                if(matcher.find()){

                    System.out.println(response.substring(matcher.end()).trim());

                }

          //     System.out.println(response);

            }

        }

        /*
curl https://api.rollbar.com/api/1/deploy/ \
  -F access_token=$myaccesstoken \
  -F environment=$myenv \
  -F revision=$MYLATESTCOMMIT \
  -F local_username=$MYUSER
         */

    }
}
