import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class LinuxMonitoring {

    HashMap<String, Object> systemInfo = new LinkedHashMap<>();
    HashMap<String, Object> cpuUsage = new LinkedHashMap<>();
    HashMap<String, Object> memoryUsage = new LinkedHashMap<>();
    HashMap<String, Object> diskUsage = new LinkedHashMap<>();


    public void getCpuUsage() {
        String[] cpu = new ExecuteShell().execute(new String[]{"/bin/sh", "-c", "cat /proc/stat | grep cpu"}).split("\n");

    }

    public void getMemoryInfo() {
        String[] consoleOutput = new ExecuteShell().execute(new String[]{"/bin/sh", "-c", "free -k"}).split("\\s{2,99}");
//        for(int i = 0; i < consoleOutput.length; i++){
//            System.out.println("************" + consoleOutput[i] + " #" + i);
//        }

        memoryUsage.put("total", kbToMbWithDecimal(Integer.parseInt(consoleOutput[7])));
        memoryUsage.put("used", kbToMbWithDecimal(Integer.parseInt(consoleOutput[13])));
        memoryUsage.put("free", kbToMbWithDecimal(Integer.parseInt(consoleOutput[14].split("\n")[0])));
        memoryUsage.put("buffers", kbToMbWithDecimal(Integer.parseInt(consoleOutput[11])));
        memoryUsage.put("cached", kbToMbWithDecimal(Integer.parseInt(consoleOutput[12].split("\n")[0])));

//        System.out.println(memoryUsage);
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

    private String kbToMbWithDecimal(int digit){
        return String.format("%,d", digit / 1024) + " MB";
    }
}
