package Maps;

public class Main {

    public static void main(String[] args) throws Exception {
        LinkedHashMapCustom<String, Integer> map = new LinkedHashMapCustom<>();
        map.put("nisab", 599);
        map.put("koni", 545);
        map.remove("koni");
          map.remove("nisab");

//        map.clear();
//        System.out.println(map.getOrDefault("hello", 46));
//        System.out.println(map.containsKey("nisab"));
//        System.out.println(map);
//        HashMapCustom<Integer, String> map = new HashMapCustom<>();
//        map.put(781, "hkjfd");
//        map.put(71, "fgfg");
//        map.put(99, "Nisab");
//        map.put(79, "jdgdsd");
//        map.put(99, "Nisab mohd");
//        map.put(24454, "fidfhkjdgkdgdg");
//        map.remove(71);
//        map.remove(781);
//        map.remove(99);   map.remove(781);
//        System.out.println(map.get(79));
//System.out.println(map.containsKey(45));
//        System.out.println(map.containsValue("Nisab mohd"));
//        System.out.println(map.getOrDefault(99, "default"));
//map.clear();
        System.out.println(map);

    }
}
