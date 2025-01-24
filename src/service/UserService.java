package service;

import model.Post;
import model.User;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {

    private static Scanner scNumere = new Scanner(System.in);
    private static Scanner scText = new Scanner(System.in);
    private static Scanner scBoolean = new Scanner(System.in);

    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();
    private LikeRepository likeRepository = new LikeRepository();
    //todo adaugam CommentRepository

    public void readAll() {
        ArrayList<User> users = userRepository.readAll();


        // citim postarea fiecarui user din tabelul Post
        for (User user : users) {

            user.setPostari(postRepository.readUserPosts(user.getId()));

            for (Post post : user.getPostari()) {
                post.setLikedBy(likeRepository.getPostLikes(post.getId()));
                //todo setam comentariile
            }

        }


        for (User user : users) {
            System.out.println(user);
        }
    }

    public void readById() {
        System.out.println("Introduceti idul userului dorit:");
        int id = scNumere.nextInt();

        User myUser = userRepository.readById(id);

        if(myUser != null){
            myUser.setPostari(postRepository.readUserPosts(id));
            for (Post post : myUser.getPostari()) {
                post.setLikedBy(likeRepository.getPostLikes(post.getId()));
                //todo setam si comentariile
            }

        }

        System.out.println(myUser);

    }

    public void create() {
        System.out.println("Introduceti numele: ");
        String nume = scText.nextLine();

        System.out.println("introduceti prenumele: ");
        String prenume = scText.nextLine();

        System.out.println("introduceti varsta: ");
        int varsta = scNumere.nextInt();

        userRepository.create(nume, prenume, varsta);
    }

    public void update() {
        System.out.println("Introduceti idul userului dorit:");
        int id=scNumere.nextInt();

        User user = userRepository.readById(id);

        if(user==null){
            System.out.println("Nu exista acest user");
        }else{
            System.out.println("Doriti sa modificati numele?");
            boolean modificamNumele = scBoolean.nextBoolean();

            System.out.println("Doriti sa modificati prenumele?");
            boolean modificamPrenumele = scBoolean.nextBoolean();

            System.out.println("Doriti sa modificati varsta?");
            boolean modificamVarsta = scBoolean.nextBoolean();

            if(modificamNumele){
                System.out.println("introduceti noul prenume: ");
                String nume = scText.nextLine();
                userRepository.updateNume(id, nume);
            }

            if(modificamPrenumele){
                System.out.println("Introduceti noul prenume: ");
                String prenume = scText.nextLine();
                userRepository.updatePrenume(id, prenume);
            }

            if(modificamVarsta){
                System.out.println("Introduceti noua varsta: ");
                int varsta = scNumere.nextInt();
                userRepository.updateVarsta(id, varsta);
            }
        }
    }

    public void delete() {
        System.out.println("Introduceti id-ul userlui pe care doriti sa il stergeti: ");
        int id = scNumere.nextInt();

        userRepository.delete(id);
    }

}
