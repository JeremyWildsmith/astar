package ca.bcit.a00699903;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node mParent;
    private Vector mLocation;
    
    public Node(Node parent, Vector location)
    {
        mParent = parent;
        mLocation = location;
    }
    
    public Node(Vector location)
    {
        this(null, location);
    }
    
    public void setParent(Node parent)
    {
        mParent = parent;
    }
    
    public Node getParent()
    {
        return mParent;
    }
    
    public int getScore(Vector start)
    {
        return getF(start) + getG();
    }
    
    public int getF(Vector start)
    {
        Vector difference = mLocation.difference(start);

        int deltaX = Math.abs(difference.getX());
        int deltaY = Math.abs(difference.getY());
        
        return deltaX + deltaY;
    }

    public int getG()
    {
        return 1 + (mParent == null ? 0 : mParent.getG());
    }
    
    public Vector getLocation()
    {
        return mLocation;
    }
    
    public Direction[] getDirections()
    {
        List<Direction> directions = new ArrayList<Direction>();
        List<Node> nodes = new ArrayList<Node>();
        
        Node n = this;
        
        while(n != null)
        {
            nodes.add(n);
            n = n.mParent;
        }
        
        for(int i = nodes.size() - 1; i > 0; i--)
        {
            Node previous = nodes.get(i - 1);
            Vector difference = previous.getLocation().difference(nodes.get(i).getLocation());
            
            if(difference.getX() < 0)
                directions.add(Direction.Left);
            else if(difference.getX() > 0)
                directions.add(Direction.Right);
            else if(difference.getY() < 0)
                directions.add(Direction.Up);
            else if(difference.getY() > 0)
                directions.add(Direction.Down);
            else
                throw new RuntimeException("Unrecognized movement.");
        }

        return directions.toArray(new Direction[0]);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mLocation == null) ? 0 : mLocation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (mLocation == null) {
            if (other.mLocation != null)
                return false;
        } else if (!mLocation.equals(other.mLocation))
            return false;
        return true;
    }
    
    
}
