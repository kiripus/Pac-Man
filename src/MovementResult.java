public class MovementResult {
    public int[] coordinates;
    public int counter;
    public GameGUI.direction directionObject;
    public boolean directionChanged;

    public MovementResult(int[] coordinates, int counter, GameGUI.direction directionObject, boolean directionChanged) {
        this.coordinates = coordinates;
        this.counter = counter;
        this.directionObject = directionObject;
        this.directionChanged = directionChanged;
    }
}



