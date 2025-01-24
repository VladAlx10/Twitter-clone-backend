package service;

import model.Post;
import model.User;
import repository.LikeRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class LikeService {

    private LikeRepository likeRepository = new LikeRepository();
    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();
    private Scanner sc = new Scanner(System.in);


    public void createLike() {
        System.out.println("introduceti idul userului:");
        int userId = sc.nextInt();
        User user = userRepository.readById(userId);

        if (user==null){
            System.out.println("Userul cu idul " + userId + " nu exista");
        }else{
            System.out.println("Introduceti idul postarii la care doriti sa dati like.");
            int postId = sc.nextInt();
            Post post = postRepository.readById(postId);
            if(post==null){
                System.out.println("Nu exista niciun post cu idul " + postId);
            }else{
                ArrayList<Integer> persoaneCareAuDatLike = likeRepository.getPostLikes(postId);
                boolean aDatDejaLike = persoaneCareAuDatLike.stream().anyMatch(id -> id == userId);
                if(aDatDejaLike){
                    System.out.println("Nu poti da like de doua ori la aceeasi postare");
                }else{
                    likeRepository.createLike(userId, postId);
                }
                likeRepository.createLike(userId, postId);
            }
        }
    }

    public void deleteLike() {
        System.out.println("Introduceti idul postarii: ");
        int postId = sc.nextInt();

        Post post = postRepository.readById(postId);

        if(post==null){
            System.out.println("Postarea cu idul " + postId + " nu exista");
        }else{
            System.out.println("Introduceti idul userlui: ");
            int userId = sc.nextInt();
            ArrayList<Integer> useriCareAuDatLike = likeRepository.getPostLikes(userId);
            boolean putemStergeLikeul = useriCareAuDatLike.stream().anyMatch(id -> id == userId);
            if(putemStergeLikeul){
                likeRepository.deleteLike(userId, postId);
            }else{
                System.out.println("Userul " + userId + " nu a dat like la postarea " + postId + ". Nu putem sterge acest like");
            }
        }


        // introducem idul postarii
        // verifici daca exista o postare cu acel id
        // daca exista idul userului al carui like vrei sa fie sters
        // Daca postarea respectiva are un like de la userul introdus, atunci stergem likeul
    }
}
