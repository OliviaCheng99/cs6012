package lab03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsTimingExperiment {

    private static final int ITER_COUNT = 1000;

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("data.csv", false)) { // open up a file writer so we can write
            // to file.
            fw.write("Timing of contains() on SortedSet,size (N),time (ns)");
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                int size = (int) Math.pow(2, exp); // or ..

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;
// SET UP!
                SortedSet<Integer> set = new TreeSet<>();
                for (int i = 0; i < size; i++) {
                    set.add(i);
                }
                for (int iter = 0; iter < ITER_COUNT; iter++) {

                    int findElement = random.nextInt(size); // This gets me a random int between 0 and size;

                    // TIME IT!
                    long start = System.nanoTime();
                    set.contains(findElement);
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }
                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.print(String.format("trial, %d, %f\n", size, averageTime)); // print to console
                fw.write(String.format("trial, %d, %f\n", size, averageTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
