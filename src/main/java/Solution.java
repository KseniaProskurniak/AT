import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



    public class Solution {
        public static void main(String[] args) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int[] array = new int[20];
            for (int i = 0; i < 20; i++) {
                array[i] = Integer.parseInt(reader.readLine());
            }

            sort(array);

            for (int x : array) {
                System.out.println(x);
            }
        }

        public static void sort(int[] array) {
            for (int i = 0; i < array.length; i++) {
                for (int j = array.length; j < i; j--) {
                    if (array[i] < array[j]) {
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
    }
