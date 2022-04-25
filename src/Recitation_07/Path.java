package Recitation_07;

public class Path {
    private static class Pos {
        public int x, y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    private Maze maze;
    private ListQueue<Pos> queue;
    private final static int dx[] = new int[]{-1, 1,  0, 0};
    private final static int dy[] = new int[]{ 0, 0, -1, 1};

    public Path(Maze map) {
        this.maze = map;
        queue = new ListQueue<Pos>();
    }

    public void findDistance(int fromX, int fromY, int toX, int toY) {
        maze.initDistance();

        if(maze.isEmpty(fromX, fromY)) {
            //TODO: set the distance from (fromX, fromY) to itself to 0 and
            //      enqueue the position
            maze.setDistance(fromX, fromY, 0);
            queue.enqueue(new Pos(fromX, fromY));
        }

        while(!queue.isEmpty()) {
            //TODO: remove a marked position from the queue and
            //      break if (toX, toY) is reached
            Pos p = queue.dequeue();
            if(p.x == toX && p.y == toY)
                break;

            //Check if distance of the four neighbors of p can be updated
            for(int i = 0; i < dx.length; i++) {
                int x = p.x + dx[i];    //neighbor's x
                int y = p.y + dy[i];    //neighbor's y

                //TODO: continue if 0 <= x < maze.col and 0 <= y < maze.row are not true
                if( x < 0 || x >= maze.getCol() ||
                        y < 0 || y >= maze.getRow() )
                    continue;

                //TODO: continue if (x, y) is not empty
                if(!maze.isEmpty(x, y))
                    continue;

                //TODO: continue if the distance to (x, y) is shorter than
                //      the distance to (p.x, p.y) + 1
                if(maze.getDistance(x, y) <= maze.getDistance(p.x, p.y) + 1)
                    continue;

                //TODO: update the distance to (x, y) to the distance to (p.x, p.y) + 1
                maze.setDistance(x, y, maze.getDistance(p.x, p.y) + 1);

                //TODO: enqueue (x, y) so that its neighbors can be updated
                queue.enqueue(new Pos(x, y));
            }
        }
    }

    public void findPath(int fromX, int fromY, int toX, int toY) {
        int cx = toX, cy = toY;
        int d = maze.getDistance(cx, cy);

        maze.setPath(cx, cy, true);
        while(cx != fromX || cy != fromY) {
            for(int i = 0; i < dx.length; i++) {
                int x = cx + dx[i];    //neighbor's x
                int y = cy + dy[i];    //neighbor's y

                //TODO: continue if 0 <= x < maze.col and 0 <= y < maze.row are not true
                if( x < 0 || x >= maze.getCol() ||
                        y < 0 || y >= maze.getRow() )
                    continue;

                //TODO: continue if (x, y) is not empty
                if(!maze.isEmpty(x, y))
                    continue;

                //TODO: continue unless the distance to (x, y) is shorter than d
                if(d <= maze.getDistance(x, y))
                    continue;

                d = maze.getDistance(x, y);
                cx = x; cy = y;
                maze.setPath(cx, cy, true);
                break;
            }
        }
    }
}