
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }


    //first we will have graphs which have nodes and size, add node for adding nodes and also a  min-heap priority queue
    public static class Graph {

        //creating vertices
        public class Vertex {
            int dist;
            int Number;
            Adjacent adj;
            int Indegree;
            Status sts;


            public Vertex(int No) {
                this.Number = No;
                this.dist = Integer.MAX_VALUE;
                sts = Status.NEW;

            }


            public int comparison(Vertex v) {
                if (v.dist == this.dist) return 0;
                if (v.dist < this.dist) return 1;
                return -1;
            }

        }

        public enum Status {

            NEW, OLD, INQUEUE
        }


        public class Adjacent {
            int index;
            Adjacent next;
            int weight;

            Adjacent(int i, int w, Adjacent n) {
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

        //Creating Vertices

        public void addVx(int name) {
            vertices[idx++] = new Vertex(name);

        }

        public void addEdge(int src, int dst, int W) {
            int srcIndx = src;
            int dstIndx = dst;
            vertices[srcIndx].adj = new Adjacent(dstIndx, W, vertices[srcIndx].adj);
            vertices[dst].Indegree++;
        }


        public void shortestpath(int src){
            dikjstraAlgorithm(vertices[src]);
            for (int i = 0; i<size; i++){
                System.out.println("the shortest path from source"+src+"is");
            }

        }

        // Creating Heap Data structure
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
                        heap.heapifyUP(vertices[temp.index]);
                    }
                    temp = temp.next;

                }

            }
        }



    }



}
