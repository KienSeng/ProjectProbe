/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class LinuxMonitoring {

    public void getCpuUsage(){
        String[] cpu = new ExecuteShell().execute(new String[]{"/bin/sh","-c","cat /proc/stat | grep cpu"}).split("\n");

    }

    public void getMemoryInfo(){

    }

    public void getDiskInfo(){

    }

    private void cpuJiffiesToPercentage(String cpuReading){
        String[] cpuUsage = cpuReading.split(" ");
        long total = Long.getLong(cpuUsage[1]) + Long.getLong(cpuUsage[2]) + Long.getLong(cpuUsage[3])
                + Long.getLong(cpuUsage[4]) + Long.getLong(cpuUsage[5]) + Long.getLong(cpuUsage[6])
                + Long.getLong(cpuUsage[7]);
    }
}
