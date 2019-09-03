package Design;

public class DesignHitCounter {

    private int[] times;	//times[i] means the timestamp on index i
    private int[] hits;		//hits[i] means how many hits on index i
    
    /** Initialize your data structure here. */
    public DesignHitCounter() {
        times = new int[300];
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
    	System.out.println("timestamp: "+timestamp);
        
    	int index = timestamp % 300;
        System.out.println("index: "+index+" times[index]: "+times[index]+" hits[index]: "+hits[index]);
        
        if(times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 1;
        } 
        else {
            hits[index]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
    	System.out.println("timestamp: "+timestamp);
        int total = 0;
        
        for(int i = 0; i < 300; i++) {
        	System.out.println("i: "+i+" times[i]: "+times[i]+" timestamp - times[i]: "+(timestamp - times[i]));
        	if(timestamp - times[i] < 300) {
                total = total + hits[i];
            }
        }
        System.out.println("total: "+total);
        return total;
    }

	
	public static void main(String[] args) {
		DesignHitCounter counter = new DesignHitCounter();

		// hit at timestamp 1.
		counter.hit(1);

		// hit at timestamp 2.
		counter.hit(2);

		// hit at timestamp 3.
		counter.hit(3);

		// get hits at timestamp 4, should return 3.
		counter.getHits(4);

		// hit at timestamp 300.
		counter.hit(300);

		// get hits at timestamp 300, should return 4.
		counter.getHits(300);

		// get hits at timestamp 301, should return 3.
		counter.getHits(301); 
	}

}
