package za.ac.cput.Service;

public interface IService<T, ID> {
    T create(T obj);
    T read(ID id);
    T update(T obj);
    boolean delete(ID id);
}
