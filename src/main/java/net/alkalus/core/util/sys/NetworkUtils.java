package net.alkalus.core.util.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import net.alkalus.api.objects.misc.AcLog;

public class NetworkUtils {

    public static String getContentFromURL(final String args) {
        if (checkNetworkIsAvailableWithValidInterface()) {
            try {
                URL url;
                // get URL content
                url = new URL(args);
                final URLConnection conn = url.openConnection();
                // open the stream and put it into BufferedReader
                final BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                String tempLine = null;
                while ((inputLine = br.readLine()) != null) {
                    tempLine = inputLine;
                }
                br.close();
                return tempLine;
            } catch (final MalformedURLException e) {
                AcLog.INFO("Bad URL for Version Check.");
            } catch (final IOException e) {
                AcLog.INFO("IOException during Version Check.");
            }
        }
        AcLog.INFO("Network Not Available during Version Check.");
        return "offline";
    }

    public static boolean checkNetworkIsAvailableWithValidInterface() {
        try {
            if (hasValidNetworkInterface()) {
                if (checkAddressWithTimeout("http://www.google.com", 10)
                        || checkAddressWithTimeout("http://www.baidu.com", 10)
                        || checkAddressWithTimeout(
                                "https://github.com/draknyte1/GTplusplus", 10)
                        || checkAddressWithTimeout("www.yahoo.com",
                                10)/*
                                    * || netIsAvailableGoogle() ||
                                    * netIsAvailableBaidu() ||
                                    * netIsAvailableGithub() ||
                                    * netIsAvailableOther()
                                    */) {
                    return true;
                } else {
                    AcLog.INFO(
                            "No sites responded to network connectivity test.");
                }
            } else {
                AcLog.INFO("Network Adapter was not valid.");
            }
        } catch (final SocketException e) {
        }
        return false;
    }

    private static boolean netIsAvailableGoogle() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            return false;
        }
    }

    private static boolean netIsAvailableBaidu() {
        try {
            final URL url = new URL("http://www.baidu.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            return false;
        }
    }

    private static boolean netIsAvailableGithub() {
        try {
            final URL url = new URL("https://github.com/draknyte1/GTplusplus");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (final IOException e) {
            return false;
        }
    }

    private static boolean netIsAvailableOther() {
        try {
            final int timeout = 200;
            final InetAddress[] addresses = InetAddress
                    .getAllByName("www.yahoo.com");
            for (final InetAddress address : addresses) {
                if (address.isReachable(timeout)) {
                    return true;
                }
                return false;
            }
        } catch (final Exception e) {
            return false;
        }
        return false;
    }

    private static boolean checkAddressWithTimeout(final String URL, final int timeout) {

        try {
            InetAddress.getByName(URL).isReachable(3000); // Replace with your
                                                          // name
            return true;
        } catch (final Exception e) {
            return false;
        }

        /*
         * try { final InetAddress[] addresses = InetAddress.getAllByName(URL);
         * for (final InetAddress address : addresses) { if
         * (address.isReachable(timeout)) { return true; } return false; } }
         * catch (final Exception e) { return false; } return false;
         */
    }

    private static boolean hasValidNetworkInterface() throws SocketException {
        final Enumeration<NetworkInterface> interfaces = NetworkInterface
                .getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            final NetworkInterface interf = interfaces.nextElement();
            if (interf.isUp() && !interf.isLoopback()) {
                return true;
            }
        }
        return false;
    }

}
