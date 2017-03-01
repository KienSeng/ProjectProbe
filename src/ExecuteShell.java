import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by kienseng.koh on 2/28/2017.
 */
public class ExecuteShell {

    public String execute(String[] command){
        Process exe;
        StringBuilder output = new StringBuilder();

        try{
            exe = Runtime.getRuntime().exec(command);
            exe.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(exe.getInputStream()));

            String line = "";

            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return output.toString();
    }
}
