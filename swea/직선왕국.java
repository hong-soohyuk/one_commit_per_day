import java.util.PriorityQueue;

class   PIP
{
    int key;
    int[] values;
    PIP(int key, int[] array)
    {
        this.key = key;
        this.values = array;
    }
}

class UserSolution
{
    static final int MAX_CITY = 200;
    int cities;
    int managers;
    class CityTaxes
    {
        PriorityQueue<Integer[]>[] pq = new PriorityQueue[MAX_CITY];

        void    init()
        {
            for (int i = 1; i < cities; i++)
                pq[i] = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        }
        void    reserveAddTax(int cityId, int t, int mTax)
        {
            pq[cityId].add(new Integer[]{t, mTax});
        }

        int takeTax(int cityId, int t)
        {
            int taxSum = 0;
            while (!pq[cityId].isEmpty() && pq[cityId].peek()[0] <= t)
            {
                taxSum += pq[cityId].peek()[1];
                pq[cityId].poll();
            }
            return taxSum;
        }
    }

    class PredictTaxes
    {
        int[]       addedTaxes = new int[MAX_CITY << 1];
        int[]       removedTaxes = new int[MAX_CITY << 1];
        boolean[]   isDispatched = new boolean[MAX_CITY << 1];
        int[]       tree = new int[MAX_CITY << 2];
        int         nn;

        void    updateTree(int i)
        {
            int left = tree[i << 1];
            int right = tree[i << 1 | 1];
            int predlefttax = addedTaxes[left] - removedTaxes[left];
            int predrighttax = addedTaxes[right] - removedTaxes[right];
            tree[i] = (isDispatched[left] || !isDispatched[right] && predlefttax < predrighttax) ? right : left;
            if (i > 1) updateTree(i / 2);
        }

        void    init()
        {
            for (nn = 1; nn < cities; nn *= 2);
            for (int i =1; i < nn; i++)
            {
                addedTaxes[i] = removedTaxes[i] = tree[i] = 0;
                tree[nn + i - 1] = i;
                isDispatched[i] = false;
            }
        }

        int top()
        {
            if (isDispatched[tree[1]] || addedTaxes[tree[1]] - removedTaxes[tree[1]] <= 0) return 0;
            return tree[1];
        }

        void    pop(int cityID)
        {
            isDispatched[cityID] = true;
            updateTree((nn + cityID - 1) / 2);
        }

        void    push(int cityID, int mTax)
        {
           isDispatched[cityID] = false;
           removedTaxes[cityID] += mTax;
           updateTree((nn + cityID - 1) / 2);
        }

        void    updatePrediction(int cityID, int mTax)
        {
            addedTaxes[cityID] += mTax;
            updateTree((nn + cityID - 1) / 2);
        }
    }

    class OfficialQueue
    {
        int officialEmpty;
        int totalCapitalTax;
        PriorityQueue<PIP> pq = new PriorityQueue<>((o1, o2) -> (o1.key - o2.key));

        void    init()
        {
            officialEmpty = managers;
            totalCapitalTax = 0;
            while (!pq.isEmpty())
                pq.poll();
        }

        void    timeFlow(int tStamp, CityTaxes cityTaxes, PredictTaxes predictTaxes)
        {
            while (!pq.isEmpty() && pq.peek().key <= tStamp)
            {
                int t = pq.peek().key;
                int x = pq.peek().values[0];
                int y = pq.peek().values[1];
                pq.poll();
                if (y >= 0)
                {
                    officialEmpty += 1;
                    totalCapitalTax += y;
                    predictTaxes.push(x, y);
                }
                else if (x >= 0)
                {
                    int mTax = cityTaxes.takeTax(x, t);
                    pq.add(new PIP(t + x, new int[]{x, mTax}));
                }
                else
                    predictTaxes.updatePrediction(-x, -y);
                if (pq.isEmpty() || pq.peek().key > t)
                {
                    while (officialEmpty > 0 && predictTaxes.top() > 0)
                    {
                        int cityID = predictTaxes.top();
                        predictTaxes.pop(cityID);
                        pq.add(new PIP(t + cityID, new int[]{cityID, -1}));
                        officialEmpty -= 1;
                    }
                }
            }
        }
        void    updatePrediction(int tStamp, int cityID, int mTax)
        {
            pq.add(new PIP(tStamp, new int[]{-cityID, -mTax}));
        }
    }

    CityTaxes       ct;
    PredictTaxes    pt;
    OfficialQueue   of;

    void init(int N, int M)
    {
        this.cities = N;
        this.managers = M;
        this.ct = new CityTaxes();
        this.pt = new PredictTaxes();
        this.of = new OfficialQueue();
        this.ct.init();
        this.pt.init();;
        this.of.init();
    }

    int order(int tStamp, int mCityA, int mCityB, int mTax)
    {
        this.of.timeFlow(tStamp - 1, ct, pt);
        this.ct.reserveAddTax(mCityB, tStamp + Math.abs(mCityA - mCityB), mTax);
        this.of.updatePrediction(tStamp + Math.max(0, Math.abs(mCityA - mCityB) - mCityB), mCityB, mTax);
        return check(tStamp);
    }

    int check(int tStamp)
    {
        this.of.timeFlow(tStamp, ct, pt);
        return (this.of.totalCapitalTax);
    }

    void destroy() {}

}

