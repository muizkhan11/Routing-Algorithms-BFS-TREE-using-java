import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.*;

public class BFSSimulator {
    private Graph graph;
    private Map<String, Boolean> visited;

    public BFSSimulator() {
        graph = new SingleGraph("BFS Tree");
        visited = new HashMap<>();
        System.setProperty("org.graphstream.ui", "swing");
    }

    public void addNode(String nodeId) {
        if (graph.getNode(nodeId) == null) {
            Node node = graph.addNode(nodeId);
            node.setAttribute("ui.label", nodeId);
            visited.put(nodeId, false);
        }
    }

    public void addEdge(String edgeId, String from, String to) {
        if (graph.getEdge(edgeId) == null) {
            graph.addEdge(edgeId, from, to, false); // Undirected graph
        }
    }

    public void bfs(String sourceId) {
        if (graph.getNode(sourceId) == null) {
            System.out.println("Source node doesn't exist.");
            return;
        }

        // Reset visited
        for (String key : visited.keySet()) visited.put(key, false);

        Queue<String> queue = new LinkedList<>();
        queue.add(sourceId);
        visited.put(sourceId, true);

        graph.getNode(sourceId).setAttribute("ui.class", "visited");

        while (!queue.isEmpty()) {
            String current = queue.poll();
            Node currentNode = graph.getNode(current);

            for (Edge edge : currentNode.edges().toList()) {
                String neighbor = edge.getOpposite(currentNode).getId();
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    graph.getNode(neighbor).setAttribute("ui.class", "visited");
                    queue.add(neighbor);
                }
            }

            sleep(2000); // Delay for visualization
        }
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    public void displayGraph() {
        graph.setAttribute("ui.stylesheet", styleSheet);
        graph.display();

        // Wait for viewer to initialize properly before traversal
        try {
            Thread.sleep(5000);  // Give time for window to fully render
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


}

    private static String styleSheet =
            "node { fill-color: grey; size: 25px; text-size: 16px; }" +
                    "node.visited { fill-color: green; }" +
                    "edge { arrow-shape: none; }";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BFSSimulator simulator = new BFSSimulator();

        System.out.println("Enter number of nodes:");
        int n = sc.nextInt();
        sc.nextLine();  // Flush the newline

        System.out.println("Enter node names:");
        for (int i = 0; i < n; i++) {
            String node = sc.nextLine();
            simulator.addNode(node);
        }

        System.out.println("Enter number of edges:");
        int e = sc.nextInt();
        sc.nextLine();  // Flush newline

        System.out.println("Enter edges (format: node1 node2):");
        for (int i = 0; i < e; i++) {
            String[] parts = sc.nextLine().split(" ");
            simulator.addEdge(parts[0] + "_" + parts[1], parts[0], parts[1]);
        }

        System.out.println("Enter source node for BFS:");
        String source = sc.nextLine();

        simulator.displayGraph();
        simulator.bfs(source);
    }
}
