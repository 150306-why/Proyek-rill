package del.ifs24024;

import java.io.*;
import java.util.*;

public class Program {
    static class Patient {
        String id, name, status;
        List<String> neighbors = new ArrayList<>(); // Directed for path
        List<String> allNeighbors = new ArrayList<>(); // Undirected for cluster

        Patient(String id, String name) {
            this.id = id;
            this.name = name;
            this.status = "AMAN";
        }
    }

    private static Map<String, Patient> patientMap = new LinkedHashMap<>();
    private static String rootId = null;
    private static String lastInfectedId = null;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                if (!sc.hasNext()) break;
                String cmd = sc.next();

                switch (cmd) {
                    case "ROOT":
                        String rId = sc.next();
                        String rName = sc.next();
                        handleRoot(rId, rName);
                        break;

                    case "TRACE":
                        String from = sc.next();
                        String to = sc.next();
                        String toName = sc.next();
                        handleTrace(from, to, toName);
                        break;

                    case "LINK":
                        String id1 = sc.next();
                        String id2 = sc.next();
                        handleLink(id1, id2);
                        break;

                    case "INFECT":
                        String infId = sc.next();
                        handleInfect(infId);
                        break;

                    case "HEAL":
                        String healId = sc.next();
                        handleHeal(healId);
                        break;

                    case "SHORTEST_PATH":
                        handleShortestPath();
                        break;

                    case "GET_CLUSTER_ACTIVE":
                        String clusterId = sc.next();
                        handleGetClusterActive(clusterId);
                        break;
                }
            }
            System.out.println(); // Pemisah antar blok input jika ada
        }
    }

    private static void handleRoot(String id, String name) {
        if (rootId != null) {
            System.out.println("[ERROR] Root sudah terdaftar di dalam sistem!");
            return;
        }
        Patient p = new Patient(id, name);
        p.status = "TERINFEKSI";
        patientMap.put(id, p);
        rootId = id;
        lastInfectedId = id;
        System.out.println("[SUKSES] Root " + id + " (" + name + ") terdaftar sebagai Patient Zero.");
    }

    private static void handleTrace(String from, String to, String name) {
        if (!patientMap.containsKey(from)) {
            System.out.println("[ERROR] Pasien dengan ID " + from + " tidak ditemukan.");
            return;
        }
        if (patientMap.containsKey(to)) {
            System.out.println("[ERROR] Pasien dengan ID " + to + " sudah ada di dalam sistem.");
            return;
        }
        Patient p = new Patient(to, name);
        patientMap.put(to, p);
        patientMap.get(from).neighbors.add(to);
        // Untuk cluster (undirected)
        patientMap.get(from).allNeighbors.add(to);
        p.allNeighbors.add(from);
        
        System.out.println("[SUKSES] " + to + " (" + name + ") terdaftar dan terhubung dengan " + from + ".");
    }

    private static void handleLink(String id1, String id2) {
        if (patientMap.containsKey(id1) && patientMap.containsKey(id2)) {
            patientMap.get(id1).neighbors.add(id2);
            patientMap.get(id1).allNeighbors.add(id2);
            patientMap.get(id2).allNeighbors.add(id1);
            System.out.println("[SUKSES] Kontak ditambahkan antara " + id1 + " dan " + id2 + ".");
        }
    }

    private static void handleInfect(String id) {
        Patient p = patientMap.get(id);
        if (p == null) {
            System.out.println("[ERROR] Pasien dengan ID " + id + " tidak ditemukan.");
            return;
        }
        p.status = "TERINFEKSI";
        lastInfectedId = id;
        System.out.println("[SUKSES] Status " + id + " berubah menjadi TERINFEKSI.");
    }

    private static void handleHeal(String id) {
        Patient p = patientMap.get(id);
        if (p == null) {
            System.out.println("[ERROR] Pasien dengan ID " + id + " tidak ditemukan.");
            return;
        }
        p.status = "SEMBUH";
        System.out.println("[SUKSES] Status " + id + " berubah menjadi SEMBUH.");
    }

    private static void handleGetClusterActive(String id) {
        if (!patientMap.containsKey(id)) return;

        // BFS/DFS untuk mencari semua node dalam satu komponen (undirected)
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(id);
        visited.add(id);
        
        int infectedCount = 0;
        while (!q.isEmpty()) {
            String curr = q.poll();
            if (patientMap.get(curr).status.equals("TERINFEKSI")) {
                infectedCount++;
            }
            for (String neighbor : patientMap.get(curr).allNeighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.add(neighbor);
                }
            }
        }
        System.out.println("[TOTAL_AKTIF] Klaster " + id + " memiliki " + infectedCount + " orang berstatus TERINFEKSI.");
    }

    private static void handleShortestPath() {
        if (rootId == null || lastInfectedId == null) return;
        if (rootId.equals(lastInfectedId)) {
            System.out.println("[JALUR] " + rootId);
            return;
        }

        Queue<String> q = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        q.add(rootId);
        visited.add(rootId);
        boolean found = false;

        while (!q.isEmpty()) {
            String curr = q.poll();
            if (curr.equals(lastInfectedId)) {
                found = true;
                break;
            }
            for (String n : patientMap.get(curr).neighbors) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    parent.put(n, curr);
                    q.add(n);
                }
            }
        }

        if (found) {
            LinkedList<String> path = new LinkedList<>();
            for (String at = lastInfectedId; at != null; at = parent.get(at)) {
                path.addFirst(at);
            }
            System.out.println("[JALUR] " + String.join(" -> ", path));
        }
    }
}