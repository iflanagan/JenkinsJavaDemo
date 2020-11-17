package com.IanFlanagan;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RESTAPITest {

    private static final String command = " curl https://api.rollbar.com/api/1/status/ping";
    private static final String myaccesstoken = "<post_server_item>";
    private static final String myenv = "DEV";
    private static final String buildNum = "1.9";
    private static final String myUser = "ianianf";
    private static final String myExpectedResponse = "pong";

    private static final String mysecondcommand = "curl https://api.rollbar.com/api/1/deploy/ -F access_token=" + myaccesstoken +
            " -F environment=" + myenv +
            " -F revision=" + buildNum +
            " -F local_username=" + myUser;

    public static void main(String[] args) throws IOException {

       boolean isUp =  checkRollbarAPI();
       if (isUp) {

           rollbarDeploy();

       } else {
           System.out.println("Can't call Rollbar API now");
       }
/*
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String response = br.readLine();
        System.out.println(response);*/

       /* System.out.println("========new one now======");

        rt = Runtime.getRuntime();
        pr = rt.exec(mysecondcommand);
        br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        while ((response = br.readLine()) != null) {

            if (response.contains("deploy_id")) {

                Matcher matcher = Pattern.compile(":").matcher(response);
                if (matcher.find()) {

                    System.out.println(response.substring(matcher.end()).trim());

                }

                //     System.out.println(response);

            }

        }*/


    }

    public static boolean checkRollbarAPI() {

        boolean isAPIUp = false;

        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String response = br.readLine();
            if (response.equals(myExpectedResponse)) {

                System.out.println("Rollbar API test Passed.");
                isAPIUp = true;
            } else {
                System.out.println("Rollbar API test failed");
            }
          //  System.out.println(response);
        } catch (IOException e) {
            System.out.println("Can't check Rollbar API status: " +e.getMessage());
            e.printStackTrace();
        }
        return isAPIUp;
    }

    public static boolean rollbarDeploy() throws IOException {

        boolean isDeployed = false;

        System.out.println("My Second Command: " + mysecondcommand);

        try
        {
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
                    if (matcher.find()) {
                        isDeployed = true;
                       // System.out.println(response.substring(matcher.end()).trim());
                        System.out.println("Rollbar Deployment for Environment: " +myenv+ " and Build: " +buildNum+ " was successful");
                    } else {
                        System.out.println("Rollbar Deployment for Environment: " +myenv+ " and Build: " +buildNum+ " was successful");
                    }
                }
            }
        } catch (IOException e ) {
            System.out.println("Can't Run Rollbar Deploy API: " +e.getMessage());
            e.printStackTrace();
        }
        return isDeployed;
    }
}
