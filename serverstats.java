import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

public class serverstats {
    public static void main(String[] args) {
        String csvFile = "data.csv";
        String line = "";
        String csvSeparator = ",";
        float sum=0;
        boolean firstro = true;
        int[] str = new int[10000];
        int i =0;
        String[] time = new String[10000];
       

        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (firstro) {
                    // skip the first row
                    firstro = false;
                    continue;
                }
                String[] data = line.split(csvSeparator);
                
                sum = sum + Integer.parseInt(data[0]);
                str[i] = Integer.parseInt(data[0]);
                
                time[i]=data[8];
                //System.out.println(time[i]);


                

                i++;

                // System.out.println(data[8]);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.sort(str);

        String timestampStr = time[time.length-1]; // The timestamp in string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Creating a formatter for the timestamp string
        LocalDateTime localDateTime = LocalDateTime.parse(timestampStr, formatter); // Parsing the timestamp string to a LocalDateTime object
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC); // Converting the LocalDateTime object to a ZonedDateTime object with the UTC time zone
        long timestamp = zonedDateTime.toInstant().toEpochMilli(); // Getting the timestamp in milliseconds since the epoch
        String timestampStr1 = time[1]; // The timestamp in string format
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Creating a formatter for the timestamp string
        LocalDateTime localDateTime1 = LocalDateTime.parse(timestampStr1, formatter1); // Parsing the timestamp string to a LocalDateTime object
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(localDateTime1, ZoneOffset.UTC); // Converting the LocalDateTime object to a ZonedDateTime object with the UTC time zone
        long timestamp1 = zonedDateTime1.toInstant().toEpochMilli(); // Getting the timestamp in milliseconds since the epoch

        // // System.out.println(timestamp);
        long x = timestamp - timestamp1;
        System.out.println("Walltime :" +x+"ms" );
        float y = x/1000;
        
       


        System.out.println("Mean Latency :" + sum/(time.length-1)+"ms");
        System.out.println("Median Latency:" + str[(time.length-1)/2]+"ms" );
        System.out.println("Throughput :"+ (time.length-1)/y +" per sec");
        System.out.println("p99 :"+ str[(time.length-1)-(10/100*(time.length-1))]+"ms");
    }
}
