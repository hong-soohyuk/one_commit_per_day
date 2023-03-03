import java.util.*;

class UserSolution {
    Map<Integer, List<List<Long>>>  users;
    int                             max;

    List<Long>  str2hash(char[] string)
    {
        List<Long>  words = new LinkedList<>();
        int     i = -1;
        long    hash = 0;
        while (string[++i] != '\0')
        {
            if (string[i] == ' ')
            {
                words.add(hash);
                hash = 0;
                continue ;
            }
            hash += (string[i] - 'a' + 1);
            hash <<= 5;
        }
        words.add(hash);
        return (words);
    }

    public void init(int N, int K)
    {
        this.users = new HashMap<>();
        for (int i = 0; i < N; i++)
            this.users.put(i, new LinkedList<>());
        this.max = K;
    }

    public void sendMail(char[] subjectStr, int uID, int cnt, int[] rIDs)
    {
        List<Long> hash = str2hash(subjectStr);
        for (int id = 0; id < cnt; id++)
        {
            if (this.users.get(rIDs[id]).size() == this.max)
                this.users.get(rIDs[id]).remove(0);
            this.users.get(rIDs[id]).add(hash);
        }
    }

    public int  getCount(int uID) {return (this.users.get(uID).size());}

    public int  deleteMail(int uID, char[] subject)
    {
        List<Long>          target_hash = str2hash(subject);
        List<List<Long>>    inbox = this.users.get(uID);
        int                 count = inbox.size();

        inbox.removeIf(mail -> word_compare(mail, target_hash));
        return (count - inbox.size());
    }

    private boolean word_compare(List<Long> words, List<Long> target)
    {
        if (words.size() != target.size())
                return (false);
        for (int i = 0; i < words.size(); i++)
            if (words.get(i).longValue() != target.get(i).longValue())
                return (false);
        return (true);
    }

    public int  searchMail(int uID, char[] text)
    {
        int     count = 0;
        long    target = str2hash(text).get(0).longValue();

        for (List<Long> mail : this.users.get(uID))
        {
            mail_loop:
            for (Long word : mail)
            {
                if (word.longValue() == target)
                {
                    count++;
                    break mail_loop;
                }
            }
        }
        return count;
    }
}
