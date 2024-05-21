package DAO;

import java.util.ArrayList;

public interface DAOinterface<T> {
	public T selectByID(T t);

	public ArrayList<T> selectAll();

	public int insertT(T t);

	public int updateT(T t);

	public int deleteT(T t);

	public int seq_num();
}
