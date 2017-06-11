import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        
        try {
            String inputFile = "kittens.in";
            BufferedReader buf = new BufferedReader(new FileReader(inputFile));
            String line = buf.readLine();
            String[] line1 = line.split(" ");
            int v = Integer.parseInt(line1[0]);
            int e = Integer.parseInt(line1[1]);
            int r = Integer.parseInt(line1[2]);
            int c = Integer.parseInt(line1[3]);
            int x = Integer.parseInt(line1[4]);
                        
            //System.out.println(v + " " + e + " " + r + " " + c + " " + x);
            
            ArrayList<Integer> sizes = new ArrayList<Integer>();
            line = buf.readLine();
            String[] line2 = line.split(" ");
            for (int i = 0; i < v; i++) {
                sizes.add(Integer.parseInt(line2[i]));
            }
            
            ArrayList<Endpoint> ends = new ArrayList<Endpoint>();
            for (int i = 0; i < e; i++) {
                line = buf.readLine();
                String[] line3 = line.split(" ");
                
                int latencyD = Integer.parseInt(line3[0]);
                int k = Integer.parseInt(line3[1]);
                ArrayList<LatencyK> latsK = new ArrayList<LatencyK>(); 
                
                for (int j = 0; j < k; j++) {
                    line = buf.readLine();
                    String[] line4 = line.split(" ");
                    Integer idc = Integer.parseInt(line4[0]);
                    Integer lc = Integer.parseInt(line4[1]);
                    latsK.add(new LatencyK(idc, lc));
                }
                
                ends.add(new Endpoint(latencyD, k, latsK));
            }
            
            ArrayList<Request> requests = new ArrayList<Request>(); 
            for (int i = 0; i < r; i++) {
                line = buf.readLine();
                String[] line5 = line.split(" ");
                
                int rv = Integer.parseInt(line5[0]);
                int re = Integer.parseInt(line5[1]);
                int rn = Integer.parseInt(line5[2]);
                
                requests.add(new Request(rv, re, rn));
            }
            
            // Sort the video requests in descending order
            Collections.sort(requests);
            
            // Sort the latencies of the cached servers
            // of each endpoint in ascending order
            for (Endpoint endpoint : ends) {
                Collections.sort(endpoint.latencyK);
            }
            
            // Array of the space left in each cache
            int[] caches = new int[c];
            for (int i = 0; i < c; i++) {
                caches[i] = x;
            }
            
            // The cache descriptions that must be followed
            ArrayList<Cache> cacheDescriptions = new ArrayList<Cache>();
            Cache auxCache;
            
            for (int i = 0; i < r; i++) {
                Request req = requests.remove(0);
                Endpoint currentEndpoint = ends.get(req.endpoint);
                ArrayList<LatencyK> currentServers = currentEndpoint.latencyK;
                int numberOfServers = currentServers.size();
                
                int currentServer = 0;
                while (currentServer < numberOfServers) {
                    LatencyK lat = currentServers.get(currentServer);
                    if (caches[lat.id] - sizes.get(req.idVideo) >= 0) {
                        //System.out.println("- id_cache:" + lat.id + " video:" + req.idVideo);
                        if (Algorithms.contains(cacheDescriptions, lat.id)) {
                            auxCache = Algorithms.CacheWithId
                                       (cacheDescriptions, lat.id);
                            if (!auxCache.videosCached.contains(req.idVideo)) {
                                auxCache.addCachedVideo(req.idVideo);
                            }
                        }
                        else {
                            cacheDescriptions.add(
                                       new Cache(lat.id, req.idVideo));
                        }
                        
                        // The video is cached and occupies space on the server
                        caches[lat.id] -= sizes.get(req.idVideo);
                        break;
                    }
                    currentServer++;
                }
            }
            /*
            String[] tokens = inputFile.split(".");
            String outputFile = tokens[0];
            StringBuilder builder = new StringBuilder(outputFile);
            builder.append(".out");
            outputFile = builder.toString();
            */
            
            //PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
            /*
            writer.println(cacheDescriptions.size());
            for (Cache server : cacheDescriptions) {
                writer.println(server);
            }
            */
            
            System.out.println(cacheDescriptions.size());
            for (Cache server : cacheDescriptions) {
                System.out.println(server);
            }
            
            buf.close();
            //writer.close();
        } catch (Exception e) {
            //System.out.println(e.printStackTrace());
            e.printStackTrace();
        }
        
    }

}
