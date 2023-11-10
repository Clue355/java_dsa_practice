# 53. Maximum Subarray
https://leetcode.com/problems/maximum-subarray/description/

**Problem Statement:**
Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Example:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

**Solution Approach (Kadane's Algorithm):**
The efficient way to solve this problem is to use a technique known as Kadane's Algorithm. The basic idea of Kadane's algorithm is to scan the entire list and at each position find the maximum sum of the subarray that ends at that position. This can be done using a single loop through the array, maintaining a running sum of the current subarray and the maximum sum found so far.

implement Kadane's Algorithm in Java:

1. Initialize two variables, `maxSoFar` to the first element of the array (or `Integer.MIN_VALUE` to handle an array of all negative numbers) and `maxEndingHere` to `0`.
2. Iterate through each number in the `nums` array:
    - Update `maxEndingHere` by adding the current number.
    - If `maxEndingHere` is greater than `maxSoFar`, update `maxSoFar` with the value of `maxEndingHere`.
    - If `maxEndingHere` becomes negative, reset it to `0` since a subarray with a negative sum will not contribute to a maximum subarray ending at a later position.
3. Return `maxSoFar` at the end, which will hold the maximum sum of any subarray.

Java code implementing the algorithm:

```java
public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Include the current element in the maxEndingHere or start a new subarray
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // Update maxSoFar if needed
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
```

In the above code:
- `maxSoFar` keeps track of the maximum sum encountered so far.
- `maxEndingHere` keeps track of the maximum sum of subarrays ending at the current index.

**Complexity Analysis:**
- **Time Complexity:** O(n), where n is the number of elements in the array, as we're going through the array with a single loop.
- **Space Complexity:** O(1), as we're only using two variables and not using any additional space that grows with the input size.

This line is the core part of Kadane's algorithm, which is used to find the maximum sum subarray in a one-dimensional array.

Here's the breakdown of what `maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);` does:

- `maxEndingHere + nums[i]`: This calculates the sum of the current subarray which includes the element at the current index `i`. It adds the current element `nums[i]` to the current running subarray sum `maxEndingHere`.

- `nums[i]`: This is considering the current element as the start of a new subarray. It's like saying, "What if the maximum subarray starts from here?" This is important because if the `maxEndingHere` becomes negative, it would reduce the sum of any subarray that includes it. Starting fresh with the current element `nums[i]` might lead to a larger sum.

- `Math.max(nums[i], maxEndingHere + nums[i])`: This chooses the larger value between the current element itself and the sum of the current element and the running sum (`maxEndingHere`). This step effectively decides whether to "extend the current subarray" or "start a new subarray" from the current element.

The line ensures that `maxEndingHere` always stores the maximum sum of a subarray that ends at the current index. If the array has only negative numbers, this approach will pick the least negative number. If the sum ever becomes negative, it resets `maxEndingHere` because a subarray with a negative sum is not going to contribute to a maximum sum of a future subarray by extending it; instead, it's better to start a new subarray from the current position.

Here's a step-by-step illustration with an example array: `[-2, 1, -3, 4, -1, 2, 1, -5, 4]`

- Start with `maxEndingHere = -2` (first element).
- At index 1, `maxEndingHere` will be `Math.max(1, -2 + 1)`, which is `1`.
- At index 2, `maxEndingHere` will be `Math.max(-3, 1 + (-3))`, which is `-2`.
- At index 3, `maxEndingHere` will be `Math.max(4, -2 + 4)`, which is `4`.
- ... and so on, for each element in the array.

By the end of the loop, `maxEndingHere` would have considered every possible subarray and `maxSoFar` would hold the maximum sum encountered.