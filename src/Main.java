/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        LinuxMonitoring p = new LinuxMonitoring();
//        String[] cpu = p.getCpuUsage().split("\n");
//        System.out.println(cpu[0]);
        p.getMemoryInfo();

    }
}
