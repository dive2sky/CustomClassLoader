package com.dive2sky.samples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MainClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException, IOException {


        ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
        //MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
        URL classUrl = new URL("file:" + "out/production/CustomClassLoader/com/dive2sky/samples/MyObject.class" );
        URLClassLoader classLoader = new URLClassLoader(new URL[]{classUrl});

        Class myObjectClass = classLoader.loadClass("com.dive2sky.samples.MyObject");

        MyObjectSuperClass obj = (MyObjectSuperClass) myObjectClass.newInstance();

        while(true) {
            //create new class loader so classes can be reloaded.
            //classLoader = new MyClassLoader(parentClassLoader);

            if( classLoader != null )
                classLoader.close();
            classLoader = new URLClassLoader(new URL[]{classUrl});
            myObjectClass = classLoader.loadClass("com.dive2sky.samples.MyObject");
            obj = (MyObjectSuperClass) myObjectClass.newInstance();
            obj.printMessage();
            Thread.sleep(2000);
        }


    }
}
