package com.IanFlanagan;


import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import com.rollbar.notifier.config.ConfigProvider;

import com.rollbar.api.payload.data.Server;
import com.rollbar.notifier.provider.Provider;

public class IanProvider implements ConfigProvider {

    @Override
    public Config provide(ConfigBuilder builder) {
        return builder.server(new ServerProvider()).build();
    }
}

class ServerProvider implements Provider<Server> {

    @Override
    public Server provide() {
        return new Server.Builder()
                .codeVersion(MyConfiguration.myCodeVersion)
                .branch(MyConfiguration.myBranch)
                .host(MyConfiguration.myHost)
                .root(MyConfiguration.myRoot)
                .build();
    }
}