package pbru.it.sukuma.pbrurun;

/**
 * Created by lap324-12 on 6/13/2016 AD.
 */
public class Mydata {

    //explicit
    private int[] avatarInts = new int[]{R.drawable.bird48, R.drawable.doremon48,
    R.drawable.kon48, R.drawable.nobita48, R.drawable.rat48};
    private double latADouble = 13.071490, lngADouble = 99.976746;


    public double getLatADouble() {
        return latADouble;
    }

    public double getLngADouble() {
        return lngADouble;
    }

    public int[] getAvatarInts() {


        return avatarInts;


    }
}   //main class
