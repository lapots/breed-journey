package com.lapots.gradle.plugins.redis.actions

import org.apache.http.HttpHost
import org.apache.http.config.Registry
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.BasicHttpClientConnectionManager
import org.gradle.api.Project

/**
 * Created by Chucke on 15.10.2016.
 *
    src 'https://github.com/MSOpenTech/redis/releases/download/win-3.2.100/Redis-x64-3.2.100.zip'
    dest new File("$rootDir/applications/install", 'Redis-x64-3.2.100.zip')
    onlyIfNewer true
 *
 * */
class DownloadAction implements DownloadSpec {
    File dest
    boolean onlyIfNewer
    Project project
    URL source

    public DownloadAction(Project project) {
        this.project = project
    }

    public void execute() {
        if (!source) { throw new IllegalArgumentException("No [src] found!") }
        if (!dest) { throw new IllegalArgumentException("No [dest] found!") }
        if (dest.equals(project.getBuildDir())) { dest.mkdirs() }

        if (!dest.isDirectory()) {
            if (!dest.exists()) { dest.mkdirs() }
        }

        execute(source)
    }

    public void execute(URL src) {
        File destFile = makeDestFile(src)

        long timestamp = onlyIfNewer && destFile.exists() ? destFile.lastModified() : 0

        HttpHost httpHost = new HttpHost(src.getHost(), src.getPort(), src.getProtocol())
        CloseableHttpClient client = createHttpClient(httpHost);
    }

    private CloseableHttpClient createHttpClient(HttpHost host) {
        HttpClientBuilder builder = HttpClientBuilder.create();

        //accept any certificate if necessary
        if ("https".equals(httpHost.getSchemeName()) && acceptAnyCertificate) {
            SSLConnectionSocketFactory icsf = getInsecureSSLSocketFactory();
            builder.setSSLSocketFactory(icsf);
            Registry<ConnectionSocketFactory> registry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("https", icsf)
                            .build();
            HttpClientConnectionManager cm =
                    new BasicHttpClientConnectionManager(registry);
            builder.setConnectionManager(cm);
        }

        //add an interceptor that replaces the invalid Content-Type
        //'none' by 'identity'
        builder.addInterceptorFirst(new ContentEncodingNoneInterceptor());

        CloseableHttpClient client = builder.build();
        return client;
    }

    private File makeDestFile(URL src) {
        if (dest == null) { throw new IllegalArgumentException("No [dest] found!"); }

        File destFile = dest;
        if (destFile.isDirectory()) {
            String name = src.toString();
            if (name.endsWith("/")) { name = name.substring(0, name.length() - 1); }
            name = name.substring(name.lastIndexOf('/') + 1);
            destFile = new File(dest, name);
        } else {
            File parent = destFile.getParentFile();
            if (parent != null) { parent.mkdirs(); }
        }
        return destFile;
    }

    @Override
    void src(Object src) {
        if (src instanceof Closure) { src = src.call() }
        if (src instanceof CharSequence) { source = new URL(src.toString()) }
    }

    @Override
    void dest(Object dest) {
        if (dest instanceof Closure) { dest = dest.call() }
        if (dest instanceof CharSequence) { this.dest = project.file(dest.toString()) }
        else if (dest instanceof File) { this.dest = dest as File }
    }

    @Override
    void onlyIfNewer(boolean onlyIfNewer) { this.onlyIfNewer = onlyIfNewer }
}
