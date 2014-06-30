
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.io.*;
import java.util.Iterator;
import java.lang.Integer;



// define shell of a Graph class

abstract class Graph$$Base {
   LinkedList vertices;
   Graph$$Base(){
     vertices = new LinkedList();
   }

   public VertexIter getVertices( ) {
      return new VertexIter(((Graph) this));
   }

   public void sortVertices(Comparator c) {
      Collections.sort(vertices, c);
   }

   // methods whose bodies will be overridden later
   EdgeIfc addEdge( Vertex v1, Vertex v2 ) { return null; }
   Vertex findsVertex( String name ) { return null; }
   void display() { }
   void addVertex( Vertex v ) { }
}



// adds utilities for reading files
// not really clear why these methods belong to Graph
// probably because this methods are easier to reuse
// if attached to Graph

abstract class Graph$$Benchmark extends  Graph$$Base 
{
    public Reader inFile; // File handler for reading
    public static int ch; // Character to read/write

    // timings
    static long last = 0, current = 0, accum = 0;

    public void runBenchmark( String FileName ) throws IOException
    {
        try
        {
            inFile = new FileReader( FileName );
        }
        catch ( IOException e )
        {
            System.out.println( "Your file " + FileName + " cannot be read" );
        }
    }

    public void stopBenchmark( ) throws IOException
    {
        inFile.close( );
    }

    public int readNumber( ) throws IOException
    {
        int index = 0;
        char[ ] word = new char[ 80 ];
        int ch = 0;

        ch = inFile.read( );
        while( ch==32 )
        {
            ch = inFile.read( ); // skips extra whitespaces
        }

        while( ch != -1 && ch != 32 && ch != 10 ) // while it is not EOF, WS, NL
        {
            word[ index++ ] = ( char )ch;
            ch = inFile.read( );
        }
        word[ index ] = 0;

        String theString = new String( word );

        theString = new String( theString.substring( 0,index ) ).trim( );
        return Integer.parseInt( theString,10 );
    }

    public static void startProfile( )
    {
        accum = 0;
        current = System.currentTimeMillis( );
        last = current;
    }

    public static void stopProfile( )
    {
        current = System.currentTimeMillis( );
        accum = accum + ( current - last );
    }

    public static void resumeProfile( )
    {
        current = System.currentTimeMillis( );
        last = current;
    }

    public static void endProfile( )
     {
        current = System.currentTimeMillis( );
        accum = accum + ( current-last );
        System.out.println( "Time elapsed: " + accum + " milliseconds" );
    }
      // inherited constructors


   Graph$$Benchmark (  ) { super(); }
}

//created on: Sat Dec 04 12:51:45 CST 2004

 abstract class Graph$$Prog extends  Graph$$Benchmark  {
    // method is extended later
    public void run( Vertex v ) { }
      // inherited constructors


   Graph$$Prog (  ) { super(); }
}

//created on: Sat Dec 04 15:32:48 CST 2004

 abstract class Graph$$DirectedCommon extends  Graph$$Prog  {
    public static final boolean isDirected = true;

    public void addVertex( Vertex v ) {
        vertices.add( v );
    }

    // Adds an edge without weights if Weighted layer is not present
    public void addAnEdge( Vertex start,  Vertex end, int weight ){
        addEdge( start,end );
    }
      // inherited constructors


   Graph$$DirectedCommon (  ) { super(); }
}




// ************************************************************

abstract class Graph$$DirectedGR extends  Graph$$DirectedCommon  {
    // Adds and edge by setting end as adjacent to start vertices
    public EdgeIfc addEdge( Vertex start,  Vertex end ) {
        start.addAdjacent( end );
        return( EdgeIfc ) start;
    }

    // Finds a vertex given its name in the vertices list
    public  Vertex findsVertex( String theName )
      {
        int i=0;
        Vertex theVertex;

        // if we are dealing with the root
        if ( theName==null )
            return null;

        for( i=0; i<vertices.size(); i++ )
            {
            theVertex = ( Vertex )vertices.get( i );
            if ( theName.equals( theVertex.name ) )
                return theVertex;
        }
        return null;
    }

    public void display() {
        int s = vertices.size();
        int i;

        System.out.println( "******************************************" );
        System.out.println( "Vertices " );
        for ( i=0; i<s; i++ )
            ( ( Vertex ) vertices.get( i ) ).display();
        System.out.println( "******************************************" );

    }
      // inherited constructors


   Graph$$DirectedGR (  ) { super(); }
}



// ***********************************************************************

abstract class Graph$$SearchCommon extends  Graph$$DirectedGR 
{
    public void GraphSearch( WorkSpace w )
    {
        // Step 1: initialize visited member of all nodes
        VertexIter vxiter = getVertices( );
        if ( vxiter.hasNext( ) == false )
        {
            return;
        }

        // Showing the initialization process
        while(vxiter.hasNext( ) )
        {
            Vertex v = vxiter.next( );
            v.init_vertex( w );
        }

        // Step 2: traverse neighbors of each node
        for (vxiter = getVertices( ); vxiter.hasNext( ); )
        {
            Vertex v = vxiter.next( );
            if ( !v.visited )
            {
                w.nextRegionAction( v );
                v.nodeSearch( w );
            }
        } //end for
    }
      // inherited constructors


   Graph$$SearchCommon (  ) { super(); }
}



// *************************************************************************

abstract class Graph$$Cycle extends  Graph$$SearchCommon  {
    public boolean CycleCheck() {
        CycleWorkSpace c = new CycleWorkSpace( isDirected );
        GraphSearch( c );
        return c.AnyCycles;
    }
      // inherited constructors


   Graph$$Cycle (  ) { super(); }
}

//created on: Sat Dec 04 12:34:33 CST 2004

 public class Graph extends  Graph$$Cycle  {
   // Executes Cycle Checking
    public void run( Vertex s )
     {
        System.out.println( " Cycle? " + CycleCheck() );
        super.run( s );
    }
      // inherited constructors


   Graph (  ) { super(); }
}