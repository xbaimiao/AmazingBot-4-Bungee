package me.albert.amazingbot;

import me.albert.amazingbot.bot.Bot;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class AmazingBot extends Plugin {

    private static ProxyServer proxyServer;

    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    private static AmazingBot instance;

    public static AmazingBot getInstance() {
        return instance;
    }

    private Configuration configuration;

    public Configuration getConfig() {
        return configuration;
    }

    private boolean enable = false;

    public static boolean getDebug() {
        return instance.getConfig().getBoolean("debug");
    }

    @Override
    public void onEnable() {
        enable = true;
        proxyServer = getProxy();
        instance = this;
        loadConfig();
        Bot.start();
        getLogger().info("AmazingBot - 4.0.10bungee魔改版 已启动 作者 -> 小白");
    }

    @Override
    public void onDisable() {
        enable = false;
        Bot.stop();
    }

    public boolean isEnabled() {
        return enable;
    }

    private void loadConfig() {
        if (!getDataFolder().exists()) {
            if (getDataFolder().mkdir()) {
                getLogger().info("创建插件配置文件夹");
            }
        }
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
