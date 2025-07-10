
---

## 💻 How to Run

### Requirements:
- Java JDK 11 or higher
- IntelliJ IDEA (Community Edition)
- GraphStream libraries:
  - `graphstream-core-2.0.jar`
  - `gs-ui-javafx-2.0.jar`

### Setup Steps:
1. Open the project in IntelliJ IDEA
2. Go to `File > Project Structure > Modules > Dependencies`
3. Add both JAR files from the `Executable/` or `lib/` folder
4. Set the SDK to **Java 11 or higher**
5. Run the file `BFSSimulator.java`

---

## 🧠 Algorithm: BFS Tree

- Input: Nodes, Edges, Source Node
- Traverses the network in a **level-wise** manner using **Breadth-First Search**
- Highlights each visited node in **green**
- Handles disconnected graphs and dynamic user-defined topologies

---

## 🎨 Visualization Instructions

- The program uses **GraphStream with JavaFX viewer**
- When `displayGraph()` is called, a new window shows the graph
- Nodes start grey and turn green in BFS order
- A short delay (`Thread.sleep()`) makes the traversal easier to see

---

## 📸 Screenshots

_(Add these to your GitHub repo or project report)_
- Initial graph (all grey)
- Mid traversal (partially green)
- Full traversal (all reachable nodes green)

---

## 📖 How to Use (Sample Input)

```text
Enter number of nodes:
5
Enter node names:
A
B
C
D
E
Enter number of edges:
6
Enter edges (format: node1 node2):
A B
A C
B D
C D
C E
D E
Enter source node for BFS:
A
