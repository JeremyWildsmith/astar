package ca.bcit.a00699903;

public class Vector {
    private int mX;
    private int mY;
    
    public Vector(int x, int y)
    {
        mX = x;
        mY = y;
    }

    public int getY() {
        return mY;
    }

    public int getX() {
        return mX;
    }
    
    public Vector move(Direction d)
    {
        Vector v = new Vector(mX, mY);
        
        switch(d)
        {
        case Up:
            v.mY++;
            break;
        case Down:
            v.mY--;
            break;
        case Right:
            v.mX++;
            break;
        case Left:
            v.mX--;
            break;
        }
        
        return v;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mX;
        result = prime * result + mY;
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
        Vector other = (Vector) obj;
        if (mX != other.mX)
            return false;
        if (mY != other.mY)
            return false;
        return true;
    }

    public Vector difference(Vector v)
    {
        return new Vector(mX - v.mX, mY - v.mY);
    }
    
    public float length()
    {
        return (float)Math.sqrt(mX * mX + mY * mY);
    }
    
    @Override
    public String toString()
    {
        return "X: " + mX + ", Y: " + mY;
    }
}
