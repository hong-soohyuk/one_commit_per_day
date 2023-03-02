import java.util.Arrays;

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
    int     MAX_N = 150001;
    int     size;
    Node[]  tree = new Node[MAX_N << 2];

    Node merge(Node a, Node b)
    {
        int min = Math.min(a.min, b.min);
        int max = Math.max(a.max, b.max);
        int alive = a.alive + b.alive;
        return (new Node(max, min, alive));
    }

    void    update(int s, int e, int node,  int i , int v)
    {
        if (i < s || e < i)
            return ;
        if (s == e)
        {
            if (v == -100)
                tree[node] = new Node();
            else
                tree[node] = new Node(v, v, 1);
            return ;
        }
        int mid = (s + e) >> 1;
        update(s, mid, node << 1, i, v);
        update(mid + 1, e,node << 1 | 1, i, v);
        tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
    }


    void init(int N, int[] mValue)
    {
        Arrays.fill(tree, new Node());
        size = 0;
        for (int i = 0; i < N; i++)
            update(1, MAX_N, 1, ++size, mValue[i]);
    }

    public void add(int M, int[] mValue)
    {
        for (int i = 0; i < M; i++)
            update(1, MAX_N, 1, ++size, mValue[i]);
    }

    public void erase(int from, int to)
    {
        for (int i = to; i >= from; i--)
        {
            int k = find_k(1, MAX_N, 1, i);
            update(1, MAX_N, 1, k, -100);
        }
    }

    public int find(int k)
    {
        int total_alive = tree[1].alive;
        int find = find_k(1, MAX_N, 1, total_alive - k + 1);
        Node result = query(1, MAX_N, 1, find, size);
        return result.max - result.min;
    }

    private int find_k(int s, int e, int n, int num)
    {
        if (s == e)
            return s;
        int mid = (s + e) / 2;
        if (tree[n << 1].alive >= num)
            return find_k(s, mid, n << 1, num);
        else
            return find_k(mid + 1, e, n << 1 | 1, num - tree[n << 1].alive);
    }

    private Node query(int s, int e, int n, int left, int right)
    {
        if (s > right || e < left)
            return new Node();
        if (left <= s && e <= right)
            return tree[n];
        int mid = (s + e) / 2;
        Node n1 = query(s, mid, n << 1, left, right);
        Node n2 = query(mid + 1, e, n << 1 | 1, left, right);
        return merge(n1, n2);
    }
}

