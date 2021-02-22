public class HanoiTower {
    private static int count ;
    public static void main(String[] args) {
        hanoiTower(5, 'a', 'b','c');
        System.out.println(count);
    }

    private static void hanoiTower(int num, char a, char b, char c) {
        count ++;
        if (num == 1) {
            System.out.println("第 1 个盘从" + a + "->" + c);
        }else{
            hanoiTower(num-1, a, c, b);
            System.out.println("第 "+num +" 个盘从" + a + "->" + c);
            hanoiTower(num-1, b, a, c);
        }
    }
}
