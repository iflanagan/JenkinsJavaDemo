package com.IanFlanagan;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;

import static com.rollbar.notifier.config.ConfigBuilder.withAccessToken;

public class Utils {

    private static Rollbar rollbar;
    private static String accessToken;
    private static String env;
    private static String version;

    public static Rollbar createRBinstanceConfig(String accessToken, String environment, String version) {


        if (accessToken == null) {
            System.out.printf("Please enter a valid rollbar Access Token");
            return null;
        }

        try
        {
            Rollbar rollbar = Rollbar.init(withAccessToken(System.getenv("ROLLBAR_ACCESS_TOKEN"))
                    .environment(System.getenv("ENVIRONMENT"))
                    .codeVersion(System.getenv("CODE_VERSION"))
                    .person(new MyPersonProvider())
                    .server(new ServerProvider())
                    .build());

        } catch (Exception e) {

            System.out.printf("can't create rollbar instance: " +e.getMessage());
            e.printStackTrace();
            rollbar = null;
        }
        return rollbar;
    }
}
