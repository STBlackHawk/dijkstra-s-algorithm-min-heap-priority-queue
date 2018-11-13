
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    //creating vertices
    public class Vertex{
    int dist;
    int Number;
    Adjacent adj;
    int Indegree;
    Status sts;


    public Vertex(int No){
        this.Number = No;
        this.dist = Integer.MAX_VALUE;
        sts = Status.NEW;

    }


    public int comparison(Vertex v){
        if (v.dist == this.dist) return 0;
        if (v.dist < this.dist) return 1;
        return -1;
    }

    }

    public enum Status{

        NEW, OLD,INQUEUE
    }



    public class Adjacent {
        int index;
        Adjacent next;
        int weight;

        Adjacent(int i, int w, Adjacent n;) {
            this.index = i;
            this.weight = w;
            this.next = n;

        }

    }



    public static class minHeap{
        private Vertex[] heap;
        private int size;
        private int idx;


        public minHeap(int s){
            this.size = s;
            heap = new Vertex[s];
        }

    }












    //first we will ave graphs which have nodes and size, add node for adding nodes and also a  min-heap priority queue
    public static class Graph {

        //adjacency list of the graph
        Vertex[] vertices;

        //size of the graph
        int size;

        //index of each vertex
        int idx;

        //graph constructor
        public Graph(int size){
            this.size = size;
            vertices = new Vertex[size];
        }

        //Creating Vertices








        public void addVx(int name){
            vertices[idx++] = new Vertex(name);

        }


        public void addEdge(int src, int dst, int W){
            int srcIndx = src;
            int dstIndx = dst;
            vertices[srcIndx].adj = new Neighb
        }
    }


}
