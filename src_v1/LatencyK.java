
public class LatencyK implements Comparable<LatencyK>{
    
    int id;
    int latency;
    
    public LatencyK(int id, int latency) {
        this.id = id;
        this.latency = latency;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", latency=" + latency + "]";
    }

    @Override
    public int compareTo(LatencyK o) {
     // ascending order
        return (this.latency - o.latency);
    }
    
}