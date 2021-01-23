import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



    public class Solution {
        public static void main(String[] args) {
            Map<String, String> map = new HashMap<>();
            map.put("Sim", "Sim");
            map.put("Tom", "Tom");
            map.put("Arbus", "Arbus");
            map.put("Baby", "Baby");
            map.put("Cat", "Cat");
            map.put("Dog", "Dog");
            map.put("Eat", "Eat");
            map.put("Food", "Food");
            map.put("Gevey", "Gevey");
            map.put("Hugs", "Hugs");

            printKeys(map);
        }

        public static void printKeys(Map<String, String> map) {
            map.forEach((k, v) -> System.out.println(k));
        }
        }

