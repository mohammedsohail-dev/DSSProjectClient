
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


 class threaddetails{

    public static Integer numberofthreads=32;
    public static Integer numberofpostsperthread=1000;
    public static Integer counter=0;
    public static Integer totalposts=10000;
    public static ArrayList<String[]> data= new ArrayList<>();
    public static ArrayList<post> list = new ArrayList<post>();
    public static ArrayList<Long> timelist= new ArrayList<Long>();
    public static Long Walltime;
    
    


}





class post extends Thread{
    @Override 
       public void run(){
    
       for(int j=1;j<=threaddetails.numberofpostsperthread;j++){
            try {
                TestPost.testLiftRidePost();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    
    }
       }
    
    
    






class TestPost {


   
/// do get and do post for random gen data
    synchronized public static void testLiftRidePost() throws Exception {

        
        // int x = threaddetails.numberofpostsperthread*threaddetails.numberofthreads -10;
        threaddetails.counter=1+threaddetails.counter;

        if (threaddetails.counter<=threaddetails.totalposts){    //totalposts condition


            long begin = System.currentTimeMillis(); 
            Date d = new Date(begin);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String strDate = formatter.format(d); 
            Result r=client1.ClientTestPost();


        

        long end = System.currentTimeMillis();
        
         long dt = end - begin;

        String[] row = {
            Long.toString(dt), Integer.toString(r.responsecode), r.responsebody,strDate
        };

        threaddetails.data.add(row);
        threaddetails.timelist.add(dt);


        
        
        // Write the data to a CSV file
        try {
            FileWriter writer = new FileWriter("data.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String[] rowData : threaddetails.data) {
                bufferedWriter.write(String.join(",", rowData));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Data saved to data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        

        }
        else{  // if the required posts finish, the remaining threads are joined with the main thread
            for(int i=0;i<threaddetails.list.size();i++){ 
        
   
            
                threaddetails.list.get(i).join();
           
           
           
           
           
                }

        }	
	
}


}



class client2{


    

    public static void main( String arg [] ) throws Exception{  
        
        
        long begint = System.currentTimeMillis();
        System.out.println(begint); 



        

        String[] heading = {
            "time taken","status code","responsebody"
        };
        
        threaddetails.data.add(heading);


    

     for(int i=1;i<=threaddetails.numberofthreads;i++){  // 32 threads are created
        
   
     post posttest = new post();
     posttest.start();
    

     threaddetails.list.add(posttest);

     }

     

     int flag=0;

     while(true){

    
        for(int i=0;i<threaddetails.list.size();i++)    //check if thread is alive
        {
            if(threaddetails.list.get(i).isAlive()){
                

                continue;
            }
            else{
                flag=1;  // if not alive, break
                

                    

            break;
            }
        }

        

        if(flag==1){

            

            break;
        }

        
     }

     long endt = System.currentTimeMillis(); 

            threaddetails.Walltime=endt-begint;

            System.out.println(threaddetails.Walltime);

     for(int i=1;i<=10;i++){  // creating extra threads if a thread is terminated (10 threads)
 
        post posttest = new post();
        posttest.start();
   
        threaddetails.list.add(posttest);
 
        }

        for(int i=0;i<threaddetails.list.size();i++){ //  join it to the main thread
            
            threaddetails.list.get(i).join();
       
       
            }


}



}

