import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class LinuxMonitoring {

    HashMap<String, Long> systemInfo = new HashMap<>();
    HashMap<String, HashMap> cpuUsage = new HashMap<>();
    HashMap<String, Long> memoryUsage = new HashMap<>();
    HashMap<String, Long> diskUsage = new HashMap<>();


    public void getCpuUsage() {

    }

    public void getMemoryInfo() {

    }

    public void getDiskInfo() {

    }

    public HashMap<String, HashMap> cpuJiffiesToPercentage() {
        String[] cpu = executeShellCommand(new String[]{"/bin/sh", "-c", "cat /proc/stat | grep cpu"}).replace("  ", " ").split("\n");
        HashMap<String, HashMap<String, String>> allCpu = new HashMap<>();
        ArrayList<Double> arrCpuUsageConverted = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");

        //Loop for multicore processor
        for(int i = 0; i < cpu.length; i++){
            String[] arrCpuUsage = cpu[i].split(" ");

            //Convert String to Long
            for (int j = 1; j < arrCpuUsage.length; j++) {
                arrCpuUsageConverted.add(Double.valueOf(arrCpuUsage[j]));
            }

            //Calculate the total usage of CPU, sum of all value
            double total = arrCpuUsageConverted.get(1) + arrCpuUsageConverted.get(2) + arrCpuUsageConverted.get(3) + arrCpuUsageConverted.get(4);
            System.out.println((arrCpuUsageConverted.get(0) + arrCpuUsageConverted.get(1))/total);
            //Calculate user, system, idle and iowait time
            HashMap<String, String> singleCpuInfo = new HashMap<>();
            singleCpuInfo.put("user", df.format(((arrCpuUsageConverted.get(0) + arrCpuUsageConverted.get(1)) / total) * 100));
            singleCpuInfo.put("system", df.format((arrCpuUsageConverted.get(2) / total) * 100));
            singleCpuInfo.put("idle", df.format((arrCpuUsageConverted.get(3) / total) * 100));
            singleCpuInfo.put("ioWait", df.format((arrCpuUsageConverted.get(4) / total) * 100));

            if(arrCpuUsage[0].equalsIgnoreCase("cpu")){
                allCpu.put("cpu_average", singleCpuInfo);
            } else {
                allCpu.put("cpu_1" + i, singleCpuInfo);
            }

        }

        cpuUsage.put("cpu_usage", allCpu);

        return cpuUsage;
    }

    private String executeShellCommand(String[] command){
        return new ExecuteShell().execute(command);

//        return "cpu  78495975 50509 67803531 12894867161 3440740 7941 426613 23175401 0\n" +
//                "cpu0 30567370 13287 19619938 3202071966 3070613 7884 280871 10874625 0\n" +
//                "cpu1 16491515 12149 17720657 3223287753 127483 34 50167 4481796 0\n" +
//                "cpu2 16099386 12796 15669906 3234254374 121504 13 48314 3994643 0\n" +
//                "cpu3 15337701 12275 14793028 3235253066 121139 9 47260 3824336 0";
    }

}
