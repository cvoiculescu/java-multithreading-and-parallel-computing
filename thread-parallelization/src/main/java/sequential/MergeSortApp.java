package sequential;

public class MergeSortApp {
    public static void main(String[] args) {
        int[] nums ={5,-1,0,7,2,3,2,1,0,1,2};
        MergeSort sort = new MergeSort(nums);
        sort.sort();
        sort.showArray();
    }
}
