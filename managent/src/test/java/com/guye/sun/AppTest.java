package com.guye.sun;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        /*int [] a = {1,2,3,4,5,6,7,8};
        int [] b = {9,10,11,12,13,14};
        System.arraycopy(a,0,b,2,3);
        for (int i = 0;i < b.length;i++){
            System.out.println(b[i]);   //9,10,1,2,3,14
        }
        System.out.println(b.length);*/

        /*AppTest appTest = new AppTest();
        char c = 'b';
        int d = c;
        System.out.println(d);
        appTest.test(c);*/

//        String a = "aaaaa";
//        String b = new String("aaaaa");
//        String c = b;
//        System.out.println(a.equals(b));    //true
//        System.out.println(a.equals(c));    //true
//        System.out.println(b.equals(c));    //true
//        System.out.println(a == b);         //false
//        System.out.println(a == c);         //false
//        System.out.println(b == c);         //true

//        String e = "";
//        String f = null;
//        System.out.println(e.equals(f));      //false
//        System.out.println(e == f);           //false

        Calendar calendar = Calendar.getInstance();
//        String str1 = "2011-11-11 12:12:12";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = "2011-11-11";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str1);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        System.out.println(date);
        System.out.println(calendar.getTime());

    }

    public void test(int a){
        System.out.println("aaaaaa");
        System.out.println(a);
    }
    public void test(String b){
        System.out.println("bbbbbb");
        System.out.println(b);
    }

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        System.out.println("=========list的长度=="+list.size());
        list.clear();
        System.out.println("=========list的长度=="+list.size());
    }
}

class D{
    int count;
    public D(int count){
        this.count = count;
    }

    public boolean equals(Object obj) {
//        if (obj == this) {
//            return true;
//        }
//        if (obj != null && obj.getClass() == D.class) {
//            D d = (D) obj;
//            return this.count == d.count;
//        }
        return true;
    }


    public int hashCode(){
        return this.count;
    }
}

class E{
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

class HashTableTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Hashtable ht = new Hashtable();
        ht.put(new D(6000), "java");
        ht.put(new D(87563), "C++");
        ht.put(new D(1232), new E());
        System.out.println(ht);

		/*只要equals方法返回true
		 * Hashtable就认为他们是相等的value
		 * Hashtable有一个对象E对象
		 * 它与任何对象通过equal方法比较都相等，所以下面输出是true
		 */
        System.out.println(ht.containsValue("testing value"));
		/*
		 * 只要D两个对象的count相等，通过equals()方法比较返回true，且hashcode相等
		 * Hashtable就认为它们相等,所以下面返回true
		 * */
        System.out.println(ht.containsValue("java"));
		/*
		 * 删除最后一个key-value
		 * */
        ht.remove(new D(1232));
		/*
		 * 遍历所有的key-value
		 * */
        for (Object key : ht.keySet()) {
            System.out.print(key + "-->");
            System.out.print(ht.get(key));
            System.out.println();
        }
    }
}
