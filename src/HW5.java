import java.util.Arrays;

public class HW5 {

    static final int size = 10_000_000;
    static final int s2 = size/2;

    public static void main(String[] args) {

        //stage 1
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        for (int i = 0; i < size ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);



        //stage 2
        Arrays.fill(arr, 1);
        float[] arr1 = new float[s2];
        float[] arr2 = new float[s2];

        a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, s2);
        System.arraycopy(arr, s2, arr2, 0, s2);

        Thread t1 = new Thread(() ->{
            for (int i = 0; i < s2 ; i++) {
                arr1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        t1.start();

        Thread t2 = new Thread(() ->{
            for (int i = 0; i < s2; i++) {
                arr2 [i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, s2);
        System.arraycopy(arr2, 0, arr, s2, s2);
        System.out.println(System.currentTimeMillis() - a);

    }


}
