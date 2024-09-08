import java.util.Scanner;

public class j20DistributeCandiesToPeople {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int candies = in.nextInt();
        int num_people = in.nextInt();
        System.out.println(distributeCandies(candies,num_people));
        in.close();
    }

    public static int[] distributeCandies(int candies, int num_people) {
        int[] out = new int[num_people];
        int s = 1;
        while(candies > 0){
         for(int i = 0; i < num_people; i++){
            out[i] += (s < candies) ? s : candies;
            candies -= s;
            if(candies <= 0) break;
            s++;
          }
       }
       return out;
    }
}
