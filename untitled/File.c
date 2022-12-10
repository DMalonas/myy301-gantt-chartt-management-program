




/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include<stdio.h>
#include<stdlib.h>

#define MAX 100

#define initial 1
#define waiting 2
#define visited 3

int n;    /*Number of vertices in the graph*/
int M;   //Number of edges
int adj[MAX][MAX]; /*Adjacency Matrix*/
int state[MAX]; /*can be initial, waiting or visited*/

void create_graph();
void BF_Traversal();
void BFS(int v);

int queue[MAX], front = -1,rear = -1;
void insert_queue(int vertex);
int delete_queue();
int isEmpty_queue();



int main()
{
        create_graph();
        BF_Traversal();

        return 0;

}




void BF_Traversal()
{
        int v;
        int connected = 1;

        for(v=0; v<n; v++)
                state[v] = initial;

        BFS(0); /*start BFS from vertex 0*/

        for(v=0; v<n; v++)
        {
                if(state[v] == initial)
                {
                        connected = 0;
                        break;
                }
        }

        if(connected)
        printf("\nGraph is connected\n");
        else
        printf("\nGraph is not connected\n");
}/*End of BF_Traversal()*/

void BFS(int v)
{
        int i;

        insert_queue(v);
        state[v] = waiting;

        while( !isEmpty_queue() )
        {
                v = delete_queue( );
                state[v] = visited;
                printf("%d ",v);

                for(i=0; i<=n-1; i++)
                {
                        /* Check for adjacent unvisited vertices */
                        if( adj[v][i] == 1 && state[i] == initial)
                        {
                                insert_queue(i);
                                state[i] = waiting;
                        }
                }
        }
        printf("\n");
}/*End of BFS()*/

void insert_queue(int vertex)
{
        if(rear == MAX-1)
                printf("\nQueue Overflow\n");
        else
        {
                if(front == -1)  /*If queue is initially empty */
                        front = 0;
                rear = rear+1;
                queue[rear] = vertex ;
        }
}/*End of insert_queue()*/

int isEmpty_queue()
{
        if(front == -1 || front > rear )
                return 1;
        else
                return 0;
}/*End of isEmpty_queue()*/

int delete_queue()
{
        int del_item;
        if (front == -1 || front > rear)
        {
                printf("\nQueue Underflow\n");
                exit(1);
        }

        del_item = queue[front];
        front = front+1;
        return del_item;

}/*End of delete_queue() */

void create_graph()
{
        int i,max_edges,origin,destin, edge_counter = 0;

        printf("\nEnter number of vertices : ");
        scanf("%d",&n);
        max_edges = n*(n-1)/2;

        for(i=1; i<=max_edges; i++)
        {
                printf("\nEnter edge %d( -1 -1 to quit ) : ",i);
                scanf("%d %d",&origin,&destin);

                if((origin == -1) && (destin == -1))
                        break;

                if( origin >= n || destin >= n || origin<0 || destin<0)
                {
                        printf("\nInvalid edge!\n");
                        i--;
                }
                else
                {
                        edge_counter++;
                        adj[origin][destin] = 1;
                        adj[destin][origin] = 1;
                }
        }/*End of for*/
        M = edge_counter;
}/*End of create_graph()*/






// Function to create Adjacency Matrix
void createAdjMatrix(int Adj[][n + 1],
                     int arr[][2])
{

    // Initialise all value to this
    // Adjacency list to zero
    for (int i = 0; i < n + 1; i++) {

        for (int j = 0; j < n + 1; j++) {
            Adj[i][j] = 0;
        }
    }

    // Traverse the array of Edges
    for (int i = 0; i < M; i++) {

        // Find X and Y of Edges
        int x = arr[i][0];
        int y = arr[i][1];

        // Update value to 1
        Adj[x][y] = 1;
        Adj[y][x] = 1;
    }
}

// Function to print the created
// Adjacency Matrix
void printAdjMatrix(int Adj[][n + 1])
{

    // Traverse the Adj[][]
    for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < n + 1; j++) {

            // Print the value at Adj[i][j]
            printf("%d ", Adj[i][j]);
        }
        printf("\n");
    }
}