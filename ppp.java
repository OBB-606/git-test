import java.lang.management.*;
import java.lang.*;
public class ppp
{
    public static void main(String[] args) {
        String Processor_IDENTIFIER = System.getenv("PROCESSOR_IDENTIFIER");
        String Processor_Architecture = System.getenv("PROCESSOR_ARCHITECTURE");
        String Processor_Number = System.getenv("NUMBER_OF_PROCESSORS");
        String LocalAppData = System.getenv("LOCALAPPDATA");
        String Java_Home = System.getenv("JAVA_HOME");
        String Username  = System.getenv("USERNAME");
        System.out.println(Processor_IDENTIFIER);
        System.out.println(Processor_Architecture);
        System.out.println(Processor_Number);
        System.out.println(LocalAppData);
        System.out.println(Java_Home);
        System.out.println(Username);
        //LOCALAPPDATA JAVA_HOME USERNAME Path


    }
}
