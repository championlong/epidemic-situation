package leetcode;



/**
 * @author 1159588554@qq.com
 * @date 2020/10/5 21:28
 */
public class  Solution {
    public static void main(String[] args) {
        /*int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = findRepeatNumber(nums);
        System.out.println(repeatNumber);*/
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        boolean numberIn2DArray = findNumberIn2DArray(matrix, 5);
        System.out.println(numberIn2DArray);

    }

    /**
     * 利用java中set集合的无序性
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                //位置正确
                continue;
            }
            if (nums[i] == nums[nums[i]]) {
                //重复
                return nums[i];
            }
            //交换
            int temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
            //这里的i--是为了抵消掉上面的i++，
            //交换之后需要原地再比较
            i--;
        }
        return -1;


        //第三种方法，建立临时数组
        /*int length = nums.length;
        int[] temps = new int[length];
        for(int num:nums){
            temps[num]++;
            if(temps[num] > 1){
                return num;
            }
        }
        return -1;*/


        /*int repeat = -1;
        Set<Integer> set = new HashSet<>();
        for(int num:nums){
            if(!set.add(num)){
                //添加失败说明重复
                repeat = num;
                break;
            }
        }
        return repeat;*/
        /*int temp;
        for(int i=0;i<nums.length;i++){
            while (nums[i]!=i){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                temp=nums[i];
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;*/
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length - 1;
        while(row < matrix.length && column >= 0){
            int num = matrix[row][column];
            if(target == num){
                return true;
            }
            else {
                if(target > num){
                    row ++;
                } else {
                    column --;
                }
            }
        }
        return false;
    }
}
