package com.ahsiu.studytest;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ahsiu.studytest.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Proxy;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    OkHttpClient client = new OkHttpClient.Builder().proxy(Proxy.NO_PROXY).build();
    private Spinner mSpinner;
    private TextView mMsgTv;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = (Spinner) findViewById(R.id.sp_urls);
        mMsgTv = (TextView) findViewById(R.id.tv_msg);
        mEditText = (EditText) findViewById(R.id.et_url);
        mMsgTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        ((RadioGroup) findViewById(R.id.radio)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ck_default:
                        client = new OkHttpClient.Builder().proxy(Proxy.NO_PROXY).build();
                        break;
                    case R.id.ck_all:
                        client = getUnsafeOkHttpClient();
                        break;
                    case R.id.ck_one:
                        client = getIboxpayClient();
                        break;
                }
            }
        });
        Log.DEBUG = true;
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String content = (String) mSpinner.getItemAtPosition(position);
                mEditText.setText(content);
                mMsgTv.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.proxy(Proxy.NO_PROXY);
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static OkHttpClient getIboxpayClient() {
        String pubStr = "-----BEGIN CERTIFICATE-----\n" +
                "MIIF0DCCBLigAwIBAgIQbHwj9uSgT5HQm8lUeyCSITANBgkqhkiG9w0BAQsFADB3\n" +
                "MQswCQYDVQQGEwJVUzEdMBsGA1UEChMUU3ltYW50ZWMgQ29ycG9yYXRpb24xHzAd\n" +
                "BgNVBAsTFlN5bWFudGVjIFRydXN0IE5ldHdvcmsxKDAmBgNVBAMTH1N5bWFudGVj\n" +
                "IENsYXNzIDMgRVYgU1NMIENBIC0gRzMwHhcNMTYwNDIxMDAwMDAwWhcNMTcwNjIx\n" +
                "MjM1OTU5WjCCAVkxEzARBgsrBgEEAYI3PAIBAxMCQ04xHTAbBgNVBA8TFFByaXZh\n" +
                "dGUgT3JnYW5pemF0aW9uMRgwFgYDVQQFEw80NDAzMDExMDU1NzczODgxCzAJBgNV\n" +
                "BAYTAkNOMQ8wDQYDVQQRDAY1MTgwMDMxEjAQBgNVBAgMCWd1YW5nZG9uZzERMA8G\n" +
                "A1UEBwwIc2hlbnpoZW4xYTBfBgNVBAkMWFJvb20gNTAxLUIgRjUgTG9uZ3RhaWxp\n" +
                "IEJ1aWxkaW5nIE5vLjMwIEdhb3hpbnpob25nIEZvdXIgQXZlbnVlIE5hbnNoYW4g\n" +
                "RGlzdHJpY3QgU2hlbnpoZW4xODA2BgNVBAoML1NoZW4gWmhlbiBpQk9YUEFZIElu\n" +
                "Zm9ybWF0aW9uIFRlY2hub2xvZ3kgQ28sTHRkMQ0wCwYDVQQLDARJVE9NMRgwFgYD\n" +
                "VQQDDA93d3cuaWJveHBheS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK\n" +
                "AoIBAQDlCBKAn8PlngnY7F25EANMNWv817X8ZIO5F0HmO4CIlo6Z+fjNHsiVRq9f\n" +
                "ObZPII3lEjrI+bShxJzXzyLHWMeoi+x4XSX8l8VInmjN5dN4WoZWY5/HUrbtesNu\n" +
                "5P1G18nzC7E+sdvdRYx5gVU/mb7H/roJCUO9TTzWPZ5TFLLVjs+A+c5vZxPyiCMP\n" +
                "HZXAKJVmPuOBEja+p4nDZaFg1QFK6Go2lOlmJeXDaDl/XF5j0K1aqoz7x1K0eDQi\n" +
                "gXjGtpfbzs9nCFuoR723sbolF63gXDI8XxbQyv7zIirl3w6HhUdvqHr+1UKm34yA\n" +
                "9B9KifG39s4OkQbHcspltb6+lXF3AgMBAAGjggFyMIIBbjAaBgNVHREEEzARgg93\n" +
                "d3cuaWJveHBheS5jb20wCQYDVR0TBAIwADAOBgNVHQ8BAf8EBAMCBaAwHQYDVR0l\n" +
                "BBYwFAYIKwYBBQUHAwEGCCsGAQUFBwMCMG8GA1UdIARoMGYwBwYFZ4EMAQEwWwYL\n" +
                "YIZIAYb4RQEHFwYwTDAjBggrBgEFBQcCARYXaHR0cHM6Ly9kLnN5bWNiLmNvbS9j\n" +
                "cHMwJQYIKwYBBQUHAgIwGQwXaHR0cHM6Ly9kLnN5bWNiLmNvbS9ycGEwHwYDVR0j\n" +
                "BBgwFoAUAVmr5906C1mmZGPWzyAHV9WR52owKwYDVR0fBCQwIjAgoB6gHIYaaHR0\n" +
                "cDovL3NyLnN5bWNiLmNvbS9zci5jcmwwVwYIKwYBBQUHAQEESzBJMB8GCCsGAQUF\n" +
                "BzABhhNodHRwOi8vc3Iuc3ltY2QuY29tMCYGCCsGAQUFBzAChhpodHRwOi8vc3Iu\n" +
                "c3ltY2IuY29tL3NyLmNydDANBgkqhkiG9w0BAQsFAAOCAQEADVt+PEVYN3CiOcwY\n" +
                "il+z9DjA8mhg8trtQgcH/qD4iYXRTbM1iY124Pbk1RCsm7IOpbZVqSEVhxQNyu5U\n" +
                "MjC0DxypKznpg7POjeZYsB/F3My6EXaUPzwdV55t+C9rIzrOdGNCkHmsGD9kP3TJ\n" +
                "N+Q6oSxL9iOO/ZdrJHmCjyp6JdAPUaVtLTMjQgE6hBV2aeSGzNi1hoBVs6AlUBmR\n" +
                "JEcLtg4Gl5yUf9BhG+Rsf0EzDosbcQ3mm+Ep2QTCIqBAQB6/sVOLQ9FFvMI0zq6b\n" +
                "13iTavjFtFRVYBD9jPugGoFO+hlfmHZZOEWE8B44C+SYTr3WrzRogNBIx84BZKMr\n" +
                "ZcBS+A==\n" +
                "-----END CERTIFICATE-----";
        try {
            // Load CAs from an InputStream
// (could be from a resource or ByteArrayInputStream or ...)
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
// From https://www.washington.edu/itconnect/security/ca/load-der.crt
            InputStream caInput = new BufferedInputStream(new ByteArrayInputStream(pubStr.getBytes()));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

// Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

// Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

// Create an SSLContext that uses our TrustManager


            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.proxy(Proxy.NO_PROXY);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadUrl(View view) {
        String url = mEditText.getText().toString();
        Observable.just(url).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                Request request = new Request.Builder()
                        .url(s)
                        .build();
                Response response = client.newCall(request).execute();
                return response.headers().toString();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mMsgTv.setText(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mMsgTv.setText((throwable instanceof SSLHandshakeException ? "证书错误" : "")
                        + throwable.toString());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mMsgTv.append("complete");
            }
        });
    }
}
