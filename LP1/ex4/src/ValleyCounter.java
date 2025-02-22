public class ValleyCounter {
    public static int countValleys(String path){
        int currentLevel = 0;
        int valleys = 0;
        for(char ch : path.toCharArray()){
            if(ch == 'U'){
                currentLevel++;
            }else if(ch=='D'){
                currentLevel--;
            }
            if(currentLevel ==0 && ch =='U'){
                valleys++;
            }
        }
        return valleys;
    }
}
