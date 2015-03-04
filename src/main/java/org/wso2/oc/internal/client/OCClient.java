package org.wso2.oc.internal.client;

/**
 * Created by chaya on 3/2/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class OCClient {
    public static void main(String[] args) {

    //post method
        try {
            URL url = new URL("http://10.100.4.138:9763/OCServer/services/oc-server/internal/oc/servers/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String input="{\"ip\":\"10.100.4.113\",\"serverName\":\"Application Server\",\"serverVersion\":\"5.2.1\",\"domain\":\"wso2.as.domain\",\"subDomain\":\"worker\",\"adminServiceUrl\":\"https://10.100.4.113:9455/\",\"startTime\":\"2015-02-18 15:15:50\",\"os\":\"Linux Mint 17 Qiana\",\"totalMemory\":\"8090.402816\",\"cpuCount\":\"4\",\"cpuSpeed\":\"2.4\",\"timestamp\":\"1424252819300\",\"patches\":[\"patch0000\",\"patch0001\",\"patch0002\",\"patch0003\"]}";


            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        //put method
        try {
            URL url = new URL("http://10.100.4.138:9763/OCServer/services/oc-server/internal/oc/servers/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            String input1="{\"freeMemory\":\"2031.464448\",\"idleCpuUsage\":\"51.67310777925644\",\"systemCpuUsage\":\"11.751442341969984\",\"userCpuUsage\":\"36.57544357970618\",\"adminServiceUrl\":\"https://10.100.4.113:9455/\",\"serverUpTime\":\"0d 0h 1m 8s\",\"threadCount\":\"62\",\"systemLoadAverage\":\"3.58\",\"timestamp\":\"1424252819302\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input1.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server put .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
