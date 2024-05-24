package DAO;

import java.util.ArrayList;

public interface DAOinterface<T> {
	public T selectByID(T t);

	public ArrayList<T> selectAll();

	public int insertT(T t) throws Exception;

	public int updateT(T t) throws Exception;

	public int deleteT(T t) throws Exception;

	public int seq_num();
}
