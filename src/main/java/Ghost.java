interface Chase{
    void chase();
}

interface Scatter{
    void scatter();
}

interface Frightened{
    void frightened();
}

public abstract class Ghost extends Element{
    Chase chaseBehaviour;
    Scatter scatterBehaviour;
    Frightened frightenedBehaviour;

    Ghost (int x, int y){
        super(x, y);
    }
}
