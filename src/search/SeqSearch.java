package search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] array = {1, 9, 11, -11, 34, 89};
        int index = seqSearch(array, -11);
        if (index == -1) {
            System.out.println("没有找到");
        }else{
            System.out.println("找到，下标为："+ (index+1));
        }
    }

    private static int seqSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++)
            if(array[i] == value)
                return i;
        return -1;
    }

}
