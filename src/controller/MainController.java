package controller;

import service.CommentService;
import service.LikeService;
import service.PostService;
import service.UserService;
import utils.Flow;

import java.util.Scanner;

public class MainController {

    private static Scanner sc = new Scanner(System.in);

    private static UserService userService = new UserService();
    private static PostService postService = new PostService();
    private static LikeService likeService = new LikeService();
    private static CommentService commentService = new CommentService();
    

    public static void main(String[] args) {

        System.out.println("Alegeti flowul: (user/post/like)");
        String flow = sc.nextLine();

        switch (flow) {
            case "user":
                startUserFlow();
                break;
            case "post":
                startPostFlow();
                break;
            case "like":
                startLikeFlow();
            case "comment":
                startCommentFlow();
                break;
            default:
                System.out.println("Nu ai ales un flow valid");
        }

    }

    private static void startCommentFlow(){
        switch (insertOperation(Flow.COMMENT)){
            case "C":
            commentService.createComment();
                break;
            case "U":
            commentService.updateComment();
                break;
            case "D":
            commentService.deleteComment();
                break;
            default:
                printWrongOperationMessage();

        }
    }

    public static void startUserFlow() {
        switch (insertOperation(Flow.USER)) {
            case "RA":
                userService.readAll();
                break;
            case "RBI":
                userService.readById();
                break;
            case "C":
                userService.create();
                break;
            case "U":
                userService.update();
                break;
            case "D":
                userService.delete();
                break;
            default:
                printWrongOperationMessage();
        }
    }

    public static void startPostFlow() {
        switch (insertOperation(Flow.POST)) {
            case "RA":
                postService.readAll();
                break;
            case "RBI":
                postService.readById();
                break;
            case "RBUI":
                postService.readByUserId();
                break;
            case "C":
                postService.create();
                break;
            case "U":
                postService.upldate();
                break;
            case "D":
                postService.delete();
                break;
            default:
                printWrongOperationMessage();
        }
    }

    public static void startLikeFlow(){
        switch(insertOperation(Flow.LIKE)){
            case "C":
                likeService.createLike();
            case "D":
                likeService.deleteLike();
            default:
                printWrongOperationMessage();
        }
    }

    private static String insertOperation(Flow flow) {
        System.out.print("Alegeti operatia dorita:");
        switch (flow){
            case USER:
                System.out.println("(RA, RBI, C, U, D)");
                break;
            case POST:
                System.out.println("(RA, RBI, RBUI C, U, D)");
            case LIKE:
                System.out.println("(C, D)");
            case COMMENT:
                System.out.println("(C, U, D)");
                break;

        }
        return sc.nextLine();
    }

    private static void printWrongOperationMessage() {
        System.out.println("Nu ai ales operatie CRUD valida");
    }
}
