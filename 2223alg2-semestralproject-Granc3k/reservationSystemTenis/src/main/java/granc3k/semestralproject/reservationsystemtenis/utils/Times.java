package granc3k.semestralproject.reservationsystemtenis.utils;

import java.util.Arrays;

public class Times {
    private final String[] times;
    /**
     * constructor for object
     */
    public Times() {
        this.times = new String[24];
        Arrays.fill(this.times,"-1");
        //if "-1" -- time is not reserved
        //otherwise there is customers name
    }
    /**
     * checks if the position is reserved
     * @param i - namuber of hour
     */
    public boolean isReserved(int i){
        return !"-1".equals(times[i]);
    }
    /**
     * sets customer name to array in range of inputted times
     * @param start - start hour
     * @param end  - end hour
     * @param customer - name of the customer
     */
    public void setTimes(int start, int end,String customer){
        for(int i=start;i<end;i++){
            this.times[i]=customer;
        }
    }
    /**
     * sets "-1" to array instead of customers name from the inputted time
     * @param start - start hour
     * @param customer - name of the customer
     */
    public void remTimes(int start, String customer){
        for(int i=start;i< times.length;i++){
            if(customer.equals(this.times[i])){
                this.times[i]="-1";
            }else{
                break;
            }
        }
    }
}
