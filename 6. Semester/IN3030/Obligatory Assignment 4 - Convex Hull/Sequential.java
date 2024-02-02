public class Sequential {
    int [] x;
    int [] y;
    int n;
    IntList points;

    public Sequential(ConvexHull c) {
        this.x = c.x;
        this.y = c.y;
        this.n = c.n;
        this.points = c.points;
    }
    public IntList findConvexHull () {
        IntList CoHull = new IntList();
        int MAX_X = 0;
        int MIN_X = 0;
        for (int i = 0; i < n; i++) {
            if (x[i] >= x[MAX_X]) {
                MAX_X = i;
            } else if (x[i] <= x[MIN_X]) {
                MIN_X = i;
            }
        }
        CoHull.add(MAX_X);
        sekRek(MIN_X, MAX_X, points, CoHull);
        CoHull.add(MIN_X);
        sekRek(MAX_X, MIN_X, points, CoHull);

        return CoHull;
    }

    public void sekRek(int p1, int p2, IntList points, IntList CoHull) {
        int maxDist = 0;
        int maxPoint = -1;

        IntList left = new IntList();
        IntList colinear = new IntList();

        for (int i = 0; i < points.size(); i++) {
            int p = points.get(i);
            int d = getDist(p, p1, p2);

            if (d > 0) {
                left.add(p);
                if (d > maxDist) {
                    maxDist = d;
                    maxPoint = p;
                }
            } else if (d == 0){
                if (p != p1 && p != p2) {
                    colinear.add(p);
                }
            }
        }
        if (maxPoint >= 0) {
            sekRek(maxPoint, p2, left, CoHull);
            CoHull.add(maxPoint);
            sekRek(p1, maxPoint, left, CoHull);
        } else if (colinear.size() > 1){
            findColinear(p2, colinear, CoHull);
        } else if (colinear.size() == 1){
            CoHull.add(colinear.get(0));
        }
    }

    public int getDist(int p, int p1, int p2) {
        return (y[p2] - y[p1]) * x[p] - (x[p2] - x[p1]) * y[p] + (x[p2] * y[p1] - x[p1] * y[p2]);
    }

    public void findColinear(int p1, IntList colinear, IntList CoHull) {
        int p3 = colinear.get(0);
        int dist = getDistColinear(p1, p3);

        IntList closer = new IntList();
        IntList further = new IntList();

        for (int i = 1; i < colinear.size(); i++) {
            int p = colinear.get(i);
            int d = getDistColinear(p1, p);

            if (d > dist) {
                further.add(p);
            } else {
                closer.add(p);
            }
        }
        if (closer.size() != 0) {
            findColinear(p1, closer, CoHull);
        }
        CoHull.add(p3);
        if (further.size() != 0) {
            findColinear(p1, further, CoHull);
        }
    }
    public int getDistColinear(int p1, int p2) {
        return (x[p2] - x[p1]) * (x[p2] - x[p1]) + (y[p2] - y[p1]) * (y[p2] - y[p1]);
    }
}
