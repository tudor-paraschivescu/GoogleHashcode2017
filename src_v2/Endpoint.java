import java.util.ArrayList;

public class Endpoint {
    
    int latencyD;
    int k;
    ArrayList<LatencyK> latencyK;
    
    @Override
    public String toString() {
        return "[latencyD=" + latencyD + ", k=" + k + ", latencyK="
                + latencyK.toString() + "]";
    }

    public Endpoint(int latencyD, int k, ArrayList<LatencyK> latencyK) {
        this.latencyD = latencyD;
        this.k = k;
        this.latencyK = latencyK;
    }


}
