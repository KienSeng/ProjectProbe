/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        LinuxMonitoring linux = new LinuxMonitoring();
        System.out.println(linux.cpuJiffiesToPercentage());
    }
}
