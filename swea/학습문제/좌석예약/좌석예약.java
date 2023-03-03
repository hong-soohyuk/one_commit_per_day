import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class pair
{
    int first;
    int second;
    pair(int a, int b)
    {
        this.first = a;
        this.second = b;
    }
}

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
    static final int    MAX_N = 2048;
    static final int    MAX_MID = 50001;
    static final int    NN = 128;
    static final int    INF = Integer.MAX_VALUE;
    static final PIP    INF_PIP = new PIP(INF, new int[]{INF, INF});
    static final int[] dx = new int[]{1, -1, 0, 0};
    static final int[] dy = new int[]{0, 0, 1, -1};

    int n;
    int[][][] seat = new int[MAX_N][11][11];
    int[][][] reserved = new int[MAX_N][11][11];
    PIP[] reserve_info = new PIP[MAX_MID];
    PriorityQueue<PIP>[] leaf = new PriorityQueue[NN + 1];
    PIP[] tree = new PIP[NN * 2];
    PriorityQueue<int[]>    pq =  new PriorityQueue<>((o1, o2) -> (o1[0] - o2[0]));
    Queue<int[]>            queue;
    int check_cnt;
    int[][] check = new int[11][11];

    private int encoding(int[] values)
    {
        return ((values[0] - 1) * 10 + values[1]);
    }

    PIP treeFind(int lev, int l, int r, int x, int y) {
        int mid = (l + r) / 2;
        if (x <= l && r <= y) return tree[lev];
        if (r < x || y < l) return INF_PIP;
        PIP left = treeFind(lev * 2, l, mid, x, y);
        PIP right = treeFind(lev * 2 + 1, mid + 1, r, x, y);
        if (left.key < right.key)
            return left;
        else
            return right;
    }

    void treeUpdate(int lev) {
        PIP left = tree[lev * 2];
        PIP right = tree[lev * 2 + 1];
        if (left.key < right.key)
            tree[lev] = left;
        else
            tree[lev] = right;
        if (lev > 1) treeUpdate(lev / 2);
    }

    void treeUpdateLeaf(int mSeat) {
        PIP oriValue = tree[NN + mSeat - 1];
        tree[NN + mSeat - 1] = INF_PIP;
        while (!leaf[mSeat].isEmpty()) {
            PIP top = leaf[mSeat].peek();
            if (seat[top.key][top.values[0]][top.values[1]] == mSeat) {
                tree[NN + mSeat - 1] = top;
                break;
            }
            leaf[mSeat].poll();
        }
        if (oriValue != tree[NN + mSeat - 1]) treeUpdate((NN + mSeat - 1) / 2);
    }

    void addCluster(PIP clusterInfo, int mSeat) {
        seat[clusterInfo.key][clusterInfo.values[0]][clusterInfo.values[1]] = mSeat;
        leaf[mSeat].add(clusterInfo);
        treeUpdateLeaf(mSeat);
    }

    void removeCluster(PIP clusterInfo) {
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
            reserve_info[i] = INF_PIP;
        for (int i = 1; i < NN * 2; i++) {
            tree[i] = INF_PIP;
        }
        for (int i = 1; i <= n; i++) {
            for (int x = 1; x <= 10; x++) {
                for (int y = 1; y <= 10; y++) {
                    reserved[i][x][y] = seat[i][x][y] = 0;
                }
            }
            addCluster(new PIP(i, new int[] {1, 1}), 100);
        }
    }

    Solution.Result reserveSeats(int mID, int k) {
        PIP res = treeFind(1, 1, NN, k, NN);
        if (res.key > n) return new Solution.Result();
        reserve_info[mID] = res;

        removeCluster(res);

        check_cnt += 1;
        pq.add(res.values);
        for (int i = 1; i <= k; i++) {
            int[] pos = pq.peek(); pq.poll();
            reserved[res.key][pos[0]][pos[1]] = mID;
            for (int j = 0; j < 4; j++) {
                int tx = pos[0] + dx[j];
                int ty = pos[1] + dy[j];
                if (1 <= tx && tx <= 10 && 1 <= ty && ty <= 10) {
                    if (reserved[res.key][tx][ty] == 0 && check[tx][ty] != check_cnt) {
                        check[tx][ty] = check_cnt;
                        pq.add(new int []{ tx, ty });
                    }
                }
            }
        }
        queue = new LinkedList<>();
        check_cnt += 1;
        while (!pq.isEmpty()) {
            int[] top = pq.peek(); pq.poll();
            if (check[top[0]][top[1]] != check_cnt) {
                int[] min_seat = { INF, INF };
                int cnt = 0;
                check[top[0]][top[1]] = check_cnt;
                queue.add(top);
                while (!queue.isEmpty()) {
                    int[] pos = queue.peek(); queue.poll();
                    if (min_seat[0] < pos[0])
                        min_seat = min_seat;
                    else
                        min_seat = pos;
                    cnt += 1;

                    for (int i = 0; i < 4; i++) {
                        int tx = pos[0] + dx[i];
                        int ty = pos[1] + dy[i];
                        if (1 <= tx && tx <= 10 && 1 <= ty && ty <= 10
                                && check[tx][ty] != check_cnt && reserved[res.key][tx][ty] == 0) {
                            check[tx][ty] = check_cnt;
                            queue.add(new int[]{ tx, ty });
                        }
                    }
                }
                addCluster(new PIP( res.key, min_seat ), cnt);
            }
        }
        Solution.Result result = new Solution.Result();
        result.id = res.key;
        result.num = encoding(res.values);
        return result;
    }

    Solution.Result cancelReservation(int mID) {
        removeCluster(reserve_info[mID]);

        int mTheater = reserve_info[mID].key;
        int[] pos_init = reserve_info[mID].values;
        int[] min_seat = { INF, INF };
        int cnt = 0, sum_seat = encoding(pos_init);
        queue = new LinkedList<>();
        check[pos_init[0]][pos_init[1]] = ++check_cnt;
        reserved[mTheater][pos_init[0]][pos_init[1]] = 0;
        queue.add(pos_init);

        while (!queue.isEmpty()) {
            int[] pos = queue.peek(); queue.poll();
            if (min_seat[0] < pos[0])
                min_seat = min_seat;
            else
                min_seat = pos;
            cnt += 1;
            for (int i = 0; i < 4; i++) {
                int tx = pos[0] + dx[i];
                int ty = pos[1] + dy[i];
                if (1 <= tx && tx <= 10 && 1 <= ty && ty <= 10 && check[tx][ty] != check_cnt) {
                    check[tx][ty] = check_cnt;
                    if (reserved[mTheater][tx][ty] == mID) {
                        sum_seat += encoding(new int[]{ tx, ty });
                        reserved[mTheater][tx][ty] = 0;
                        queue.add(new int[]{ tx, ty });
                    }
                    else if (reserved[mTheater][tx][ty] == 0) {
                        if (seat[mTheater][tx][ty] > 0) {
                            removeCluster(new PIP(mTheater, new int[]{ tx, ty } ));
                        }
                        queue.add(new int[]{ tx, ty });
                    }
                }
            }
        }

        addCluster(new PIP(mTheater, min_seat), cnt);

        Solution.Result result = new Solution.Result();
        result.id = mTheater;
        result.num = sum_seat;
        return result;
    }
}
