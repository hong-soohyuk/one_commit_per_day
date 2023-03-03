package Solution;

import java.io.*;
import java.util.*;
class Solution {
    static Set<String> treeset;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder   sb = new StringBuilder();
        int		        test, T;
        treeset = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length())
                    return (o1.length() - o2.length());
                else
                    return (o1.compareTo(o2));
            }
        });
        T = Integer.parseInt(br.readLine());
        for (test = 1; test <= T; test++)
        {
            treeset.clear();
            int lines = Integer.parseInt(br.readLine());
            for (int i = 0; i < lines; i++)
                treeset.add(br.readLine());

            sb.append("#").append(test).append("\n");
            for (String name : treeset)
                sb.append(name).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
