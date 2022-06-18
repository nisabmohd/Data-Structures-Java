package Maps;

public class Main {

    public static void main(String[] args) throws Exception {
//        TreeMapCustom<String, Integer> map = new TreeMapCustom<>();
//        map.put("t", 70);
//        map.put("b", 150);
//        map.put("z", 70);
//        map.put("d", 150);
//        map.display();
        LinkedHashMapCustom<Integer, Integer> map = new LinkedHashMapCustom<>();
        map.put(58, 78);
        map.put(8, 8);
        map.put(99, 48);
        map.put(58, 58);
        map.put(18, 178);
        map.remove(18);
        map.remove(58);
        map.remove(99);
//        map.remove(8);
//        System.out.println(map.containsKey(68));
//        System.out.println(map.containsValue(58));
//        map.clear();
//        System.out.println(map.getOrDefault(99, 46));
        System.out.println(map);

    }
}
