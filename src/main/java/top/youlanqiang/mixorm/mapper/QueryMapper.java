package top.youlanqiang.mixorm.mapper;

import top.youlanqiang.mixorm.mate.EntityMate;

import java.sql.*;
import java.util.List;

/**
 * 使用Select操作的结果
 */
public class QueryMapper<T> {


    private EntityMate<T> mate;


    public QueryMapper(EntityMate<T> mate) {
        this.mate = mate;
    }


    List<T> queryToList(Connection conn, String sql, List<Object> param){

        return null;
    }


    T queryToSingle(Connection conn, String sql, List<Object> param){
        return null;
    }

    InsertResult insert(Connection conn, String sql, List<Object> param){
        return null;
    }

    Integer executeToUpdate(Connection conn, String sql, List<Object> param){
        return null;
    }

    public void close(Connection conn, Statement state, ResultSet rs){
        try {
            if(rs != null)rs.close();
            if(state != null)state.close();
            if(conn != null && !conn.isClosed())conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static class InsertResult{
        private Integer count;

        private List<Object> idList;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Object> getIdList() {
            return idList;
        }

        public void setIdList(List<Object> idList) {
            this.idList = idList;
        }
    }
}
