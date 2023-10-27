package BinarySearch;

/*
Binary Search is an efficient algorithm for finding a target value within a sorted array.
It works by repeatedly dividing in half the portion of the array that could contain the target,
and comparing the target value to the middle element of that portion.

This process continues until the target value is found or the array has been narrowed down to zero size.
Binary Search has a time complexity of O(log n), making it much more efficient than linear search (O(n)) in a sorted array.

Top Coder-Binary Search Article | Link: https://www.topcoder.com/thrive/articles/Binary%20Search
Leetcode Explore - Template Analysis | Link: https://leetcode.com/explore/learn/card/binary-search/135/template-iii/936/
*/

// This solution is for normal sorted arrays
public class BinarySearchExample {
    // Returns index of target if it is present in arr[], else return -1
    int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the target is present at mid
            if (arr[mid] == target)
                return mid;

            // If target greater, ignore the left half
            if (arr[mid] < target)
                left = mid + 1;

                // If target is smaller, ignore the right half
            else
                right = mid - 1;
        }
        // Target was not present or return left if target is between 2 indexes or at the ends of the array
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchExample bs = new BinarySearchExample();
        int[] arr = {2, 3, 4, 10, 40};
        int target = 10;
        int result = bs.binarySearch(arr, target);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index " + result);
    }
}

// This solution is for shifted sorted arrays
// ex: [5,6,7,0,1,2,3,4]
class ShiftedArrays {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;  // Target found
            }

            if (nums[left] <= nums[mid]) {  // Left side is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;  // Target is in the left half
                } else {
                    left = mid + 1;  // Target is in the right half
                }
            } else {  // Right side is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // Target is in the right half
                } else {
                    right = mid - 1;  // Target is in the left half
                }
            }
        }

        return -1;  // Target not found
    }
}
