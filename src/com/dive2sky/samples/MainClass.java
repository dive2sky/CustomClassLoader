package com.dive2sky.samples;

public class MainClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {


        ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
        MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
        Class myObjectClass = classLoader.loadClass("com.dive2sky.samples.MyObject");

        MyObjectSuperClass obj = (MyObjectSuperClass) myObjectClass.newInstance();

        while(true) {
            //create new class loader so classes can be reloaded.
            classLoader = new MyClassLoader(parentClassLoader);
            myObjectClass = classLoader.loadClass("com.dive2sky.samples.MyObject");
            obj = (MyObjectSuperClass) myObjectClass.newInstance();
            obj.printMessage();
            Thread.sleep(2000);
        }


    }
}
