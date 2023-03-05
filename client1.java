import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class Result{

    int responsecode;
    String responsebody;
    public Result(int responsecode, String responsebody) {
        this.responsecode = responsecode;
        this.responsebody = responsebody;
    }

}




public class client1 {
	
	

	public static void main(String []args) throws Exception {

        

        

        Result r3 =client1.ClientTestPost();
        int k=0;
        if (r3.responsecode/100 == 4 | r3.responsecode/100 == 5)   ////Exception handling
        {
            for(int i=0;i<5;i++){
                Result r2 = ClientTestPost();
                k=k+1;
                if( r2.responsecode/100==4){
                    break;
                }
            }

           }

            else {
                
                System.out.println("Server ok");}

        if (k==5)
        {System.out.println("Server down");}
        else { System.out.println("Server ok");}

       


        }


    

	static Result ClientTestPost() throws IOException, InterruptedException
    {

	    Integer minskierID=1;
	    Integer maxskierID=100000;   // Dummy data generation

	    Random random = new Random();
	    Integer skierID=random.nextInt(maxskierID-minskierID+1)+minskierID;

	    Integer minresortID=1;
	    Integer maxresortID=10;

	    
	    Integer resortID=random.nextInt(maxresortID-minresortID+1)+minresortID;
	    
	    Integer minliftID=1;
	    Integer maxliftID=40;

	    
	    Integer liftID=random.nextInt(maxliftID-minliftID+1)+minliftID;


	            
	    Integer mintime=1;
	    Integer maxtime=360;

	    
	    Integer time=random.nextInt(maxtime-mintime+1)+mintime;


	    String jsonPayload = String.format("{\"time\": %d, \"liftID\": %d}", time,liftID);

	    HttpClient httpClient = HttpClient.newHttpClient();


	    String seasonID = "2022";
	    String dayID = "1";

	    URI uri = URI.create(String.format("http://168.138.69.171:8093/skiers/%d/seasons/%s/days/%s/skiers/%d", resortID,seasonID,dayID,skierID));

	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(uri)
	            .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
	            .header("Content-Type", "application/json")
	            .build();

	    HttpResponse<String> response;
        
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
	        String responseBody = response.body();
            //System.out.println("Status code: " + statusCode + "Response body: " + responseBody);
            Result r1 = new Result(statusCode, responseBody);
            return r1;

        
        
            
            
       
        
        	    
    }
        
    }
	    





