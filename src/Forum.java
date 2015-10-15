import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DrScott on 10/15/15.
 */
public class Forum {
    public static void main(String[] args) {
        ArrayList<Post> posts = new ArrayList();
        String postContent = readFile("posts.txt");
        String[] lines = postContent.split("\n"); //  \n means new lines. will split postContent on the new lines for the array list

        for (String line : lines) {
            String[] columns = line.split("\\|");
            int replyID = Integer.valueOf(columns[0]);
            String author = columns[1];
            String text = columns[2];
            Post post = new Post(replyID, author, text);
            posts.add(post);
        }

        Scanner scanner = new Scanner(System.in);
        int postID = -1;
        while (true) {
            printPosts(posts, postID);
            System.out.println("Enter post id to see replies");
            postID = Integer.valueOf(scanner.nextLine());
        }
    }
    static void printPosts(ArrayList<Post> posts, int postID){
        int id = 0;
        for (Post post : posts) {
            if (post.replyID ==postID) {
                System.out.println(String.format("[%d] %s (%s)", id, post.text, post.author));
            }
            id++;
        }
    }


    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }
    static void writeFile(String fileName, String fileContent){
        File f = new File(fileName);
        try{
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e){

        }
    }
}
