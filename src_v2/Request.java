
public class Request implements Comparable<Request>{
    
    int idVideo;
    int endpoint;
    int numberOfRequests;
    
    public Request(int idVideo, int endpoint, int numberOfRequests) {
        this.idVideo = idVideo;
        this.endpoint = endpoint;
        this.numberOfRequests = numberOfRequests;
    }

    @Override
    public String toString() {
        return "[idVideo=" + idVideo + ", endpoint=" + endpoint
                + ", numberOfRequests=" + numberOfRequests + "]";
    }

    @Override
    public int compareTo(Request o) {
        // descending order
        return o.numberOfRequests - this.numberOfRequests;
    }
    
}
