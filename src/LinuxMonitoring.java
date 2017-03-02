import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class LinuxMonitoring {

    HashMap<String, Integer> systemInfo = new HashMap<>();
    HashMap<String, Integer> cpuUsage = new HashMap<>();
    HashMap<String, Integer> memoryUsage = new HashMap<>();
    HashMap<String, Integer> diskUsage = new HashMap<>();


    public void getCpuUsage() {
        String[] cpu = new ExecuteShell().execute(new String[]{"/bin/sh", "-c", "cat /proc/stat | grep cpu"}).split("\n");

    }

    public void getMemoryInfo() {

    }

    public void getDiskInfo() {

    }

    private void cpuJiffiesToPercentage(String cpuReading) {
        String[] arrCpuUsage = cpuReading.split(" ");
        ArrayList<Long> arrCpuUsageConverted = new ArrayList<>();

        //Convert String to Long
        for (int i = 1; i < arrCpuUsage.length; i++) {
            arrCpuUsageConverted.add(Long.getLong(arrCpuUsage[i]));
        }

        //Calculate the total usage of CPU, sum of all value
        long total = arrCpuUsageConverted.get(0) + arrCpuUsageConverted.get(2) + arrCpuUsageConverted.get(3);
    }
}
