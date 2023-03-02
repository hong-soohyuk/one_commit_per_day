class Node
{
    int max;
    int min;
    int alive;
    Node()
    {
        this.max = Integer.MIN_VALUE;
        this.min = Integer.MAX_VALUE;
        this.alive = 0;
    }
    Node(int max, int min, int alive)
    {
        this.max = max;
        this.min = min;
        this.alive = alive;
    }
}

class UserSolution
{
    int     MAX_N = 262144;
    Node[]  tree = new Node[MAX_N << 2];
    int     totalAdd;

    void    clearTree()
    {
        for (int i = 0; i < (MAX_N << 2); i++)
            tree[i] = new Node();
    }

    Node merge(Node a, Node b)
    {
        int max = Math.max(a.max, b.max);
        int min = Math.min(a.min, b.min);
        int alive = a.alive + b.alive;
        return (new Node(max, min, alive));
    }

    Node    query(int node, int s, int e, int i, int j)
    {
        if (j < s || e < i)
            return (new Node());
        if (i <= s && e <= j)
            return (tree[node]);
        int mid = s + e >> 1;
        Node p = query(node << 1, s, mid, i, j);
        Node q = query(node << 1 | 1, mid + 1, e, i, j);
        return (merge(p, q));
    }

    int Kth(int node, int s, int e, int k)
    {
        if (s == e)
            return (s);
        int mid = s + e >> 1;
        if (tree[node << 1].alive < k)
            return (Kth(node << 1 | 1, mid + 1, e,k - tree[node << 1].alive));
        return (Kth(node << 1, s, mid, k));
    }

    void    update(int node, int s, int e, int i , int v)
    {
        if (i < s || e < i)
            return ;
        if (s == e)
        {
            if (v == Integer.MAX_VALUE)
                tree[node] = new Node();
            else
                tree[node] = new Node(v, v, 1);
            return ;
        }
        int mid = s + e >> 1;
        update(node << 1, s, mid, i, v);
        update(node << 1 | 1, mid + 1, e, i, v);
        tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
    }


    void init(int N, int[] mValue)
    {
        clearTree();
        totalAdd = 0;
        for (int i = 0; i < N; i++)
            update(1, 1, MAX_N, ++totalAdd, mValue[i]);
    }

    public void add(int M, int[] mValue)
    {
        for (int i = 0; i < M; i++)
            update(1, 1, MAX_N, ++totalAdd, mValue[i]);
    }

    public void erase(int from, int to)
    {
        for (int i = to; i >= from; i--)
        {
            int del = Kth(1, 1, MAX_N, i);
            update(1, 1, MAX_N, del, Integer.MAX_VALUE);
        }
    }

    public int find(int k)
    {
        int totalAlive = tree[1].alive;
        int fake_start = totalAlive - k + 1;
        int real_start = Kth(1, 1, MAX_N, fake_start);
        Node result = query(1, 1, MAX_N, real_start, MAX_N);
        return (result.max - result.min);
    }
}

