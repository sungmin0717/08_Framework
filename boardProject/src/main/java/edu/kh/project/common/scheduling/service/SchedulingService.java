package edu.kh.project.common.scheduling.service;

import java.util.List;

public interface SchedulingService {

	/** DB에 기록될 모든 파일명을 조회
	 * 
	 * @return
	 */
	List<String> getDbFileNameList();

}
