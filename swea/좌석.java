import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class   PIP
{
    int key;
    int values[];
    PIP(int key, int[] array)
    {
        this.key = key;
        this.values = array;
    }
}

class UserSolution
{
    static final int    MAX_N = 2048;
    static final int    MAX_MID = 50001;
    static final int    NN = 128;
    static final int    INF = Integer.MAX_VALUE;
//    static final PIP    INF_PIP = new PIP(INF, new int[]{INF, INF});
    static final int dx[] = new int[]{1, -1, 0, 0};
    static final int dy[] = new int[]{0, 0, 1, -1};

    int n;
    int seat[][][] = new int[MAX_N][11][11];
    int reserved[][][] = new int[MAX_N][11][11];
    PIP reserve_info[] = new PIP[MAX_MID];
    PriorityQueue<PIP>          leaf[] = new PriorityQueue[NN + 1];
    PIP tree[] = new PIP[NN * 2];
    PriorityQueue<int[]>    pq =  new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
    Queue<int[]>            queue = new LinkedList<>();
    int check_count;
    int check[][] = new int[11][11];

    private int encoding(int[] values)
    {
        return ((values[0] - 1) * 10 + values[1]);
    }

    private PIP treeFind(int lev, int left, int right, int start, int end)
    {
        int mid = (left + right) >> 1;
        if (start <= left && right <= end)
            return tree[lev];
        if (right < start || end < left)
            return new PIP(INF, new int[]{INF, INF});
        PIP l = treeFind(lev << 1, left, mid, start, end);
        PIP r = treeFind(lev << 1 | 1, mid + 1, right, start, end);
        return (l.key < r.key ? l : r);
    }

    private void treeUpdate(int lev)
    {
        PIP left = tree[lev << 1];
        PIP right = tree[lev << 1 | 1];

        tree[lev] = (left.key < right.key ? left : right);
        if (lev > 1)
            treeUpdate(lev / 2);
    }

    private void treeUpdateLeaf(int mSeat)
    {
        PIP value = tree[NN + mSeat - 1];
        tree[NN + mSeat - 1] = new PIP(INF, new int[]{INF, INF});
        while (!leaf[mSeat].isEmpty())
        {
            PIP top = leaf[mSeat].peek();
            if (seat[top.key][top.values[0]][top.values[1]] == mSeat)
            {
                tree[NN + mSeat - 1] = top;
                break;
            }
            leaf[mSeat].poll();
        }
        if (value != tree[NN + mSeat - 1])
            treeUpdate((NN + mSeat - 1) / 2);
    }

    void addCluster(PIP clusterInfo, int mSeat)
    {
        seat[clusterInfo.key][clusterInfo.values[0]][clusterInfo.values[1]] = mSeat;
        leaf[mSeat].add(clusterInfo);
        treeUpdateLeaf(mSeat);
    }

    void removeCluster(PIP clusterInfo)
    {
        int mSeat = seat[clusterInfo.key][clusterInfo.values[0]][clusterInfo.values[1]];
        seat[clusterInfo.key][clusterInfo.values[0]][clusterInfo.values[1]] = 0;
        treeUpdateLeaf(mSeat);
    }

    void init(int N)
    {
        n = N;
        for (int i = 1; i <= NN; i++)
            leaf[i] = new PriorityQueue<>(Comparator.comparingInt(o -> o.key));
        for (int i = 0; i < MAX_MID; i++)
            reserve_info[i] = new PIP(INF, new int[]{INF, INF});
        for (int i = 1; i < NN * 2; i++)
            tree[i] = new PIP(INF, new int[]{INF, INF});
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= 10; j++)
            {
                for (int k = 1; k <= 10; k++)
                    reserved[i][j][k] = seat[i][j][k] = 0;
            }
            addCluster(new PIP(i, new int[]{1, 1}), 100);
        }
    }

    Solution.Result reserveSeats(int mID, int K)
    {
        PIP res = treeFind(1, 1, NN, K, NN);
        if (res.key > n)
            return new Solution.Result();
        reserve_info[mID] = res;

        removeCluster(res);

        check_count += 1;
        pq.add(res.values);
        for (int i = 1; i <= K; i++)
        {
            int[]   pos = pq.poll();
            reserved[res.key][pos[0]][pos[1]] = mID;
            for (int j = 0; j < 4; j++)
            {
                int tx = pos[0] + dx[j];
                int ty = pos[1] + dy[j];
                if (1 <= tx && tx <= 10 && 1 <= ty & ty <= 10)
                {
                    if (reserved[res.key][tx][ty] == 0 && check[tx][ty] != check_count)
                    {
                        check[tx][ty] = check_count;
                        pq.add(new int[]{tx, ty});
                    }
                }
            }
        }
        check_count++;
        while (!pq.isEmpty())
        {
            int[]   top = pq.poll();
            if (check[top[0]][top[1]] != check_count)
            {
                int[]   min_seat = new int[]{INF, INF};
                int count = 0;
                check[top[0]][top[1]] = check_count;
                queue.add(top);
                while (!queue.isEmpty())
                {
                    int[]   pos = queue.poll();
                    min_seat = (min_seat[0] < pos[0] ? min_seat : pos);
                    count++;

                    for (int i = 0; i < 4; i++)
                    {
                        int tx = pos[0] + dx[i];
                        int ty = pos[1] + dy[i];
                        if (1 <= tx && tx <= 10 && 1 <= ty && ty <= 10
                        && check[tx][ty] != check_count && reserved[res.key][tx][ty] == 0)
                        {
                            check[tx][ty] = check_count;
                            queue.add(new int[]{tx, ty});
                        }
                    }
                }
            }
        }
        Solution.Result result = new Solution.Result();
        result.id = res.key;
        result.num = encoding(res.values);
        return (result);
    }




    Solution.Result cancelReservation(int mID)
    {
        removeCluster(reserve_info[mID]);

        int mTheater = reserve_info[mID].key;
        int[]   pos_init = reserve_info[mID].values;
        int[]   min_seat = new int[] {INF, INF};
        int count = 0;
        int sum_seat = encoding(pos_init);
        check[pos_init[0]][pos_init[1]] = ++check_count;
        reserved[mTheater][pos_init[0]][pos_init[1]] = 0;
        queue.add(pos_init);

        while (!queue.isEmpty())
        {
            int[]   pos = queue.poll();
            min_seat = (min_seat[0] < pos[0] ? min_seat : pos);
            count++;

            for (int i = 0; i < 4; i++)
            {
                int tx = pos[0] + dx[i];
                int ty = pos[1] + dy[i];
                if (1 <= tx && tx <= 10 && 1 <= ty && ty <= 10 && check[tx][ty] != check_count)
                {
                    check[tx][ty] = check_count;
                    if (reserved[mTheater][tx][ty] == mID) {
                        sum_seat += encoding(new int[]{tx, ty});
                        reserved[mTheater][tx][ty] = 0;
                        queue.add(new int[]{tx, ty});
                    }
                    else if (reserved[mTheater][tx][ty] == 0) {
                        if (seat[mTheater][tx][ty] > 0)
                            removeCluster(new PIP(mTheater, new int[]{tx, ty}));
                        queue.add(new int[] {tx, ty});
                    }
                }
            }
        }
        addCluster(new PIP(mTheater, min_seat), count);
        Solution.Result result = new Solution.Result();
        result.id = mTheater;
        result.num = sum_seat;
        return (result);
    }
}
