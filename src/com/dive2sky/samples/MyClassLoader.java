package com.dive2sky.samples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parentClassLoader) {
        super(parentClassLoader);
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        if(!"com.dive2sky.samples.MyObject".equals(name))
            return super.loadClass(name);

        try {
            String url = "out/production/CustomClassLoader/com/dive2sky/samples/MyObject.class";
            URL myUrl = Paths.get(url).toUri().toURL();
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("com.dive2sky.samples.MyObject",
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
