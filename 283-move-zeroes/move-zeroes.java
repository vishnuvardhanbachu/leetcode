class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0; // position for next non-zero

        // Move non-zero elements forward
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        // Fill remaining positions with 0
        while (j < nums.length) {
            nums[j] = 0;
            j++;
        }
    }
}
