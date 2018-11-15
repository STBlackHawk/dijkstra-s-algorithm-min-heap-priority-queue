
import java.util.Scanner;
import java.util.Stack;

public class DijkstraSAlgorithm {

    public static void main(String[] args) {



        //Creating scanner for reading from standard input
        Scanner in = new Scanner(System.in);

        //reading input and assigning them to the number of vertecies and edges
        int s = in.nextInt();
        int e = in.nextInt();

        Graph graph = new Graph(s);
        for (int i = 0; i < s; i++) {

        }

        while (in.hasNextLine()){
           graph.addEdge(in.nextInt(),
                   in.nextInt(),in.nextFloat());

        }



       graph.shortestpath(0);








    }




    //first we will have graphs which have nodes and size, add node for adding nodes and also a  min-heap priority queue
    public static class Graph {

        //creating an object tye for vertices
        public class Vertex {
            float dist;
            int Number;
            Adjacent adj;
            int Indegree;
            Status sts;


            public Vertex(int No) {
                this.Number = No;
                this.dist = Integer.MAX_VALUE;
                sts = Status.NEW;

            }

            //Comparison function to compare vertecies min distace from source
            public int comparison(Vertex v) {
                if (v.dist == this.dist) return 0;
                if (v.dist < this.dist) return 1;
                return -1;
            }

        }

        //Status for each edge wether or no they have been visited or they are in our min heap queue
        public enum Status {

            NEW, OLD, INQUEUE
        }

        //adjacent object for adjacency list which is a linked list and points to the next neighbour
        public class Adjacent {
            int index;
            Adjacent next;
            float weight;

            Adjacent(int i, float w, Adjacent n) {
                this.index = i;
                this.weight = w;
                this.next = n;

            }

        }


        //adjacency list of the graph
        Vertex[] vertices;

        //size of the graph
        int size;

        //index of each vertex
        int idx;


        //graph constructor
        public Graph(int size) {
            this.size = size;
            vertices = new Vertex[size];
        }


        //an array using quick find technique for finding the rout of the shortest path from source to each node
        int[] path = new int[size];



        //Creating Vertices

        public void addVx(int name) {
            vertices[idx++] = new Vertex(name);

        }
        //addEdge function for adding edges in the graph
        public void addEdge(int src, int dst, float W) {
            int srcIndx = src;
            int dstIndx = dst;
            vertices[srcIndx].adj = new Adjacent(dstIndx, W, vertices[srcIndx].adj);
            vertices[dst].Indegree++;
        }

        //finding the shortest path in the graph
        public void shortestpath(int src){
            for (int j = 0; j < size; j++ ){
                path[j] = j;
            }
            Stack<Integer> S = new Stack<>();

            dikjstraAlgorithm(vertices[src]);
            for (int i = 0; i<size; i++){
                System.out.println("the shortest path of the vertex"+vertices[i].Number+ "from source:"+src+"is"
                + vertices[i].dist);
                System.out.println("the rout for the shortest path is");
                while (path[i] != i);
                {
                    S.push(path[i]);
                }

                S.push(src);
                while (!S.empty()){
                    System.out.print(S.pop());
                }


            }

        }

        // Creating Heap Data structure and its function using heapify functions
        public static class minHeap {
            private Vertex[] heap;
            private int size;
            private int idx;


            public minHeap(int s) {
                this.size = s;
                heap = new Vertex[s];
            }


            public void heapifyUP(Vertex u) {
                for (int i = 0; i < size; i++) {
                    if (u == heap[i]) {
                        heapifyUp(i);
                        break;
                    }
                }
            }

            public void swap(int idx1, int idx2) {
                Vertex current = heap[idx1];
                heap[idx1] = heap[idx2];
                heap[idx1] = current;
            }

            public void heapifyUp(int index) {
                int curidx = index;
                Vertex currentVertex = heap[curidx];
                int parentidx = (curidx - 1) / 2;
                Vertex parentVertex = heap[parentidx];
                while (currentVertex.comparison(parentVertex) == -1) {
                    swap(curidx, parentidx);
                    curidx = parentidx;
                    if (curidx == 0) {
                        break;
                    }
                    currentVertex = heap[curidx];
                    parentidx = (curidx - 1) / 2;
                    parentVertex = heap[parentidx];

                }
            }


            public void add(Vertex u) {
                heap[idx++] = u;
                heapifyUp(idx - 1);
            }


            public void heapifyDown(int index) {
                if (idx == 1) {
                    return;
                }
                int curidx = index;
                Vertex currentVertex = heap[curidx];
                int leftChildidx = (2 * curidx) + 1;
                int rightChildidx = (2 * curidx) + 2;
                int childidx;
                Vertex childVertex;
                if (heap[leftChildidx] == null) {
                    return;
                }
                if (heap[rightChildidx] == null) {
                    childidx = leftChildidx;
                } else if (heap[leftChildidx].comparison(heap[rightChildidx]) == -1) {
                    childidx = leftChildidx;
                } else {
                    childidx = rightChildidx;
                }
                childVertex = heap[childidx];

                while (childVertex.comparison(currentVertex) == 1) {
                    swap(curidx, childidx);
                    curidx = childidx;
                    currentVertex = heap[curidx];
                    leftChildidx = (2 * curidx) + 1;
                    rightChildidx = (2 * curidx) + 2;

                    if (heap[leftChildidx] == null) {
                        return;
                    }
                    if (heap[rightChildidx] == null) {
                        childidx = leftChildidx;
                    } else if (heap[leftChildidx].comparison(heap[rightChildidx]) == -1) {
                        childidx = leftChildidx;
                    } else {
                        childidx = rightChildidx;
                    }
                }

            }


            public Vertex remove() {
                Vertex v = heap[0];
                swap(0, idx - 1);
                heap[idx - 1] = null;
                idx--;
                heapifyDown(0);
                return v;

            }

            public boolean isEmpty() {
                return idx == 0;
            }

        }

        //implementation of the dikjstraAlgorithm
        public void dikjstraAlgorithm(Vertex src){
            minHeap heap = new minHeap(size);
            heap.add(src);
            src.sts = Status.INQUEUE;
            src.dist = 0;

            while (!heap.isEmpty()){
                Vertex u= heap.remove();
                u.sts = Status.OLD;
                Adjacent temp = u.adj;
                while(temp != null){
                    if(vertices[temp.index].sts == Status.NEW){
                        heap.add(vertices[temp.index]);
                        vertices[temp.index].sts = Status.INQUEUE;
                    }
                    if (vertices[temp.index].dist > u.dist +temp.weight){
                        vertices[temp.index].dist = u.dist +temp.weight;
                        path[vertices[temp.index].Number] = u.Number;

                        heap.heapifyUP(vertices[temp.index]);

                    }
                    temp = temp.next;

                }

            }
        }

    }

}
