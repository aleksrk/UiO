public class Parallel {
    int [] x;
    int [] y;
    IntList CoHull;
    int threads;
    int n;
    Sequential s;
    IntList points;

    public IntList findConvexHull (ConvexHull c, int threads) {
        IntList CoHull = new IntList();
        this.x = c.x;
        this.y = c.y;
        this.n = c.n;
        this.points = c.points;
        this.threads = threads;

        IntList[] subRegion = new IntList[threads];
        IntList totalRegion = new IntList();
        Thread[] workers = new Thread[threads];

        int workPerThread = n / threads;
        int start = 0;
        int stop = 0;
        for (int i = 0; i < threads; i++) {
            subRegion[i] = new IntList();
            start = stop;
            stop = (i == threads - 1) ? n : start + workPerThread;

            workers[i] = new Thread(new Worker(start, stop, subRegion[i]));
            workers[i].start();
        }

        for (int i = 0; i < threads; i++) {
            try {
                workers[i].join();
                totalRegion.append(subRegion[i]);
            } catch (InterruptedException e) {
                System.out.println("Thread join error");
            }
        }

        int MIN_X = 0;
        int MAX_X = 0;
        int TEMP;


        for (int i = 0; i < totalRegion.len; i++) {
            TEMP = totalRegion.get(i);
            if (x[TEMP] >= x[MAX_X]) {
                MAX_X = TEMP;
            } else if (x[TEMP] <= x[MIN_X]) {
                MIN_X = TEMP;
            }
        }

        CoHull.add(MAX_X);
        sekRek(MIN_X, MAX_X, 0, totalRegion.len, totalRegion, CoHull);
        CoHull.add(MIN_X);
        sekRek(MAX_X, MIN_X, 0, totalRegion.len, totalRegion, CoHull);

        return CoHull;

    }

    public class Worker implements Runnable {
        int start;
        int stop;
        IntList subRegion;

        public Worker(int start, int stop, IntList subRegion) {
            this.start = start;
            this.stop = stop;
            this.subRegion = subRegion;
        }

        public void run() {
            int MAX_X = start;
            int MIN_X = start;
            for (int i = start + 1; i < stop; i++) {
                if (x[i] > x[MAX_X]) {
                    MAX_X = i;
                } else if (x[i] < x[MIN_X]) {
                    MIN_X = i;
                }
            }

            subRegion.add(MAX_X);
            sekRek(MIN_X, MAX_X, start, stop, points, subRegion);
            subRegion.add(MIN_X);
            sekRek(MAX_X, MIN_X, start, stop, points, subRegion);
        }
    }

    public void sekRek(int p1, int p2, int start, int stop, IntList points, IntList CoHull) {
        int maxDist = 0;
        int maxPoint = -1;

        IntList left = new IntList();
        IntList colinear = new IntList();

        //System.out.println(maxPoint + " " + p2 + " " + start + " " + stop);
        for (int i = start; i < stop; i++) {
            //System.out.println("Stop = " + stop);
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
            //System.out.println(maxPoint + " " + p2 + " " + start + " " + stop);
            sekRek(maxPoint, p2, 0, left.size(), left, CoHull);
            CoHull.add(maxPoint);
            sekRek(p1, maxPoint, 0, left.size(), left, CoHull);
        } else if (colinear.size() > 1){
            findColinear(p2, colinear, CoHull);
        } else if (colinear.size() == 1){
            CoHull.add(colinear.get(0));
        }
    }
    public int getDist(int p, int p1, int p2) {
        //System.out.println("p: " + p + ", p1: "+p1+", p2: "+p2);
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
