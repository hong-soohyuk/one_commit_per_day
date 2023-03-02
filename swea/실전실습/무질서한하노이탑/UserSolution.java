import java.util.Arrays;
import java.util.Stack;

class disk implements Comparable<disk>
{
    int size;
    int depart;
    int destination;
    disk(int size, int depart)
    {
        this.size = size;
        this.depart = depart;
    }

    @Override
    public int compareTo(disk o) {return (this.size - o.size);}
}

class Peek
{
    int first, second, third;

    Peek(int first, int second, int third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

class UserSolution {
    Stack<disk>[]   top;
    Peek[]          peeks;
    disk[]          disks;
    int count, n;
    int a, b, c;

    void init(int N[], int mDisk[][])
    {
        top = new Stack[4];
        peeks = new Peek[500001];
        count = 0;
        n = 0;
        for (int i = 1; i <= 3; i++)
            top[i] = new Stack<>();
        disks = new disk[N[0] + N[1] + N[2]];
        int index = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < N[i]; j++)
                disks[index++] = new disk(mDisk[i][j], i + 1);
        Arrays.sort(disks);
        for (int i = disks.length - 1; i >= 0; i--)
        {
            if (i == disks.length - 1)
                disks[i].destination = 3;
            else
            {
                int d = -1;
                for (int j = 1; j <= 3; j++)
                    if (j != disks[i + 1].depart && j != disks[i + 1].destination)
                        d = j;
                if (disks[i + 1].depart == disks[i + 1].destination)
                    d = disks[i + 1].destination;
                disks[i].destination = d;
            }
        }
        for (int i = disks.length - 1; i >= 0; i--)
            top[disks[i].depart].push(disks[i]);
        for (int i = 0; i < disks.length; i++)
        {
            int mid = -1;
            for (int j = 1; j <= 3; j++)
                if (j != disks[i].depart && j != disks[i].destination)
                    mid = j;
            if (i == 0)
            {
                Move(disks[i].depart, disks[i].destination);
                continue;
            }
            if (disks[i].depart != disks[i].destination)
            {
                Move(disks[i].depart, disks[i].destination);
                Hanoi(i, mid, disks[i].destination, disks[i].depart);
            }
            else
                Hanoi(i, disks[i - 1].destination, disks[i].destination, disks[i].depart);
        }
    }

    private void Hanoi(int i, int start, int end, int mid)
    {
        if (count >= 500000)
            return ;
        if (i == 1)
        {
            Move(start, end);
            return ;
        }
        Hanoi(i - 1, start, mid, end);
        Move(start, end);
        Hanoi(i - 1, mid, end, start);
    }

    private void Move(int depart, int destination)
    {
        if (count >= 500000)
            return ;
        if (depart == destination)
            return ;
        if (top[3].size() == disks.length)
            peeks[++count] = new Peek(0, 0, top[3].peek().size);
        else
        {
            if (!top[depart].isEmpty())
                top[destination].push(top[depart].pop());
            if (top[1].isEmpty())
                a = 0;
            else
                a = top[1].peek().size;
            if (top[2].isEmpty())
                b = 0;
            else
                b = top[2].peek().size;
            if (top[3].isEmpty())
                c = 0;
            else
                c = top[3].peek().size;
            peeks[++count] = new Peek(a, b, c);
        }
    }


    void go(int k, int mTop[])
    {
        n += k;
        if (n > count)
        {
            mTop[0] = 0;
            mTop[1] = 0;
            mTop[2] = top[3].peek().size;
        }
        else
        {
            mTop[0] = peeks[n].first;
            mTop[1] = peeks[n].second;
            mTop[2] = peeks[n].third;
        }
    }

    void destroy() {}
}

