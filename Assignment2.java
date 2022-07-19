import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Assignment2 {
  static int moves;
  static int swaps;

  public static ArrayList<String> deleteStopwords(String input, String stopwords) throws FileNotFoundException {
      // TO BE COMPLETED
      ArrayList<String> result = new ArrayList<>();
      ArrayList<String> del = new ArrayList<>();

      Scanner s = new Scanner(new File("input.txt"));
          while (s.hasNext()) {
              result.add(s.next());
          }
          s.close();

      Scanner s1 = new Scanner(new File("stopwords.txt"));
          while (s1.hasNext()) {
              del.add(s1.next());
          }
          s1.close();

          result.removeAll(del);
          return result;

    }

    public static ArrayList<String> insertionSort(ArrayList<String> listofWords){
        for(int i = 1; i< listofWords.size(); i++){
          String item = listofWords.get(i);
          int j = i;
          while( j>0 && item.compareTo(listofWords.get(j-1)) <0)  {
            moves++;
            listofWords.set(j, listofWords.get(j-1));
            swaps++;
            j = j-1;
          }

          listofWords.set(j, item);
          moves++;
        }
        return listofWords;
    }

    public static ArrayList<String> mergeSort(ArrayList<String> listofWords){
      moves = 0;
      swaps = 0;
      moves++;
      ArrayList<String> left = new ArrayList<>();
      ArrayList<String> right = new ArrayList<>();
      int midp = listofWords.size()/2;

      if (listofWords.size() <2){
        return listofWords;
      }
      //get left ele
      for (int i=0; i<midp; i++){
        left.add(listofWords.get(i));
        moves++;
      }//get right ele
      for (int j=midp; j<listofWords.size(); j++){
        right.add(listofWords.get(j));
        moves++;
      }

      left = mergeSort(left);
      right = mergeSort(right);
      return merge(left, right);
    }//endfunc

    public static ArrayList<String> merge(ArrayList<String> left,ArrayList<String> right){
      ArrayList<String> sorted = new ArrayList<>();
      int l= 0,r= 0;

      while(l<left.size() && r<right.size() ){
        moves++;
        if (left.get(l).compareTo(right.get(r)) <=0 ){
          swaps++;
          sorted.add(left.get(l));
          l++;
        }
        else{
          sorted.add(right.get(r));
          swaps++;
          r++;
        }
      }
      while(l < left.size()){sorted.add(left.get(l)); l++;
        moves++;
        swaps++;
      }
      while(r < right.size()){sorted.add(right.get(r)); r++;
        moves++;
        swaps++;
      }
      return sorted;

    }//endfunc

    public static void main(String[] args)throws FileNotFoundException {
      //System.out.println(Assignment2.deleteStopwords("input.txt", "stopwords.txt"));
      //System.out.println(Assignment2.insertionSort(Assignment2.deleteStopwords("input.txt", "stopwords.txt")));
      //System.out.println(Assignment2.mergeSort(Assignment2.deleteStopwords("input.txt", "stopwords.txt")));

      ArrayList<String> allWords = Assignment2.deleteStopwords("input.txt", "stopwords.txt");
      for (int i = 100; i <= 500; i += 100){
        if (i> allWords.size()){
          i = allWords.size();
        }
        ArrayList<String> words = new ArrayList<String>(allWords.subList(0, i));
        moves = 0;
        swaps = 0;
        System.out.println(words.size());
        //insertionSort
        long startInsertion = System.nanoTime();
        ArrayList<String> DoneInsertion = Assignment2.insertionSort(words);
        long endInsertion = System.nanoTime();
        long InsertionT = (endInsertion - startInsertion);
        System.out.println("INSERTION sort");
        System.out.println("Time taken: " + Long.toString(InsertionT) + " Nano seconds");
        System.out.println("Moves:" + Integer.toString(moves));
        System.out.println("Swaps:" + Integer.toString(swaps));
        //mergeSort
        moves = 0;
        swaps = 0;
        long startMerge = System.nanoTime();
        ArrayList<String> DoneMerge = Assignment2.mergeSort(words);
        long endMerge = System.nanoTime();
        long MergeT = (endMerge - startMerge);
        System.out.println("MERGE sort");
        System.out.println("Time taken: " + Long.toString(MergeT) + " Nano seconds");
        System.out.println("Moves:" + Integer.toString(moves));
        System.out.println("Swaps:" + Integer.toString(swaps));

      }//end func
    }

}//end
