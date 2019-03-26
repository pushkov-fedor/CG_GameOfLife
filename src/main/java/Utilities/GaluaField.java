package Utilities;

public class GaluaField {

    public static int getNumber(int dimmension, int number){
        //dimmension - number of elements in galua field
        //last number in filed = dimmenison - 1;
        int lastNumber = dimmension - 1;
        if(number < 0){
            while(lastNumber - number >= dimmension){
                number += dimmension;
            }
        } else if(number > lastNumber){
            while(number >= lastNumber){
                number -= dimmension;
            }
        }
        return number;
    }
}
