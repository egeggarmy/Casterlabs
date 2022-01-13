package co.casterlabs.caffeinated.bootstrap.linux;

import co.casterlabs.caffeinated.bootstrap.Bootstrap;
import co.casterlabs.caffeinated.bootstrap.NativeSystem;
import co.casterlabs.caffeinated.bootstrap.linux.music.LinuxSystemPlaybackMusicProvider;
import co.casterlabs.caffeinated.bootstrap.webview.impl.CefWebview;

public class LinuxBootstrap {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        LinuxSystemPlaybackMusicProvider playbackProvider = null;

        if (LinuxSystemPlaybackMusicProvider.isPlayerCtlInstalled()) {
            playbackProvider = new LinuxSystemPlaybackMusicProvider();
        }

        NativeSystem.initialize(
            false,
            null,
            playbackProvider,
            CefWebview.FACTORY // TODO Move to WebKit until CEF gets their shit together.
        );

        Bootstrap.main(args);
    }

}
