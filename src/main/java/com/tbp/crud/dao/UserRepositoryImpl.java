package com.tbp.crud.dao;

import com.tbp.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_USER_QUERY = "INSERT INTO USER(id,bookname,author,edition,phoneno,price) values(?,?,?,?,?,?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE USER SET bookname=?,edition=? WHERE ID=?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM USER WHERE ID=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID=? ";
    private static final String GET_USERS_QUERY = "SELECT * FROM USER";
    private static final String GET_USER_BY_PRICE_QUERY="SELECT BOOKNAME FROM USER WHERE PRICE=?";
    private static final String GET_USER_BY_EDITION_QUERY="SELECT * FROM USER WHERE EDITION=?";
    private static final String GET_USER_BY_AUTHOR_QUERY="SELECT BOOKNAME FROM USER WHERE  AUTHOR=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getBookname(), user.getAuthor(), user.getEdition(), user.getPhoneno(),user.getPrice());
        return user;
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY, user.getBookname(), user.getEdition(), user.getId());
        return user;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, (rs, rowNum) -> {

            return new User(rs.getInt("id"), rs.getString("bookname"), rs.getString("author"), rs.getInt("edition"), rs.getInt("phoneno"),rs.getInt("price"));
        }, id);
    }
   @Override
    public List<User> getByEdition(int edition) {
        return jdbcTemplate.query(GET_USER_BY_EDITION_QUERY, (rs, rowNum)  -> {

            return new User(rs.getInt("id"), rs.getString("bookname"), rs.getString("author"), rs.getInt("edition"),rs.getInt("phoneno"), rs.getInt("price"));
        },edition);
    }

    @Override
    public String deleteById(int id) {
        int rowsAffected=jdbcTemplate.update(DELETE_USER_BY_ID, id);
        if(rowsAffected==0){
            return "Given id does not exist in Database";
        }
        else{
            return "deletion was succcessful, Row associated with id "+id+" deleted successful";
        }
    }


        @Override
        public List<User> allUsers() {
            return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("bookname"), rs.getString("author"), rs.getInt("edition"), rs.getInt("phoneno"), rs.getInt("price")));
        }

    /*@Override
    public List<User> getPrice(int price) {
        return jdbcTemplate.query(GET_USER_BY_PRICE_QUERY,(rs,rowNum)->{
            return   new User(rs.getInt("id"),rs.getString("bookname"),rs.getString("author"),rs.getInt("edition"),rs.getInt("phoneno"),rs.getInt("price"));
        },price);*/

        @Override
        public List getPrice(int price) {
            return jdbcTemplate.query(GET_USER_BY_PRICE_QUERY,(rs,rowNum)->{
                return  rs.getString("bookname");
            },price);
    }

    @Override
    public List getAuthor(String author) {
        return jdbcTemplate.query(GET_USER_BY_AUTHOR_QUERY,(rs,rowNum)->{
            return rs.getString("bookname");
        },author);
    }
}

