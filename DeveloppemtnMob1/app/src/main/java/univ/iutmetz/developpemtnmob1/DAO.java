package univ.iutmetz.developpemtnmob1;

public interface DAO<T> {

    public void findAll();
    public void Libre(User u);
    public void insert(T objet);
    public void traiteFindAll(String result);

}
