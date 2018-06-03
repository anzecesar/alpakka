/*
 * Copyright (C) 2016-2018 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.stream.alpakka.ftp.examples;

//#create-settings
import akka.stream.alpakka.ftp.FtpCredentials;
import akka.stream.alpakka.ftp.FtpSettings;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class FtpSettingsExample {
    private FtpSettings settings;

    public FtpSettingsExample() {
        try {
            settings = FtpSettings.create(
                InetAddress.getByName("localhost"))
                .withPort(FtpSettings.DefaultFtpPort())
                .withCredentials(FtpCredentials.createAnonCredentials())
                .withBinary(false)
                .withPassiveMode(false)
                .withConfigureConnection((FTPClient ftpClient) -> {
                    ftpClient.addProtocolCommandListener(
                            new PrintCommandListener(new PrintWriter(System.out), true)
                    );
                    return null;
                });
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
//#create-settings