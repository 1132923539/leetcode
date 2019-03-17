package test;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main1(String[] args) {
        Map a = new HashMap(1);
        Map b = new HashMap();
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(Math.round(-1.5));
    }

    public static void main(String args[]) {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
    }
}
