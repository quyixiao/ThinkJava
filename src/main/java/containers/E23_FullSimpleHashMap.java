package containers;

import net.mindview.util.Countries;

import java.util.LinkedList;

import static net.mindview.util.Print.print;


/***
 * 495页
 * @param <K>
 * @param <V>
 *
 *
 *
 *     1
 */
class SimpleHashMap4<K, V> extends SimpleHashMap3<K, V> {

    public int size() {
        // Could rely on the inherited implementation, which
        // returns entrySet().size(), but this is faster:
        int sz = 0;
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
            if (bucket != null)
                sz += bucket.size();
        return sz;
    }

    public boolean isEmpty() {
        // Could just say return size() == 0;
        // but this is faster:
        for (LinkedList<MapEntry<K, V>> bucket : buckets)
            if (bucket != null)
                return false;
        return true;
    }

    public boolean containsKey(Object key) {
        // A slight modification of the previous method:
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mPair : bucket)
                if (mPair.getKey().equals(key))
                    return true;
        }
        return false;
    }
}

public class E23_FullSimpleHashMap {
    public static void main(String args[]) {
        SimpleHashMap4<String, String>
                m = new SimpleHashMap4<String, String>(),
                m2 = new SimpleHashMap4<String, String>();
        m.putAll(Countries.capitals(10));
        m2.putAll(Countries.capitals(10));
        print("m.size() = " + m.size());
        print("m.isEmpty() = " + m.isEmpty());
        print("m.equals(m2) = " + m.equals(m2));
        print("m.containsKey(\"BENIN\") = " +
                m.containsKey("BENIN"));
        print("m.containsKey(\"MARS\") = " +
                m.containsKey("MARS"));
        print("m.keySet() = " + m.keySet());
        print("m.values() = " + m.values());
    }
} 