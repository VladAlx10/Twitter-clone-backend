package service;

public class CommentService {
    public void createComment() {
        //todo introduc idul postarii
        //todo daca acea postare exista, pot sa adaug un comentariu
        //todo introduc idul userului care vrea sa commenteze
        //todo daca acel user exista, -> las comentariul postarii
        //todo introduc un mesaj
        //todo pe care il salvez in baza de date - trimit requestul catre CommentRepository

    }

    public void updateComment() {
        //todo introduc idul postarii
        //todo verific daca exista. DACA EXISTA, o sa o si printez
        //todo o sa pot sa introduc idul comentului pe care vreau sa il modific
        //todo daca introduc un id valabil, atunci
        //todo introduc idul userului care vrea sa modifice comentariul
        //todo daca acel comment a fost facut de userul respectiv
        //todo introduc noul mesaj
        //todo trimit requestul catre CommentRepository si modific comentariul

    }

    public void deleteComment() {
        //todo introducem idul postarii
        //todo verificam daca exista. Daca exista, printez
        //todo introduc idul comentului pe care vreau sa il sterg
        //todo daca acel comment exista, introducem idul userului care a comentat
        //todo daca am introdus idul corespunzator, atunci stergem comentariul (-> Comment Repository)
    }
}
