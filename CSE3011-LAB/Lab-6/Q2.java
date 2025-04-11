import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Q2 {

    private static OkHttpClient client;

    public static void setupClient(long cacheSize) {
        File cacheDirectory = new File("cache");
        Cache cache = new Cache(cacheDirectory, cacheSize);
        client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    public static void testRequest(String url, String cacheControlValue) throws IOException {
        CacheControl cacheControl = new CacheControl.Builder()
                .noCache() 
                .build();

        Request request = new Request.Builder()
                .url(url)
                .cacheControl(cacheControl) 
                .build();

        long startTime = System.nanoTime();
        try (Response response = client.newCall(request).execute()) {
            
            if (response.body() != null) {
                response.body().string();
            }
        }
        long duration = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);

        System.out.println("Request with Cache-Control: " + cacheControlValue + " took " + duration + " ms");
    }

    public static void main(String[] args) throws IOException {
        String url = "http://example.com";


        setupClient(10 * 1024 * 1024); // 10 MiB


        testRequest(url, "no-cache");
        testRequest(url, "no-store");
        testRequest(url, "max-age=0");
        testRequest(url, "s-maxage=0");
    }
}
