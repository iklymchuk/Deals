package ua.ukrdev.deal.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Eugene on 21.09.2014.
 */
public class Net {

    public String send(String method, String scheme, String host, int port, String path,  Map params) throws IOException {

        String responseAsString = null;
        org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
        java.util.List<NameValuePair> qparams = new ArrayList<NameValuePair>();

        Iterator entries = params.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            Object key = thisEntry.getKey();
            Object value = thisEntry.getValue();
            qparams.add(new BasicNameValuePair(key.toString(), value.toString()));

        }


        try {

               URI uri = URIUtils.createURI(scheme, host, port, path,
                        URLEncodedUtils.format(qparams, "UTF-8"), null);



            org.apache.http.client.methods.HttpUriRequest req;
            if (method.equalsIgnoreCase("POST")) req = new HttpPost(uri); else req = new HttpGet(uri);

            try {
                HttpResponse response = httpclient.execute(req);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    responseAsString = org.apache.commons.io.IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        } catch (URISyntaxException ur) {
            ur.printStackTrace();
        }
        return responseAsString;
    }


    public String send(String method, String url, String params) throws URISyntaxException {
        String responseAsString = null;
        org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

        try {

            URI uri = new URI(url+"?"+params);


            org.apache.http.client.methods.HttpUriRequest req;
            if (method.equalsIgnoreCase("POST")) req = new HttpPost(uri); else req = new HttpGet(uri);

            try {
                HttpResponse response = httpclient.execute(req);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    responseAsString = org.apache.commons.io.IOUtils.toString(instream, "UTF-8");
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        } catch (URISyntaxException ur) {
            ur.printStackTrace();
        }
        return responseAsString;
    }


}
