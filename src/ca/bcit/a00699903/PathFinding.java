package ca.bcit.a00699903;

import java.util.ArrayList;
import java.util.List;

public class PathFinding {
    
    private static final int MAP_BOUNDS = 5;
    
    public static void main(String[] args)
    {
        boolean map[][] = new boolean[][] {{true,  true,  true, true,  true},
                                           {true,  true,  true, true,  true},
                                           {false, false, true, false, true},
                                           {true,  true,  true, true,  true},
                                           {true,  true,  true, true,  true}};
        Vector start = new Vector(0, 0);
        Vector end = new Vector(3, 3);
        
        System.out.println("Map below. s=start location; d=destination");
        
        for(int y = 0; y < map.length; y++)
        {
            for(int x = 0; x < map[y].length; x++)
            {
                if(start.equals(new Vector(x, y)))
                    System.out.print("[s]");
                else if(end.equals(new Vector(x, y)))
                    System.out.print("[d]");
                else
                    System.out.print(map[y][x] ? "[ ]" : "[X]");
            }
            
            System.out.println();
        }
        
        System.out.println("\nF Value score map");
        
        for(int y = 0; y < 5; y++)
        {
            for(int x = 0; x < 5; x++)
            {
                int score = new Node(new Vector(x,y)).getF(end);
                System.out.print("[" + score + "]");
            }
            System.out.println();
        }
        
        System.out.println("\nDirections to reach destination: ");
        Node path = pathFind(start, end, map);

        Direction[] directions = path.getDirections();
        
        System.out.println(directions.length + " movements.");
        for(Direction d : directions)
        {
            System.out.println(d);
        }
        
    }
    
    private static Node pathFind(Vector start, Vector end, boolean[][] walkableMap)
    {
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Node> closed = new ArrayList<>();
        
        Node startNode = new Node(start);
        
        open.add(startNode);
        
        for(int i = 0; i < 100; i++)
        {
            Node smallestScore = smallestScore(open, end);

            if(smallestScore.getLocation().equals(end))
                return smallestScore;

            for(Direction d : Direction.values())
            {
                Node movement = new Node(smallestScore, smallestScore.getLocation().move(d));
                if(isWalkable(movement, walkableMap) && !open.contains(movement) && !closed.contains(movement))
                    open.add(movement);
            }
            
            closed.add(smallestScore);
            open.remove(smallestScore);
        }
        
        return null;
    }
    
    private static Node smallestScore(List<Node> adjacent, Vector start)
    {
        int smallestScore = Integer.MAX_VALUE;
        Node smallest = null;
        for(Node n : adjacent)
        {
            int fValue = n.getScore(start);
            if(fValue < smallestScore)
            {
                smallest = n;
                smallestScore = fValue;
            }
        }
        
        return smallest;
    }
    
    private static boolean isWalkable(Node node, boolean[][] walkableMap)
    {
        Vector location = node.getLocation();
        
        if(location.getX() < 0 || location.getX() >= MAP_BOUNDS ||
           location.getY() < 0 || location.getY() >= MAP_BOUNDS)
            return false;
        
        return walkableMap[location.getY()][location.getX()];
    }
}
