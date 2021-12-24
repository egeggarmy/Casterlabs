package co.casterlabs.caffeinated.builtin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.Nullable;

import co.casterlabs.caffeinated.bootstrap.FileUtil;
import co.casterlabs.caffeinated.builtin.widgets.ChatWidget;
import co.casterlabs.caffeinated.builtin.widgets.EmojiRainWidget;
import co.casterlabs.caffeinated.builtin.widgets.NowPlayingWidget;
import co.casterlabs.caffeinated.builtin.widgets.labels.FollowerCountLabel;
import co.casterlabs.caffeinated.builtin.widgets.labels.SubscriberCountLabel;
import co.casterlabs.caffeinated.builtin.widgets.labels.ViewersCountLabel;
import co.casterlabs.caffeinated.pluginsdk.CaffeinatedPlugin;
import co.casterlabs.caffeinated.pluginsdk.PluginImplementation;
import co.casterlabs.caffeinated.util.WebUtil;
import co.casterlabs.rakurai.io.IOUtil;
import lombok.NonNull;
import okhttp3.Request;

@PluginImplementation
public class CaffeinatedDefaultPlugin extends CaffeinatedPlugin {
    public static final String DEV_ADDRESS = "http://localhost:4088";

    @Override
    public void onInit() {
        // I spend way too long on this shit.
        this.getLogger().info(" _________________");
        this.getLogger().info("|       Hi!       |");
        this.getLogger().info("|   My name is:   |");
        this.getLogger().info("|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|");
        this.getLogger().info("|   Casterlabs    |");
        this.getLogger().info("|                 |");
        this.getLogger().info("|                 |");
        this.getLogger().info(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾ ");

        // Interaction
        this.getPlugins().registerWidget(this, ChatWidget.DETAILS, ChatWidget.class);
        this.getPlugins().registerWidget(this, EmojiRainWidget.DETAILS, EmojiRainWidget.class);

        // Labels
        this.getPlugins().registerWidget(this, FollowerCountLabel.DETAILS, FollowerCountLabel.class);
        this.getPlugins().registerWidget(this, SubscriberCountLabel.DETAILS, SubscriberCountLabel.class);
        this.getPlugins().registerWidget(this, ViewersCountLabel.DETAILS, ViewersCountLabel.class);

        // Other
        this.getPlugins().registerWidget(this, NowPlayingWidget.DETAILS, NowPlayingWidget.class);

        // Alerts
//        this.getPlugins().registerWidget(this, DonationAlert.DETAILS, DonationAlert.class);
//        this.getPlugins().registerWidget(this, FollowAlert.DETAILS, FollowAlert.class);

    }

    @Override
    public void onClose() {

    }

    @Override
    public @NonNull String getName() {
        return "Casterlabs Default Widgets";
    }

    @Override
    public @NonNull String getId() {
        return "co.casterlabs.defaultwidgets";
    }

    // This allows us to either:
    // 1) Grab resources out of the jar normally.
    // or
    // 2) Grab resources from the dev environment, since we're bundled in a
    // different way from the typical plugin setup.
    public static @Nullable String resolveResource(@NonNull String path) throws IOException {
        final String resource = "/chat.html";

        if (CaffeinatedPlugin.isDevEnvironment()) {
            return WebUtil.sendHttpRequest(new Request.Builder().url(CaffeinatedDefaultPlugin.DEV_ADDRESS + resource));
        } else {
            InputStream in = FileUtil.class.getClassLoader().getResourceAsStream("widgets" + path);

            return IOUtil.readInputStreamString(in, StandardCharsets.UTF_8);
        }
    }

}
