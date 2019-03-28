package univ.iutmetz.developpemtnmob1;

public interface DAO<T> {

    public void findAll();
    public void insert(T objet);
    public void traiteFindAll(String result);

}
