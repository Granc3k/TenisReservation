package granc3k.semestralproject.reservationsystemtenis;

import java.util.Arrays;

public class Times {
    private final String[] times;
    public Times() {
        this.times = new String[24];
        Arrays.fill(this.times,"-1");
        //if "-1" -- time is not reserved
        //otherwise there is customers name
    }
    public boolean isReserved(int i){
        return !"-1".equals(times[i]);
    }
    public void setTimes(int start, int end,String customer){
        for(int i=start;i<end;i++){
            this.times[i]=customer;
        }
    }
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
