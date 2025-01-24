package service;

import model.Post;
import model.User;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class PostService {
    private PostRepository postRepository = new PostRepository();
    private UserRepository userRepository = new UserRepository();
    private LikeRepository likeRepository = new LikeRepository();
    //todo adaugam CommentRepository

    private Scanner scNumere = new Scanner(System.in);
    private Scanner scText = new Scanner(System.in);


    public void readAll() {
        ArrayList<Post> posts = postRepository.readAll();

        for (Post post : posts) {
            post.setLikedBy(LikeRepository.getPostLikes(post.getId()));
            //todo adaugam si comentariile
        }
        for (Post post: posts) {
            System.out.println(post);
        }

    }

    public void readById() {
        System.out.println("introduceti idul postarii: ");
        int id = scNumere.nextInt();

        Post post = postRepository.readById(id);

        if (post==null){
            System.out.println("Postarea cu idul " + id + " nu exista");
        }else{
            post.setLikedBy(likeRepository.getPostLikes(post.getId()));
            //todo populam si lista de comentarii
            System.out.println(post);

        }


    }

    public void readByUserId() {
        System.out.println("Introduceti idul userului:");
        int userId = scNumere.nextInt();

        ArrayList<Post> postarileUtilizatorului = postRepository.readUserPosts(userId);

        if(postarileUtilizatorului.size()==0){
            System.out.println("Utilizatorul cu idul " + userId + " nu are nicio postare");
        }else{
            for (Post postare : postarileUtilizatorului) {
                postare.setLikedBy(likeRepository.getPostLikes(postare.getId()));
                //todo setam si comentariile
                System.out.println(postare);
            }
        }


    }

    public void create() {
        System.out.println("Introduceti idul userului: ");
        int userId= scNumere.nextInt();

        User user = userRepository.readById(userId);

        if (user==null){
            System.out.println("Userul introdus nu exista");
        }else{
            System.out.println("Introduceti mesajul: ");
            String message = scText.nextLine();

            postRepository.create(message, userId, LocalDateTime.now());

        }    }

    public void upldate() {
        System.out.println("Introduceti idul postarii: ");
        Post post = postRepository.readById(scNumere.nextInt());
        if(post==null){
            System.out.println("Postarea aceasta nu exista");
        }else{
            System.out.println("Introduceti noul mesaj:");
            String mesajNou = scText.nextLine();
            postRepository.updatePostMessage(post.getId(), mesajNou);
        }

    }

    public void delete() {
        System.out.println("Introduceti idul postarii:");
        int postId = scNumere.nextInt();
        Post post = postRepository.readById(postId);
        if(post==null){
            System.out.println("Postarea cu idul " + postId + " nu exista");
        }else{
            postRepository.delete(postId);
        }
    }


}
