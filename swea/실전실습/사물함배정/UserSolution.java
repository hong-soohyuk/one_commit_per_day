import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Locker implements Comparable<Locker>
{
    int		start;
    int length;
    boolean valid;
    Locker	prev;
    Locker	next;

    Locker(int start, int length, boolean valid, Locker prev, Locker next)
    {
        this.start = start;
        this.length = length;
        this.valid = valid;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public int compareTo(Locker o) {
        if (o.length == this.length)
            return (this.start - o.start);
        else
            return (o.length - this.length);
    }
}

class UserSolution {
    Locker[]		        lockers = new Locker[40000];
    PriorityQueue<Locker>   pq = new PriorityQueue<>();
    Map<Integer, Locker>    user_to_locker = new HashMap<>();
    int		n;
    int     index;
    int     count;

    public void init(int N) {
        index = 0;
        pq.clear();
        user_to_locker.clear();
        n = N;
        count = N;
        Locker node = new Locker(1, N, true, null, null);
        lockers[index++] = node;
        pq.add(node);
    }


    public int arrive(int mId)
    {
        Locker longest_locker = pq.poll();
        Locker assigned = lockers[index++];
        while (!longest_locker.valid)
            longest_locker = pq.poll();
        assigned = new Locker(0, 1, false, null, null);
        if (longest_locker.start == 1)
        {
            assigned.start = 1;
            longest_locker.start += 1;
            longest_locker.length -= 1;
            assigned.next = longest_locker;
            longest_locker.prev = assigned;
        }
        else if (longest_locker.start + longest_locker.length > n)
        {
            assigned.start = n;
            longest_locker.length -= 1;
            longest_locker.next = assigned;
            assigned.prev = longest_locker;
        }
        else
        {
            Locker right = lockers[index++];
            assigned.start = longest_locker.start + ((longest_locker.length - 1) / 2);
            right = new Locker(assigned.start+1, (longest_locker.length) / 2, true, null, null);
            longest_locker.length = (longest_locker.length - 1) / 2;
            push(longest_locker, assigned);
            if (right.length > 0)
            {
                push(assigned, right);
                pq.add(right);
            }
        }

        if (longest_locker.length == 0)
            delete(longest_locker);
        else
            pq.add(longest_locker);
        user_to_locker.put(mId, assigned);
        count--;

        return assigned.start;
    }



    void	push(Locker at, Locker new_node)
    {
        new_node.next = at.next;
        if (at.next != null)
            at.next.prev = new_node;
        at.next = new_node;
        new_node.prev = at;
    }

    void delete(Locker del)
    {
        Locker  prev = del.prev;
        Locker  next = del.next;
        if (prev != null)
            prev.next = next;
        if (next != null)
            next.prev = prev;
        del.valid = false;
    }

    public int leave(int mId) {
        Locker assigned = user_to_locker.get(mId);
        assigned.valid = true;
        if (assigned.prev != null && assigned.prev.valid)
        {
            assigned.start = assigned.prev.start;
            assigned.length = assigned.prev.length + 1;
            delete(assigned.prev);
        }

        if (assigned.next != null && assigned.next.valid)
        {
            assigned.length += assigned.next.length;
            delete(assigned.next);
        }
        pq.add(assigned);
        user_to_locker.remove(mId);
        count++;
        return count;
    }
}
