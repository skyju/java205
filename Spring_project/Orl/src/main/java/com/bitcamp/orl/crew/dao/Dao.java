package com.bitcamp.orl.crew.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bitcamp.orl.crew.domain.Crew;
import com.bitcamp.orl.crew.domain.CrewComment;
import com.bitcamp.orl.crew.domain.CrewCommentInfo;
import com.bitcamp.orl.crew.domain.CrewListCriteria;
import com.bitcamp.orl.crew.domain.CrewMemberList;
import com.bitcamp.orl.crew.domain.SearchType;
import com.bitcamp.orl.member.domain.Member;

public interface Dao {
	
	List<Crew> selectAll();
	
	//검색을 통한 crew 리스트 
	List<Crew> selectCrewAll(SearchType searchType);
	
	List<Crew> selectMyCrewList(@Param("memberIdx")int memberIdx);

	Crew selectCrew(@Param("crewIdx")int crewIdx);
	
	int selectCrewMemberNum(@Param("crewIdx")int crewIdx);
	
	int selectCrewCommentNum(@Param("crewIdx")int crewIdx);
	
	int selectCountMemberToRegCrew(@Param("memberIdx")int memberIdx, @Param("crewIdx")int crewIdx);
	
	Member selectMemberByMemberIdx(@Param("memberIdx")int memberIdx);
	
	List<CrewComment> selectCrewComment(@Param("crewIdx")int crewIdx);
	
	int insertCrewReg(@Param("memberIdx")int memberIdx, @Param("crewIdx")int crewIdx);
	
	int insertCrewComment(@Param("crewComment")String crewComment, @Param("memberIdx")int memberIdx, @Param("crewIdx")int crewIdx);
	
	List <CrewComment> selectCrewCommentPaging(@Param("crewIdx")int crewIdx, @Param("firstRow")int firstRow, @Param("amountPerPage")int amountPerPage);

	int insertCrew(Crew crew);
	
	int selectByCrewName(@Param("crewName")String crewName);
	
	int deleteCrew(@Param("crewIdx")int crewIdx);
	
	int updateCrew(@Param("crewName")String crewName, @Param("crewPhoto")String crewPhoto, @Param("crewDiscription")String crewDiscription, @Param("crewTag")String crewTag, @Param("crewIdx")int crewIdx);

	int updateCrewWithoutPhoto(@Param("crewName")String crewName, @Param("crewDiscription")String crewDiscription, @Param("crewTag")String crewTag, @Param("crewIdx")int crewIdx);
	
	List<CrewMemberList> selectCrewMemberList(@Param("crewIdx")int crewIdx);
	
	int deleteFromCrewMemberList(@Param("memberIdx")int memberIdx, @Param("crewIdx")int crewIdx);
	
	int deleteCrewComment(@Param("crewCommentIdx")int crewCommentIdx);
	
	CrewCommentInfo getCrewCommentInfo(@Param("crewCommentIdx")int crewCommentIdx);
	
	int updateCrewComment(@Param("crewComment")String crewComment, @Param("crewCommentIdx")int crewCommentIdx);

	//게시물 총 개수
	int CrewCount();
		
	//페이징 + 리스트
	List<Crew> listCriteria(CrewListCriteria cri);
}
