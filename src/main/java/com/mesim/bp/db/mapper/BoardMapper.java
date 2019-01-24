package com.mesim.bp.db.mapper;

import com.mesim.bp.web.dto.BoardData;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MisunKim
 * Date: 2019-01-24 오후 2:10
 *
 * 게시판 관련 Mybatis Mapper Interface
 */
@Mapper
public interface BoardMapper {

    List<BoardData> getBoardList() throws SQLException;
}
