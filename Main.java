import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[10000];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            int indx = 0;
            while (reader.ready()){
                array[indx] = Integer.parseInt(reader.readLine());
                indx++;
            }
            RedBlackTree redBlackTree = new RedBlackTree();
            long start = 0;
            long time = 0;
            long cntOperations = 0;
            try {
                for (int num : array) {
                    start = System.nanoTime();
                    redBlackTree.insert(num);
                    cntOperations += RedBlackTree.COUNT_OF_OPERATIONS;
                    RedBlackTree.COUNT_OF_OPERATIONS = 0;
                    time += System.nanoTime() - start;
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            System.out.println("Average time for insert elements: " + time/array.length);
            System.out.println("Average count of operations for insert elements: " + cntOperations/array.length);

            time = 0;
            cntOperations = 0;
            for (int i = 0; i < 100; i++){
                int num = array[(int)(Math.random()*array.length)];
                start = System.nanoTime();
                redBlackTree.contains(num);
                cntOperations += RedBlackTree.COUNT_OF_OPERATIONS;
                RedBlackTree.COUNT_OF_OPERATIONS = 0;
                time += System.nanoTime() - start;
            }
            System.out.println("Average time for find elements: " + time/100);
            System.out.println("Average count of operations for find elements: " + cntOperations/100);

            time = 0;
            cntOperations = 0;
            ArrayList<Integer> arrayList = new ArrayList<>();
            Collections.addAll(arrayList, array);
            for (int i = 0; i < 100; i++) {
                int index = (int)(Math.random()*arrayList.size());
                int num = arrayList.get(index);
                start = System.nanoTime();
                redBlackTree.delete(num);
                cntOperations += RedBlackTree.COUNT_OF_OPERATIONS;
                RedBlackTree.COUNT_OF_OPERATIONS = 0;
                time += System.nanoTime() - start;
            }
            System.out.println("Average time for delete elements: " + time/100);
            System.out.println("Average count of delete for delete elements: " + cntOperations/100);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


