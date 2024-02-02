public class ConvexHull {
    int [] x;
    int [] y;
    int n;
    int MAX_X;
    int MAX_Y;
    IntList points;

    public ConvexHull(int n, int seed) {
        this.n = n;
        this.x = new int[n];
        this.y = new int[n];
        NPunkter17 p = new NPunkter17(n, seed);
        p.fyllArrayer(x, y);
        this.points = p.lagIntList();

        int tmpx = 0;
        int tmpy = 0;
        for(int i = 0; i < n; i++){
          if(x[i] > tmpx) tmpx = x[i];
          if(y[i] > tmpy) tmpy = y[i];
        }
        this.MAX_X = tmpx;
        this.MAX_Y = tmpy;

    }

    public int getDist(int p, int p1, int p2) {
        int a = y[p1] - y[p2];
        int b = x[p2] - x[p1];
        int c = (y[p2] * x[p1]) - (y[p1] * x[p2]);
        return a * x[p] + b * y[p] + c;
    }
}
