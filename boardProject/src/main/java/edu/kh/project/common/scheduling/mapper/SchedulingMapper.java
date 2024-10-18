package edu.kh.project.common.scheduling.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulingMapper {

	/** getDbFileNameList
	 * 
	 * @return
	 */
	List<String> getDbFileNameList();

}
