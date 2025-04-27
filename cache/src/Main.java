import cache.Cache;
import cache.exceptions.InvalidKeyException;
import cache.exceptions.InvalidOperationException;
import cache.exceptions.InvalidStorageCapacity;
import cache.factories.CacheFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidStorageCapacity, InvalidOperationException, InvalidKeyException {
        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().getCache();
        System.out.println(cache.get(0));
        cache.put(0, 1);
        System.out.println(cache.get(0));
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println("0:" + cache.get(0));
        cache.put(2, 3);
        System.out.println("0:" + cache.get(0));
        System.out.println("1:" + cache.get(1));
        System.out.println("2:" + cache.get(2));

        cache.remove(2);
        System.out.println("0:" + cache.get(0));
        System.out.println("1:" + cache.get(1));
        System.out.println("2:" + cache.get(2));
    }
}