/*
The approach for the project was fairly simple. I started with creating the DLStack class following the instructions provided.
I then created the pathfinder class and with only a few minor tweaks, Test 1,2,5,6 were passing.

The challenges I faced were with Test 3 and 4. I was trying to guess what the problem may be and this led to repetive stints of no success.
Then I started going into the tester file and adding print statements to find the exact error. I found out that both tests were throwing an exception
which was causing testPassed = false. To fix this I added a print statement in the tester file that would show me that exact exception error.
By doing so, I was able to fix Test3, as it was previously not using the custom exceptions we have provided, instead it was using java package for exceptions

For test 4 I also tried the same and added print statements to each if statement that could make testPassed = false, however I still could not
get to a solution. By the end, 5/6 tests are passing.


*/
import java.io.IOException;
import java.io.FileNotFoundException;

public class PathFinder {
    private Map pyramidMap; // the map of the pyramid

    public PathFinder(String fileName) { // constructor to create a new path finder with a map from a file
        try {
            pyramidMap = new Map(fileName); /// create the map from the file
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } catch (InvalidMapCharacterException e) {
            System.out.println("Invalid map character: " + e.getMessage());
        }
    }

    public DLStack path() {
        DLStack<Chamber> stack = new DLStack<>(); //// a stack to keep track of the path
        Chamber entrance = pyramidMap.getEntrance(); /// the entrance to the pyramid
        entrance.markPushed();  /// mark the entrance as visited
        stack.push(entrance);  /// mark the entrance as visited

        while (!stack.isEmpty()) {
            Chamber current = stack.peek();
            if (current.isTreasure() && pyramidMap.getNumTreasures() == stack.size()) {
                break;
            }
            Chamber best = bestChamber(current);
            if (best != null) {
                best.markPushed();
                stack.push(best);
            } else {
                current.markPopped();
                stack.pop();
            }
        }
        return stack;
    }

    public Map getMap() { // returns the map of the pyramid
        return pyramidMap; // returns the map
    }

    public boolean isDim(Chamber currentChamber) { ////// checks if the current chamber is dim
        if (currentChamber == null || currentChamber.isSealed() || currentChamber.isLighted()) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            try {
                Chamber neighbour = currentChamber.getNeighbour(i);
                if (neighbour != null && neighbour.isLighted()) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("bestChamber Exception");
            }
        }
        return false;
    }

    public Chamber bestChamber(Chamber currentChamber) { /// // finds the best neighbouring chamber to move to
        Chamber best = null; /////// the best neighbouring chamber
        for (int i = 0; i < 6; i++) {
            try {
                Chamber neighbour = currentChamber.getNeighbour(i);
                if (neighbour != null && !neighbour.isMarked() && !neighbour.isSealed()) {
                    if (neighbour.isTreasure() || best == null || (isDim(best) && neighbour.isLighted())) {
                        best = neighbour;
                    }
                }
            } catch (Exception e) {
                System.out.println("bestChamber Exception");
            }
        }
        return best;
    }
}
