import java.util.ArrayList;

public class Cache {
    
    int id;
    ArrayList<Integer> videosCached;
    
    public Cache(int id, int firstVideo) {
        this.id = id;
        this.videosCached = new ArrayList<Integer>();
        addCachedVideo(firstVideo);
    }
    
    public void addCachedVideo(Integer newVid) {
        videosCached.add(newVid);
    }

    @Override
    public boolean equals(Object obj) {
        return (this.id == ((LatencyK)obj).id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        for (int vid : videosCached) {
            sb.append(" ");
            sb.append(vid);
        }
        
        return sb.toString();
    }
    

}
