package com.hskj.single.demo;

/**
 * 
 * 项目名称：design-pattern-demo 类名称：LazySingleton 类描述： 创建人：1207263 创建时间：2014-6-3
 * 上午10:23:23 修改人：1207263 修改时间：2014-6-3 上午10:23:23 修改备注：
 * 
 * @version
 * 
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private volatile static LazySingleton volatileInstance = null;

    private LazySingleton() {

    }

    /**
     * 简单的单利模式
     */
    @SuppressWarnings("unused")
    private static LazySingleton getInstance01() {

        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;

    }

    /*
     * 是要getInstance()加上同步锁，一个线程必须等待另外一个线程创建完成后才能使用这个方法，这就保证了单例的唯一性。
     */
    public static LazySingleton getInstance02() {
        synchronized (LazySingleton.class) {
            if (instance == null) {
                instance = new LazySingleton();
            }
        }
        return instance;
    }

    // getInstance02次调用getInstance()的时候必然要同步，性能问题还是存在,改进:

    public static LazySingleton getInstance03() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    /*
     * 线程A开始创建LazySingleton的实例，此时线程B调用了getInstance()方法，首先判断instance是否为null。
     * 按照我们上面所说的内存模型，A已经把instance指向了那块内存，只是还没有调用构造方法，因此B检测到instance不为null，
     * 于是直接把instance返回了——问题出现了，尽管instance不为null，但它并没有构造完成，就像一套房子已经给了你钥匙，
     * 但你并不能住进去，因为里面还没有收拾。此时，如果B在A将instance构造完成之前就是用了这个实例，程序就会出现错误了！
     * 
     * 
     * 同步块的释放保证在此之前——也就是同步块里面——的操作必须完成，但是并不保证同步块之后的操作不能因编译器优化而调换到同步块结束之前进行。因此，
     * 编译器完全可以把instance=sc;这句移到内部同步块里面执行。这样，程序又是错误的
     */
    public static LazySingleton getInstance04() {
        if (instance == null) {
            LazySingleton sc;
            synchronized (LazySingleton.class) {
                sc = instance;
                if (sc == null) {
                    synchronized (LazySingleton.class) {
                        if (sc == null) {
                            sc = new LazySingleton();
                        }
                    }
                    instance = sc;
                }
            }
        }
        return instance;
    }

    /*
     * JDK
     * 5之后，Java使用了新的内存模型。volatile关键字有了明确的语义——在JDK1.5之前，volatile是个关键字，但是并没有明确的规定其用途
     * ——被volatile修饰的写变量不能和之前的读写代码调整，读变量不能和之后的读写代码调整！因此，
     * 只要我们简单的把instance加上volatile关键字就可以了
     */

    public static LazySingleton getInstance05() {
        if (volatileInstance == null) {
            synchronized (LazySingleton.class) {
                if (volatileInstance == null) {
                    volatileInstance = new LazySingleton();
                }
            }
        }
        return volatileInstance;
    }

    /*
     * Java的静态内部类。这一技术是被JVM明确说明了的，因此不存在任何二义性。在这段代码中，因为SingletonClass没有static的属性，
     * 因此并不会被初始化。直到调用getInstance()的时候，会首先加载SingletonClassInstance类，
     * 这个类有一个static的SingletonClass实例
     * ，因此需要调用SingletonClass的构造方法，然后getInstance()将把这个内部类的instance返回给使用者
     * 。由于这个instance是static的，因此并不会构造多次。
     * 由于SingletonClassInstance是私有静态内部类，所以不会被其他类知道，同样，static语义也要求不会有多个实例存在。并且，
     * JSL规范定义，类的构造必须是原子性的，非并发的，因此不需要加同步块。同样，由于这个构造是并发的，所以getInstance()也并不需要加同步。
     */

    private static class SingletonClassInstance {
        private static final LazySingleton instance = new LazySingleton();
    }

    public static LazySingleton getInstance06() {
        return SingletonClassInstance.instance;
    }

    /*
     * 项目中使用单例的场景： msg_id是唯一自增长的，在0-127之间循环，所以变量msg_id必然是单例
     */

}
